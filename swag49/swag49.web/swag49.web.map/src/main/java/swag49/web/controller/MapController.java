package swag49.web.controller;

import gamelogic.MapLogic;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import swag49.dao.DataAccessObject;
import swag49.model.Player;
import swag49.util.Log;
import swag49.web.model.TokenDTO;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author michael
 */
@Controller
@Scope(value = "session")
@RequestMapping(value = "/map")
public class MapController {

    private static final String userManagement = "http://localhost:8080/user/swag";
    private static final String tokenService = "/token/";

    @Log
    private static Logger logger;

    @Autowired
    @Qualifier("mapDAO")
    private DataAccessObject<swag49.model.Map> mapDAO;

    @Autowired
    @Qualifier("playerDAO")
    private DataAccessObject<Player> playerDAO;

    @Autowired
    private RestTemplate restTemplate;

    private UUID userToken;
    private Long userID;


    //TODO: GET MAP AND PLAYER
    private swag49.model.Map map;
    private Player player;

    @RequestMapping(value = "/{token}")
    @Transactional
    public String initPlayer(@PathVariable("token") String token) {

        System.out.println("Got request with token: " + token);

        UUID userToken = UUID.fromString(token);

        // verify token
        Map<String, UUID> vars = new HashMap<String, UUID>();
        vars.put("token", userToken);
        TokenDTO tokenDTO = restTemplate.getForObject(userManagement + tokenService + "{token}",
                TokenDTO.class, vars);

        System.out.println("Got TokenDTO: " + tokenDTO);

        if (tokenDTO != null) {
            this.userToken = tokenDTO.getToken();
            this.userID = tokenDTO.getUserId();
        }

        if (map != null) {
            //test if a player for that user exists
            Player example = new Player();
            example.setPlays(map);
            example.setUserId(this.userID);
            Collection<Player> playerValues = playerDAO.queryByExample(example);
            if (playerValues != null && playerValues.size() == 1) {
                player = playerValues.iterator().next();
            } else if (playerValues != null && playerValues.size() == 0) {
                // create new player & create start conditions ( map, resources, units, etc)
                MapLogic.initializePlayer(map, player);
            } else {
                //THIS SHOULD NEVER HAPPEN
                //TODO: fehlerfall gescheit behandeln
                return "HELP";
            }
        }
        return "redirect:./";
    }

    @RequestMapping(value = "/")
    public String handle(Map<String, Object> map) {

        map.put("userID", this.userID);

        return "home";
    }

    @RequestMapping(value = "/messaging", method = RequestMethod.GET)
    public String messaging() {
        return "redirect:../messaging/";
    }

    @RequestMapping(value = "/mapoverview", method = RequestMethod.GET)
    public String mapoverview() {
        return "redirect:../mapoverview/";
    }

    public UUID getUserToken() {
        return userToken;
    }

    public void setUserToken(UUID userToken) {
        this.userToken = userToken;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
