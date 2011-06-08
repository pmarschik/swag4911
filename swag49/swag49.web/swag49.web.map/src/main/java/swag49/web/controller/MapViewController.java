package swag49.web.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import swag49.dao.DataAccessObject;
import swag49.model.*;
import swag49.util.Log;
import swag49.web.model.TileOverviewDTO;
import swag49.web.model.TroopDTO;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;

@Controller
@Scope(value = "session")
@RequestMapping(value = "/mapoverview")
public class MapViewController {

    @Autowired
    @Qualifier("tileDAO")
    private DataAccessObject<Tile, Tile.Id> tileDAO;

    @Autowired
    @Qualifier("mapDAO")
    private DataAccessObject<Map, Long> mapDAO;

    @Log
    private static Logger logger;

    @Autowired
    private NodeContext nodeController;

    private static final int VIEWSIZE = 3;

    private Player player;

    private Map map;

    @PostConstruct
    @Transactional
    public void init() {
        swag49.model.Map example = new swag49.model.Map();
        example.setUrl(nodeController.getMapNodeUrl());

        Collection<swag49.model.Map> maps = mapDAO.queryByExample(example);
        if (maps != null && maps.size() == 1) {
            map = maps.iterator().next();
        } else {
            logger.error("Error while finding map");
        }
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Transactional
    public String getMapOverview(@RequestParam(value = "xLow", defaultValue = "-1") int x_low,
                                 @RequestParam(value = "yLow", defaultValue = "-1") int y_low,
                                 @RequestParam(value = "xHigh", defaultValue = "-1") int x_high,
                                 @RequestParam(value = "yHigh", defaultValue = "-1") int y_high, Model model) {

        //TESTCODE - MUST BE REMOVED ASAP
        if (map == null) {
            //Map emptyMap = new Map();
            // emptyMap.setId());
            map = mapDAO.get(Long.valueOf(2));
            //  map = mapDAO.queryByExample(emptyMap).iterator().next();
        }

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

                x_low = homeBase.getLocatedOn().getId().getX() - VIEWSIZE;
                x_high = homeBase.getLocatedOn().getId().getX() + VIEWSIZE + 1;
                y_low = homeBase.getLocatedOn().getId().getY() - VIEWSIZE;
                y_high = homeBase.getLocatedOn().getId().getY() + VIEWSIZE + 1;
            } else {
                x_low = 0;
                y_low = 0;
                x_high = 2 * VIEWSIZE + 1;
                y_high = 2 * VIEWSIZE + 1;
            }
        }

        ArrayList<ArrayList<TileOverviewDTO>> displayedTiles = new ArrayList<ArrayList<TileOverviewDTO>>();

        // get all visible tiles
        Tile exampleTile = new Tile(map, x_low, y_low);
        for (int y = y_low; y <= y_high; y++) {
            ArrayList<TileOverviewDTO> currentRow = new ArrayList<TileOverviewDTO>();
            for (int x = x_low; x <= x_high; x++) {
                Collection<Tile> result = tileDAO.queryByExample(exampleTile);
                if (result != null && result.size() == 1) {
                    Tile tile = result.iterator().next();

                    TileOverviewDTO dto = new TileOverviewDTO(tile);

                    dto.setSpecialResource(tile.getSpecial());

                    // TODO: TOOLTIP Java Script???

                    // create info
                    StringBuilder sb = new StringBuilder();

                    // check if base
                    if (tile.getBase() != null) {
                        if (tile.getBase().getOwner() != player) {
                            sb.append("Enemy base owned by ");
                            sb.append(tile.getBase().getOwner());
                        } else {
                            sb.append("Your base!");
                        }

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

                    dto.setInfo(sb.toString());

                    if (tile.getBase() != null)
                        dto.setHasBase(true);
                    else
                        dto.setHasBase(false);

                    if (checkForEnemyTerritory(tile))
                        dto.setEnemyTerritory(true);
                    else
                        dto.setEnemyTerritory(false);

                    currentRow.add(dto);
                }

            }
        }

        model.addAttribute("tiles", displayedTiles);

        return "overview";
    }

    private boolean checkForEnemyTerritory(Tile tile) {
        if (tile.getBase() != null && tile.getBase().getOwner() != player)
            return true;
        if (!tile.getTroops().isEmpty()
                && tile.getTroops().iterator().next().getOwner() != player)
            return true;

        return false;
    }

    @RequestMapping(value = "/tile/", method = RequestMethod.GET)
    public String getTileDetails(@RequestParam("x") int x,
                                 @RequestParam("y") int y, Model model) {
        Tile.Id id = new Tile.Id(map.getId(), x, y);
        Tile tile = tileDAO.get(id);

        model.addAttribute("specialResource", tile.getSpecial());

        // troops
        ArrayList<TroopDTO> troopList = new ArrayList<TroopDTO>();

        for (Troop troop : tile.getTroops()) {
            troopList.add(new TroopDTO(troop));
        }

        model.addAttribute("troops", troopList);

        // tile info
        TileOverviewDTO tileInfo = new TileOverviewDTO(tile);

        if (tile.getBase() != null)
            tileInfo.setHasBase(true);
        else
            tileInfo.setHasBase(false);

        if (checkForEnemyTerritory(tile))
            tileInfo.setEnemyTerritory(true);
        else
            tileInfo.setEnemyTerritory(false);

        model.addAttribute("tileInfo", tileInfo);

        return "";
    }

}
