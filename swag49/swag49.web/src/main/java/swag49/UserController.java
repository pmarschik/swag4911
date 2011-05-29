package swag49;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import swag49.dao.DataAccessObject;
import swag49.model.UserDTO;
import swag49.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author michael
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    @Qualifier("userDAO")
    private DataAccessObject<User> userDAO;

    private Integer counter = 0;
    private Map<Integer, UserDTO> users = new HashMap<Integer, UserDTO>();

    private UserDTO loggedInUser = null;

    @RequestMapping(value = "/doUserRegistration", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("userDTO")
                                   UserDTO userDTO, BindingResult bingBindingResult) {
        System.out.println("Added new user:" + userDTO);
        userDTO.setId(generateId());
        users.put(userDTO.getId(), userDTO);
        return "redirect:register";
    }

    @RequestMapping(value = "/register")
    public String register(Map<String, Object> map) {
        map.put("userDTO", new UserDTO());
        map.put("userList", users.values());
        return "register";
    }

    @RequestMapping(value = "/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) {
        users.remove(userId);
        System.out.println("Remove User with id: " + userId);
        return "redirect:../register";
    }

    @RequestMapping(value = "/")
    public String handler() {

        String redirect = "";

        if(loggedInUser == null)
            redirect = "redirect:login";
        else
            redirect = "redirect:register";

        return redirect;
    }


    @RequestMapping(value = "/login")
    public ModelAndView login() {
        return new ModelAndView("login", "command", new UserDTO());
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String loginUser(@ModelAttribute("contact")
                                UserDTO userDTO, BindingResult bingBindingResult) {
        System.out.println("Logged in user:" + userDTO);
        return "redirect:register";
    }

    private Integer generateId() {
        return counter++;
    }


}
