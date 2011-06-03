package swag49.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import swag49.dao.DataAccessObject;
import swag49.model.MapLocation;
import swag49.model.User;
import swag49.util.Log;
import swag49.web.HelperComponent;
import swag49.web.TokenService;
import swag49.web.model.MapLocationDTO;
import swag49.web.model.UserLoginDTO;
import swag49.web.model.UserRegisterDTO;

import javax.validation.Valid;
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

    private static String mapController = "/swag/map/";

    @Autowired
    @Qualifier("userDAO")
    private DataAccessObject<User> userDAO;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private HelperComponent helperComponent;

    private Integer counter = 0;
    private Map<String, UserRegisterDTO> users = new HashMap<String, UserRegisterDTO>();

    private UserLoginDTO loggedInUser = null;
    private UUID userToken = null;

    private Map<Long, MapLocationDTO> myMaps = new HashMap<Long, MapLocationDTO>();

    public UserController() {
        UserRegisterDTO user = new UserRegisterDTO();
        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("test@swag.com");
        users.put("test", user);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("userRegisterDTO")
                               UserRegisterDTO userRegisterDTO, BindingResult bingBindingResult,
                               Map<String, Object> map) {

        if (bingBindingResult.hasErrors())
            return "register";

        if (users.containsKey(userRegisterDTO.getUsername())) {
            map.put("registerError", "User with username: " +
                    userRegisterDTO.getUsername() + " is already registered!");
            return "register";
        }

        System.out.println("Added new user:" + userRegisterDTO);
        users.put(userRegisterDTO.getUsername(), userRegisterDTO);

        return "redirect:./";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Map<String, Object> map) {

        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();

        map.put("userRegisterDTO", userRegisterDTO);
        map.put("userList", users.values());
        return "register";
    }

    @RequestMapping(value = "/delete/{username}")
    public String deleteUser(@PathVariable("username") String username) {
        users.remove(username);
        System.out.println("Remove User with username: " + username);
        return "redirect:./";
    }

    @RequestMapping(value = "/")
    public String handle(Map<String, Object> map) {

        map.put("loggedInUser", loggedInUser);

        return "overview";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Map<String, Object> map) {
        map.put("userLoginDTO", new UserLoginDTO());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(@Valid @ModelAttribute("userLoginDTO")
                            UserLoginDTO userLoginDTO, BindingResult bingBindingResult, Map<String, Object> map) {

        if (bingBindingResult.hasErrors())
            return "login";

        UserRegisterDTO user = users.get(userLoginDTO.getUsername());

        if (user == null) {
            map.put("loginError", "Username not found!");
            return "login";
        }

        if (!user.getPassword().equals(userLoginDTO.getPassword())) {
            map.put("loginError", "Username/Password combination doesn't match!");
            return "login";
        }

        System.out.println("Logged in user:" + userLoginDTO);

        this.loggedInUser = userLoginDTO;

        // TODO set UserToken
        generateToken();
        return "redirect:./";
    }

    private void generateToken() {
        User user = new User();
        user.setId(0L);
        UUID token = tokenService.generateToken(user);
        this.userToken = token;
    }

    @RequestMapping(value = "/overview")
    public String handleOverview() {
        return "redirect:./";
    }

    @RequestMapping(value = "/logout")
    public String logoutUser() {
        this.loggedInUser = null;
        return "redirect:./";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Map<String, Object> map) {

        if (this.loggedInUser != null) {
            UserRegisterDTO user = users.get(loggedInUser.getUsername());
            map.put("user", user);
            return "edit";
        } else
            return "redirect:./";

    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(@Valid @ModelAttribute("user")
                           UserRegisterDTO userRegisterDTO, BindingResult bingBindingResult) {

        if (bingBindingResult.hasErrors())
            return "edit";

        System.out.println("Updated user:" + userRegisterDTO);
        users.put(userRegisterDTO.getUsername(), userRegisterDTO);

        return "redirect:./";
    }

    @RequestMapping(value = "/maps", method = RequestMethod.GET)
    public String maps(Map<String, Object> map) {
        if (loggedInUser == null)
            return "redirect:./";

        List<MapLocationDTO> availableMaps = getMapLocations();
        map.put("availableMapLocations", getMapLocations());
        map.put("myMapLocations", myMaps.values());

        return "maps";
    }

    @RequestMapping(value = "/join/{id}")
    public String joinMap(@PathVariable("id") Long id) {

        MapLocationDTO mapLocation = getMapLocation(id);

        myMaps.put(mapLocation.getId(), mapLocation);

        return "redirect:../maps";
    }


    @RequestMapping(value = "/play/{id}")
    public String playMap(@PathVariable("id") Long id) {

        MapLocationDTO mapLocation = getMapLocation(id);

        return "redirect:" + mapLocation.getUrl() + mapController + userToken.toString();
    }

    public MapLocationDTO getMapLocation(Long id) {
        MapLocationDTO ret = null;

        for (MapLocation mapLocation : helperComponent.getMapLocations()) {
            if (mapLocation.getId().equals(id)) {
                MapLocationDTO temp = new MapLocationDTO();
                temp.setUrl(mapLocation.getUrl());
                temp.setId(mapLocation.getId());
                ret = temp;

                break;
            }

        }

        return ret;
    }


    public List<MapLocationDTO> getMapLocations() {
        List<MapLocationDTO> mapLocations = new ArrayList<MapLocationDTO>();

        for (MapLocation mapLocation : helperComponent.getMapLocations()) {

            if (myMaps.get(mapLocation.getId()) != null)
                continue;
            ;

            MapLocationDTO mapLocationDTO = new MapLocationDTO();
            mapLocationDTO.setUrl(mapLocation.getUrl());
            mapLocationDTO.setId(mapLocation.getId());
            mapLocations.add(mapLocationDTO);

        }

        return mapLocations;
    }


//    @RequestMapping(value = "/messages", method = RequestMethod.GET)
//    public String messages() {
//        System.out.println("Redirecting to Messaging Controller!");
//        return "redirect:../messaging/";
//    }

    public UserLoginDTO getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(UserLoginDTO loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
