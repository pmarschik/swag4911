package swag49.web.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import swag49.dao.DataAccessObject;
import swag49.model.Address;
import swag49.model.MapLocation;
import swag49.model.User;
import swag49.transfer.model.MapLocationDTO;
import swag49.transfer.model.TokenDTO;
import swag49.transfer.model.UserDTO;
import swag49.transfer.model.UserLoginDTO;
import swag49.util.Log;
import swag49.web.TokenService;

import javax.validation.Valid;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @author michael
 */
@Controller
@Scope(value = "session")
@RequestMapping(value = "/user")
public class UserController {

    @Log
    private static Logger logger;

    private static String mapController = "/swag/map/authenticate";

    @Autowired
    @Qualifier("userDAO")
    private DataAccessObject<User, String> userDAO;

    @Autowired
    @Qualifier("mapLoactionDAO")
    private DataAccessObject<MapLocation, Long> mapLocationDAO;

    @Autowired
    private TokenService tokenService;

    private UserDTO loggedInUser = null;
    private UUID userToken = null;

    public UserController() {
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @Transactional("swag49.user")
    public String registerUser(@Valid @ModelAttribute("user")
                               UserDTO userDTO, BindingResult bingBindingResult,
                               Map<String, Object> map) {

        if (bingBindingResult.hasErrors())
            return "register";

        User user = new User();
        Collection<User> users = userDAO.queryByExample(user);

        // check if username already exists
        if (users != null) {
            for (User u : users) {
                if (u.getUsername().equals(userDTO.getUsername())) {
                    map.put("registerError", "User with username: " +
                            userDTO.getUsername() + " is already registered!");
                    return "register";
                }
            }
        }

        // register user
        System.out.println("Registered new user:" + userDTO);

        // TODO set correct utc offset
        userDTO.setUtcOffset(0);
        userDAO.create(createUserFromDTO(userDTO));

        return "redirect:./";
    }

    private User createUserFromDTO(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUtcOffset(userDTO.getUtcOffset());

        Address address = new Address();
        address.setState(userDTO.getState());
        address.setCity(userDTO.getCity());
        address.setPostalCode(userDTO.getPostalCode());
        address.setStreet(userDTO.getStreet());
        user.setAddress(address);

        return user;
    }

    private UserDTO createDTOFromUser(User user) {
        return new UserDTO(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getUtcOffset(), user.getAddress().getState(), user.getAddress().getCity(),
                user.getAddress().getPostalCode(), user.getAddress().getStreet());
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Map<String, Object> map) {
        UserDTO userDTO = new UserDTO();
        map.put("user", userDTO);
        map.put("userList", getRegisteredUsers());
        return "register";
    }

    @Transactional("swag49.user")
    private List<UserDTO> getRegisteredUsers() {
        List<UserDTO> users = new ArrayList<UserDTO>();

//        Collection<User> registeredUsers = userDAO.queryByExample(new User());
//        if (registeredUsers != null) {
//            for (User user : registeredUsers) {
//                users.add(new UserDTO(user));
//            }
//        }
        return users;
    }

    @RequestMapping(value = "/delete/{id}")
    @Transactional("swag49.user")
    public String deleteUser(@PathVariable("id") String userID) {
        User user = userDAO.get(userID);
        if (user != null) {
            userDAO.delete(user);
            System.out.println("Remove User with username: " + user.getUsername());
        }
        return "redirect:./";
    }

    @RequestMapping(value = "/")
    public String handle(Map<String, Object> map) {
        map.put("loggedInUser", loggedInUser);
        return "overview";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Map<String, Object> map) {
        if (loggedInUser != null)
            return "redirect:./";

        map.put("user", new UserLoginDTO());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @Transactional("swag49.user")
    public String loginUser(@Valid @ModelAttribute("user")
                            UserLoginDTO userLoginDTO, BindingResult bingBindingResult, Map<String, Object> map) {

        if (bingBindingResult.hasErrors())
            return "login";

        User template = new User();
        List<User> users = userDAO.queryByExample(template);
        User user = null;

        if (users != null) {
            for (User u : users) {
                if (u.getUsername().equals(userLoginDTO.getUsername())) {
                    user = u;
                    break;
                }
            }
        }

        if (user == null) {
            map.put("loginError", "Username not found!");
            return "login";
        }

        if (!user.getPassword().equals(userLoginDTO.getPassword())) {
            map.put("loginError", "Username/Password combination doesn't match!");
            return "login";
        }

        UserDTO loggedInUser = createDTOFromUser(user);

        // check if user is already logged in in another session
        if (tokenService.hasTokenForUser(user)) {
            map.put("loginError", "User is already logged in in another session!");
            return "login";
        }

        System.out.println("Logged in user:" + loggedInUser);

        this.loggedInUser = loggedInUser;

        // set UserToken
        this.userToken = tokenService.generateToken(user);

        return "redirect:./";
    }

    @RequestMapping(value = "/overview")
    public String handleOverview() {
        return "redirect:./";
    }

    @RequestMapping(value = "/logout")
    public String logoutUser() {
        tokenService.removeToken(userToken);
        this.loggedInUser = null;
        return "redirect:./";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Map<String, Object> map) {
        if (this.loggedInUser != null) {
            UserDTO user = createDTOFromUser(userDAO.get(loggedInUser.getUsername()));
            System.out.println("Try to update user: " + user);
            map.put("user", user);
            return "edit";
        } else
            return "redirect:./";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @Transactional("swag49.user")
    public String editUser(@Valid @ModelAttribute("user")
                           UserDTO userDTO, BindingResult bingBindingResult) {
        if (bingBindingResult.hasErrors())
            return "edit";

        // TODO set corect utcOffset
        userDTO.setUtcOffset(0);
        System.out.println("Updated user:" + userDTO);

        User user = userDAO.get(loggedInUser.getUsername());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUtcOffset(userDTO.getUtcOffset());

        Address address = new Address();
        address.setState(userDTO.getState());
        address.setCity(userDTO.getCity());
        address.setPostalCode(userDTO.getPostalCode());
        address.setStreet(userDTO.getStreet());
        user.setAddress(address);

        user = userDAO.update(user);

        this.loggedInUser = createDTOFromUser(user);

        return "redirect:./";
    }

    @RequestMapping(value = "/maps", method = RequestMethod.GET)
    @Transactional("swag49.user")
    public String maps(Map<String, Object> map) throws UnknownHostException {
        if (loggedInUser == null)
            return "redirect:./";

        User user = userDAO.get(loggedInUser.getUsername());

        String remoteAddressString =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
                        .getRemoteAddr();
        InetAddress remoteAddress = InetAddress.getByName(remoteAddressString);

        List<MapLocationDTO> availableMaps = getMapLocations(user);
        List<MapLocationDTO> userMapLocations = getUserMapLocations(user);

        if (!remoteAddress.isLoopbackAddress()) {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();

            for (MapLocationDTO mapLocation : userMapLocations) {
                String newUrl = mapLocation.getUrl().replace("localhost", hostAddress);
                mapLocation.setUrl(newUrl);
            }
        }

        map.put("availableMapLocations", availableMaps);
        map.put("myMapLocations", userMapLocations);
        map.put("tokenDTO", new TokenDTO(userToken, null, null));
        map.put("mapController", mapController);

        return "maps";
    }

    @Transactional("swag49.user")
    private List<MapLocationDTO> getUserMapLocations(User user) {
        List<MapLocationDTO> mapLocations = new ArrayList<MapLocationDTO>();

        for (MapLocation mapLocation : user.getMapLocations())
            mapLocations.add(new MapLocationDTO(mapLocation.getId(), mapLocation.getUrl(), mapLocation.getMapName()));

        return mapLocations;
    }

    @RequestMapping(value = "/join/{id}")
    @Transactional("swag49.user")
    public String joinMap(@PathVariable("id") Long mapLocationID) {

        User user = userDAO.get(loggedInUser.getUsername());
        user.getMapLocations().add(getMapLocation(mapLocationID));
        userDAO.update(user);

        return "redirect:../maps";
    }

    @Transactional("swag49.user")
    public MapLocation getMapLocation(Long mapLocationID) {
        MapLocation mapLocation = mapLocationDAO.get(mapLocationID);
        return mapLocation;
    }

    @Transactional("swag49.user")
    public List<MapLocationDTO> getMapLocations(User user) {
        List<MapLocationDTO> mapLocations = new ArrayList<MapLocationDTO>();

        Collection<MapLocation> availableMaps = mapLocationDAO.queryByExample(new MapLocation());

        if (availableMaps != null) {
            for (MapLocation mapLocation : availableMaps) {
                if (!user.getMapLocations().contains(mapLocation))
                    mapLocations.add(
                            new MapLocationDTO(mapLocation.getId(), mapLocation.getUrl(), mapLocation.getMapName()));
            }
        }

        return mapLocations;
    }

    public UserDTO getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(UserDTO loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
