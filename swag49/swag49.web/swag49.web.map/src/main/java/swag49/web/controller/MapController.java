package swag49.web.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import swag49.util.Log;

import java.util.Map;
import java.util.UUID;

/**
 * @author michael
 */
@Controller
@Scope(value = "session")
@RequestMapping(value = "/map")
public class MapController {

    @Log
    private static Logger logger;

    private UUID userToken;
    private Long userID;

    @RequestMapping(value = "/{token}")
    public String initPlayer(@PathVariable("token") String token) {

        System.out.println("Got request with token: " + token);

        UUID userToken = UUID.fromString(token);

        // verify token

        return "redirect:./";
    }

    @RequestMapping(value = "/")
    public String handle() {
        return "home";
    }

}
