package swag49;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author michael
 */
@Controller
@RequestMapping(value = "/map")
public class MapController {

    @Autowired
    UserController userController;

    @RequestMapping(value = "/")
    public String handle(Map<String, Object> map) {

        map.put("controllerMessage", userController.getLoggedInUser());

        return "home";
    }

}
