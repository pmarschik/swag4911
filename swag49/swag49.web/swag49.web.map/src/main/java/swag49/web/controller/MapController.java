package swag49.web.controller;

import gamelogic.MapLogic;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import swag49.dao.DataAccessObject;
import swag49.model.*;
import swag49.util.Log;
import swag49.web.model.TileOverviewDTO;
import swag49.web.model.TokenDTO;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.Map;

/**
 * @author michael
 */
@Controller
@Scope(value = "session")
@RequestMapping(value = "/map")
public class MapController {

    private static final String userManagement = "http://localhost:8080/user/swag";
    private static final String tokenService = "/token/";

    private static final int VIEWSIZE = 3;

    @Log
    private static Logger logger;

    @Autowired
    @Qualifier("mapDAO")
    private DataAccessObject<swag49.model.Map, Long> mapDAO;

    @Autowired
    @Qualifier("playerDAO")
    private DataAccessObject<Player, Long> playerDAO;

    @Autowired
    @Qualifier("tileDAO")
    private DataAccessObject<Tile, Tile.Id> tileDAO;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NodeController nodeController;

    @Autowired
    private MapLogic mapLogic;

    private UUID userToken;
    private Long userID;

    @PostConstruct
    @Transactional
    public void init() {
        swag49.model.Map example = new swag49.model.Map();
        example.setUrl(nodeController.getMapNodeUrl());
        logger.error("Map url: " + nodeController.getMapNodeUrl());

        Collection<swag49.model.Map> maps = mapDAO.queryByExample(example);
        if (maps != null && maps.size() == 1) {
            map = maps.iterator().next();
            logger.error("Map with id " + map.getId() + " found");
        } else {
            logger.error("Error while finding map");
        }
    }


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
                logger.error("Player " + player.getId() + " found");
            } else if (playerValues != null && playerValues.size() == 0) {
                // create new player & create start conditions ( map, resources, units, etc)

                player = new Player();
                player.setUserId(userID);
                player.setPlays(map);
                player.setOnline(true);
                player.setDeleted(false);
                player.setResources(new ResourceValue());
                player.setIncome(new ResourceValue());
                player.setUpkeep(new ResourceValue());
                player = playerDAO.create(player);

                mapLogic.initializePlayer(map, player);

                logger.info("Player " + player.getId() + " initialized");
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Transactional
    public String getMapOverview(@RequestParam(value = "xLow", defaultValue = "-1") int x_low,
                                 @RequestParam(value = "yLow", defaultValue = "-1") int y_low,
                                 @RequestParam(value = "xHigh", defaultValue = "-1") int x_high,
                                 @RequestParam(value = "yHigh", defaultValue = "-1") int y_high, Model model) {


        //TODO: besser machen
        player = playerDAO.get(player.getId());
        map = mapDAO.get(map.getId());


        //default values
        if (x_low == x_high && y_low == y_high && y_low == x_low && x_low == -1) {
            //focus on home base
            Base homeBase = null;
            if (player.getOwns() != null && !player.getOwns().isEmpty()) {
                for (Base base : player.getOwns()) {
                    if (base.isHome()) {
                        homeBase = base;
                        break;
                    }
                }
                int maxVal = (int) Math.sqrt(map.getConsistsOf().size());

                x_low = Math.max(0, homeBase.getLocatedOn().getId().getX() - VIEWSIZE);
                x_high = Math.min(maxVal, homeBase.getLocatedOn().getId().getX() + VIEWSIZE + 1);
                y_low = Math.max(0, homeBase.getLocatedOn().getId().getY() - VIEWSIZE);
                y_high = Math.min(maxVal, homeBase.getLocatedOn().getId().getY() + VIEWSIZE + 1);
            } else {
                x_low = 0;
                y_low = 0;
                x_high = 2 * VIEWSIZE + 1;
                y_high = 2 * VIEWSIZE + 1;
            }
        }
        model.addAttribute("xLow", x_low);
        model.addAttribute("yLow: ", y_low);
        model.addAttribute("xHigh: ", x_high);
        model.addAttribute("yHigh: ", y_high);
        ArrayList<ArrayList<TileOverviewDTO>> displayedTiles = new ArrayList<ArrayList<TileOverviewDTO>>();

        // get all visible tiles
        Tile.Id id = new Tile.Id(map.getId(), 0, 0);
        for (int y = y_low; y <= y_high; y++) {
            ArrayList<TileOverviewDTO> currentRow = new ArrayList<TileOverviewDTO>();
            for (int x = x_low; x <= x_high; x++) {
                id.setX(Integer.valueOf(x));
                id.setY(Integer.valueOf(y));
                Tile tile = tileDAO.get(id);
                if (tile != null) {
                    TileOverviewDTO dto = new TileOverviewDTO(tile);

                    dto.setSpecialResource(tile.getSpecial());

                    // TODO: TOOLTIP Java Script???

                    // create info
                    StringBuilder sb = new StringBuilder();

                    // check if base
                    if (tile.getBase() != null) {
                        if (tile.getBase().getOwner().getId() != player.getId()) {
                            sb.append("Enemy base owned by ");
                            sb.append(tile.getBase().getOwner());
                        } else {
                            sb.append("Your base!");
                        }
                        System.out.println("BAse found: " + tile.getId().getX() + tile.getId().getY());
                        sb.append("<br/>");
                    }

                    // check for troops
                    if (!tile.getTroops().isEmpty()) {
                        for (Troop troop : tile.getTroops()) {
                            sb.append("TODO");
                            sb.append("<br/>");
                        }

                        sb.append("<br/>");
                    }

                    // check for special resources
                    if (tile.getSpecial() != ResourceType.NONE) {
                        sb.append("Special resouce: ");
                        sb.append(tile.getSpecial().toString());
                    }

                    if (sb.length() == 0) {
                        sb.append("Empty Tile...");
                    }

                    dto.setInfo(sb.toString());

                    if (tile.getBase() != null)
                        dto.setHasBase(true);
                    else
                        dto.setHasBase(false);

                    if (checkForEnemyTerritory(tile))
                        dto.setEnemyTerritory(true);
                    else
                        dto.setEnemyTerritory(false);

                    if (tile.getTroops().isEmpty())
                        dto.setHasTroops(false);
                    else
                        dto.setHasTroops(true);

                    currentRow.add(dto);
                }

            }
            displayedTiles.add(currentRow);
        }
        model.addAttribute("tiles", displayedTiles);

        return "mapoverview";
    }

    private boolean checkForEnemyTerritory(Tile tile) {
        if (tile.getBase() != null && tile.getBase().getOwner() != player)
            return true;
        if (!tile.getTroops().isEmpty()
                && tile.getTroops().iterator().next().getOwner() != player)
            return true;

        return false;
    }


}
