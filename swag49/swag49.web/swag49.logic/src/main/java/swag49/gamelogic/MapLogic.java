package swag49.gamelogic;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import swag49.dao.DataAccessObject;
import swag49.gamelogic.exceptions.NotEnoughMoneyException;
import swag49.model.*;
import swag49.model.Map;
import swag49.model.helper.ResourceValueHelper;
import swag49.transfer.model.MessageDTO;
import swag49.util.Log;

import java.util.*;

@Component
@SuppressWarnings({"UnusedAssignment", "UnusedDeclaration"})
public class MapLogic {

    @Autowired
    @Qualifier("mapDAO")
    private DataAccessObject<Map, Long> mapDAO;

    @Autowired
    @Qualifier("baseDAO")
    private DataAccessObject<Base, Long> baseDAO;

    @Autowired
    @Qualifier("squareDAO")
    private DataAccessObject<Square, Square.Id> squareDAO;

    @Autowired
    @Qualifier("playerDAO")
    private DataAccessObject<Player, Long> playerDAO;

    @Autowired
    @Qualifier("troopDAO")
    private DataAccessObject<Troop, Long> troopDAO;

    @Autowired
    @Qualifier("troopActionDAO")
    private DataAccessObject<TroopAction, Long> troopActionDAO;

    @Autowired
    @Qualifier("tileDAO")
    private DataAccessObject<Tile, Tile.Id> tileDAO;

    @Autowired
    @Qualifier("buildingDAO")
    private DataAccessObject<Building, Square.Id> buildingDAO;

    @Autowired
    @Qualifier("buildingLevelDAO")
    private DataAccessObject<BuildingLevel, BuildingLevel.Id> buildingLevelDAO;

    @Autowired
    @Qualifier("troopUpgradeActionDAO")
    private DataAccessObject<TroopUpgradeAction, Long> troopUpgradeActionDAO;


    @Autowired
    @Qualifier("troopLevelDAO")
    private DataAccessObject<TroopLevel, TroopLevel.Id> troopLevelDAO;

    @Autowired
    @Qualifier("troopBuildActionDAO")
    private DataAccessObject<TroopBuildAction, Long> troopBuildActionDAO;

    @Autowired
    @Qualifier("buildActionDAO")
    private DataAccessObject<BuildAction, Long> buildActionDao;

    @Autowired(required = false)
    private RestTemplate restTemplate;


    private static final int RANDOMTRIES = 1000;

    private static final int START_AMOUNT_WOOD = 1000;
    private static final int START_AMOUNT_STONE = 1000;
    private static final int START_AMOUNT_GOLD = 1000;
    private static final int START_AMOUNT_CROPS = 1000;

    private static final int DISTANCEFACTOR = 60000;

    private static final int NO_SQUARES = 10;
    private static final int INCOME_WOOD = 15;
    private static final int INCOME_STONE = 15;
    private static final int INCOME_GOLD = 15;
    private static final int INCOME_CROPS = 15;

    @Log
    private static Logger logger;
    private static final String SUBJECT_FIGHTRESULT = "[Fight Result]";
    private Long systemUserId;
    private static final String SUBJECT_BUILDBASE = "[Base founded]";
    private static final double RESOURCEFACTOR = 1.2;

    @Transactional("swag49.map")
    public Base initializePlayer(Map map, Player player) {
        map = mapDAO.get(map.getId());
        Tile homeBaseTile = findHomeBaseLocation(map);


        Base homeBase = createBase(homeBaseTile, player);
        homeBase.setHome(true);

        baseDAO.update(homeBase);
        tileDAO.update(homeBaseTile);

        player.setIncome(homeBase.getResourceProduction());
        player.setUpkeep(new ResourceValue());
        player.setResources(
                new ResourceValue(START_AMOUNT_WOOD, START_AMOUNT_CROPS, START_AMOUNT_GOLD, START_AMOUNT_STONE));

        player = playerDAO.update(player);

        return homeBase;

    }


    @Transactional("swag49.map")
    public void upgradeTroop(Player player, Troop troop, TroopLevel troopLevel) throws Exception {

        //calculate cost
        ResourceValue cost = new ResourceValue(troopLevel.getBuildCosts());

        //check if user can afford the troops
        if (ResourceValueHelper.geq(player.getResources(), cost)) {

            ResourceValueHelper.remove(player.getResources(), cost);
            Long duration = troopLevel.getUpgradeDuration();

            playerDAO.update(player);

            troop.setActive(Boolean.FALSE);
            troopDAO.update(troop);

            TroopUpgradeAction action = new TroopUpgradeAction(player, troop, troopLevel, duration);

            action.setTarget(troop.getPosition());
            action.setIsAbortable(Boolean.TRUE);
            action.setStartDate(new Date());

            action = troopUpgradeActionDAO.create(action);
        } else {
            throw new NotEnoughMoneyException();
        }
    }

    @Transactional("swag49.map")
    public void handleAction(TroopUpgradeAction action) {
        action = troopUpgradeActionDAO.get(action.getId());
        Troop troop = action.getTroop();

        // get current level
        TroopLevel currentLevel = troop.getIsOfLevel();


        TroopLevel nextLevel = action.getTroopLevel();

        if (nextLevel != null) {
            troop.setIsOfLevel(nextLevel);
            troop.setActive(Boolean.TRUE);

            troopDAO.update(troop);

            //update upkeep
            ResourceValueHelper.remove(action.getPlayer().getUpkeep(), currentLevel.getUpkeepCosts());
            ResourceValueHelper.add(action.getPlayer().getUpkeep(), nextLevel.getUpkeepCosts());

            playerDAO.update(action.getPlayer());
        }
    }

    @Transactional("swag49.map")
    public void buildTroop(Player player, TroopType type, TroopLevel level, Tile baseTile, int amount)
            throws NotEnoughMoneyException {

        //calculate cost
        ResourceValue cost = new ResourceValue(level.getBuildCosts());

        cost.setAmount_crops(cost.getAmount_crops() * amount);
        cost.setAmount_gold(cost.getAmount_gold() * amount);
        cost.setAmount_wood(cost.getAmount_wood() * amount);
        cost.setAmount_stone(cost.getAmount_stone() * amount);

        //check if user can afford the troops
        if (player.getResources().getAmount_crops() >= cost.getAmount_crops() &&
                player.getResources().getAmount_gold() >= cost.getAmount_gold() &&
                player.getResources().getAmount_wood() >= cost.getAmount_wood() &&
                player.getResources().getAmount_stone() >= cost.getAmount_stone()) {

            ResourceValueHelper.remove(player.getResources(), cost);
            Long duration = level.getUpgradeDuration();

            playerDAO.update(player);

            TroopBuildAction action = new TroopBuildAction(player, baseTile, type, level, amount, duration);
            action.setStartDate(new Date());
            action.setIsAbortable(Boolean.TRUE);

            action = troopBuildActionDAO.create(action);
        } else {
            throw new NotEnoughMoneyException();
        }
    }

    @Transactional("swag49.map")
    public void handleAction(TroopBuildAction action) {
        action = troopBuildActionDAO.get(action.getId());
        TroopType type = action.getTroopType();

        Tile tile = action.getTarget();

        //check if a base on that tile exists and if that base is still under the control of that player
        Base base = tile.getBase();
        if (base != null && base.getOwner().getId().equals(action.getPlayer().getId())) {


            for (int i = 0; i < action.getAmount(); i++) {
                //ad troops to that base
                Troop troop = new Troop(type, action.getTroopLevel(), tile, action.getPlayer());
                troop.setActive(Boolean.TRUE);
                troop = troopDAO.create(troop);

                //add troops to tile
                tile.getTroops().add(troop);

                //update upkeep
                ResourceValueHelper.add(action.getPlayer().getUpkeep(), action.getTroopLevel().getUpkeepCosts());

                playerDAO.update(action.getPlayer());
            }

            tileDAO.update(tile);
        }
    }


    @Transactional("swag49.map")
    public void build(Square square, BuildingType type) throws Exception {
        if (square.getBuilding() != null) {
            //    throw new Exception("Square not empty");
            return;
        } else {
            Player player = square.getBase().getOwner();
            //get first level
            BuildingLevel.Id id = new BuildingLevel.Id(1, type.getId());
            BuildingLevel levelOne = buildingLevelDAO.get(id);

            //check if the player has enough resources

            if (!ResourceValueHelper.geq(player.getResources(), levelOne.getBuildCosts())) {
                throw new NotEnoughMoneyException();
            }

            ResourceValueHelper.remove(player.getResources(), levelOne.getBuildCosts());

            playerDAO.update(player);

            Building constructionYard = new Building(square);

            square.setBuilding(constructionYard);

            constructionYard.setType(type);

            // get zero-level
            id.setLevel(0);
            BuildingLevel level = buildingLevelDAO.get(id);

            constructionYard.setIsOfLevel(level);
            constructionYard.setActive(Boolean.FALSE);

            constructionYard = buildingDAO.create(constructionYard);

            squareDAO.update(square);

            logger.info("CREATE BUILD ACTION");
            // create BuildAction
            BuildAction action = new BuildAction();
            action.setConcerns(constructionYard);
            action.setTarget(square.getBase().getLocatedOn());
            action.setDuration(levelOne.getUpgradeDuration());
            action.setStartDate(new Date());
            action.setPlayer(square.getBase().getOwner());
            action.setIsAbortable(true);


            action = buildActionDao.create(action);
        }
    }

    @Transactional("swag49.map")
    public void upgradeBuilding(Building building) throws Exception {

        Player player = building.getSquare().getBase().getOwner();

        //get next level
        BuildingLevel.Id id = new BuildingLevel.Id(building.getIsOfLevel().getLevel() + 1, building.getType().getId());
        BuildingLevel levelOne = buildingLevelDAO.get(id);

        if (levelOne == null) {
            throw new Exception("no next level found");
        }

        //check if the player has enough resources

        if (!ResourceValueHelper.geq(player.getResources(), levelOne.getBuildCosts())) {
            throw new NotEnoughMoneyException();
        }

        //reduce resources
        ResourceValueHelper.remove(player.getResources(), levelOne.getBuildCosts());

        playerDAO.update(player);


        building.setActive(Boolean.FALSE);
        building = buildingDAO.update(building);

        // create BuildAction
        BuildAction action = new BuildAction();
        action.setConcerns(building);
        action.setTarget(building.getSquare().getBase().getLocatedOn());
        action.setDuration(levelOne.getUpgradeDuration());
        action.setStartDate(new Date());
        action.setPlayer(building.getSquare().getBase().getOwner());
        action.setIsAbortable(true);


        action = buildActionDao.create(action);
    }

    @Transactional("swag49.map")
    public void sendTroops(Tile target, Set<Troop> troops, boolean shouldFoundBase) throws Exception {

        if (troops.isEmpty())
            throw new Exception("Troops must not be empty");

        //TODO: unsauber, aber was solls
        Tile source = troops.iterator().next().getPosition();

        // long timeNeeded = calculateTravelTime(source, target, troops);
        long timeNeeded = multipleStartPlacesTravelTime(troops, target);

        TroopAction action = new TroopAction();

        action.setConcerns(troops);
        action.setDuration(timeNeeded);
        action.setPlayer(troops.iterator().next().getOwner());
        action.setStartDate(new Date());
        action.setIsAbortable(true);
        action.setSource(source);
        action.setTarget(target);

        action.setShouldFoundBase(Boolean.valueOf(shouldFoundBase));

        //remove troop location of troops
        for (Troop troop : troops) {

            troop.setPosition(null);
            troopDAO.update(troop);

        }

        action = troopActionDAO.create(action);
    }

    @Transactional("swag49.map")
    public void sendTroops(Tile target, Set<Troop> troops) throws Exception {
        sendTroops(target, troops, false);
    }

    @Transactional("swag49.map")
    private long multipleStartPlacesTravelTime(Collection<Troop> troops,
                                               Tile destination) {

        // sort troops according to start position
        HashMap<Tile, ArrayList<Troop>> map = new HashMap<Tile, ArrayList<Troop>>();
        for (Troop troop : troops) {
            if (map.containsKey(troop.getPosition())) {
                map.get(troop.getPosition()).add(troop);
            } else {
                ArrayList<Troop> list = new ArrayList<Troop>();
                list.add(troop);
                map.put(troop.getPosition(), list);
            }
        }

        long timeNeeded = 0;
        for (Tile tile : map.keySet()) {
            timeNeeded = Math.max(timeNeeded,
                    calculateTravelTime(tile, destination, map.get(tile)));
        }

        return timeNeeded;
    }

    /**
     * Calculates the time needed for a group of troops to go from a start to
     * destination tile
     *
     * @param start       start
     * @param destination destination
     * @param troops      troops
     * @return long
     */
    @Transactional("swag49.map")
    private long calculateTravelTime(Tile start, Tile destination,
                                     Collection<Troop> troops) {

        // get slowest troops
        int minSpeed = Integer.MAX_VALUE;

        for (Troop troop : troops) {
            minSpeed = Math.min(minSpeed, troop.getIsOfLevel().getSpeed());
        }

        // calculate the distance (number of tiles to pass)
        int distance = Math.abs(start.getId().getX()
                - destination.getId().getX())
                + Math.abs(start.getId().getY() - destination.getId().getY());

        return (distance * DISTANCEFACTOR) / minSpeed;
    }

    @Transactional("swag49.map")
    private Tile findHomeBaseLocation(Map map) {
        //try random
        Random rnd = new Random(0);

        ArrayList<Tile> tiles = Lists.newArrayList(map.getConsistsOf());
        int tileNo = tiles.size();
        for (int i = 0; i < RANDOMTRIES; i++) {
            int j = rnd.nextInt(tileNo);
            Tile tile = tiles.get(j);

            if (tile.getBase() != null || tile.getSpecial() != ResourceType.NONE)
                continue;
            else {
                return tile;
            }
        }

        //check all sequentially
        for (Tile tile : tiles) {
            if (tile.getBase() != null || tile.getSpecial() != ResourceType.NONE)
                continue;
            else {
                return tile;
            }
        }
        return null;
    }


    @Transactional("swag49.map")
    public Base createBase(Tile tile, Player owner) {
        Base base = new Base(tile);


        base.setLocatedOn(tile);
        base.setHome(false);
        base.setOwner(owner);
        base = baseDAO.create(base);

        Set<Square> squares = new HashSet<Square>();
        for (int i = 0; i < NO_SQUARES; i++) {

            squares.add(squareDAO.create(new Square(base, i)));
        }

        base.setConsistsOf(squares);

        ResourceValue resourceProduction = new ResourceValue(INCOME_WOOD, INCOME_CROPS, INCOME_GOLD, INCOME_STONE);
        base.setResourceProduction(resourceProduction);
        base = baseDAO.update(base);

        tile.setBase(base);
        tileDAO.update(tile);
        ResourceValueHelper.add(owner.getResources(), resourceProduction);

        playerDAO.update(owner);

        return base;
    }


    @Transactional("swag49.map")
    public void handleAction(BuildAction action) {
        action = buildActionDao.get(action.getId());
        Building building = action.getConcerns();

        // get current level
        BuildingLevel currentLevel = building.getIsOfLevel();

        // get next level
        // TODO: getNextLevel-Funktion??
        BuildingLevel.Id id = new BuildingLevel.Id(currentLevel.getId()
                .getLevel() + 1, currentLevel.getId().getBuildingTypeId());
        BuildingLevel nextLevel = buildingLevelDAO.get(id);

        if (nextLevel != null) {
            building.setIsOfLevel(nextLevel);
            building.setActive(Boolean.TRUE);
            buildingDAO.update(building);

            Player player = action.getPlayer();
            // update upkeep
            ResourceValueHelper.remove(player.getUpkeep(), currentLevel.getUpkeepCosts());
            ResourceValueHelper.add(player.getUpkeep(), nextLevel.getUpkeepCosts());

            // update income
            if (building.getSquare().getBase().getLocatedOn().getSpecial() != ResourceType.NONE) {
                ResourceValueHelper.remove(player.getIncome(), specialResourceFactor(currentLevel.getResourceProduction(), building.getSquare().getBase().getLocatedOn().getSpecial()));
                ResourceValueHelper.add(player.getIncome(), specialResourceFactor(nextLevel.getResourceProduction(), building.getSquare().getBase().getLocatedOn().getSpecial()));
            } else {
                ResourceValueHelper.remove(player.getIncome(), currentLevel.getResourceProduction());
                ResourceValueHelper.add(player.getIncome(), nextLevel.getResourceProduction());
            }

            playerDAO.update(player);
        }
    }

    private ResourceValue specialResourceFactor(ResourceValue value, ResourceType type) {
        ResourceValue result = new ResourceValue(value);
        switch (type) {
            case WOOD:
                result.setAmount_wood(Integer.valueOf((int) (result.getAmount_wood() * RESOURCEFACTOR)));
            case GOLD:
                result.setAmount_gold(Integer.valueOf((int) (result.getAmount_gold() * RESOURCEFACTOR)));
            case STONE:

                result.setAmount_stone(Integer.valueOf((int) (result.getAmount_stone() * RESOURCEFACTOR)));
            case CROPS:
                result.setAmount_crops(Integer.valueOf((int) (result.getAmount_crops() * RESOURCEFACTOR)));
            default:
            case NONE:
                break;
        }
        return result;
    }


    @Transactional("swag49.map")
    public void handleAction(TroopAction action) {
        action = troopActionDAO.get(action.getId());
        Tile tile = action.getTarget();

        boolean canBuildBase = false;


        for (Troop troop : action.getConcerns()) {
            if (troop.getType().getCanFoundBase()) {
                canBuildBase = true;
                break;
            }
        }


        boolean enemyTerritory = false;
        Player otherPlayer = null;

        if (tile.getBase() != null && !tile.getBase().getOwner().equals(action.getPlayer())) {
            enemyTerritory = true;
            otherPlayer = tile.getBase().getOwner();
        }

        // check if other troops are on that tile
        List<Troop> defenders = new ArrayList<Troop>(tile.getTroops());

        if (!defenders.isEmpty() && !defenders.iterator().next().getOwner().equals(action.getPlayer())) {
            enemyTerritory = true;
            otherPlayer = defenders.iterator().next().getOwner();
        }

        if (!enemyTerritory) {
            tile.getTroops().addAll(action.getConcerns());
            for (Troop troop : action.getConcerns()) {
                troop.setPosition(tile);
                troopDAO.update(troop);
            }

            tileDAO.update(tile);


            if (canBuildBase && action.getShouldFoundBase()) {
                if (tile.getBase() != null) {
                    //write errormsg
                    sendMessage(action.getPlayer(), action.getPlayer(), SUBJECT_BUILDBASE,
                            "Your plans to build a base at (" + tile.getId().getX() + "," + tile.getId().getY() +
                                    ") couldn't be fulfilled - there is already a base on that tile.");
                } else {
                    Base base = createBase(tile, action.getPlayer());
                    //write msg to player
                    sendMessage(action.getPlayer(), action.getPlayer(), SUBJECT_BUILDBASE,
                            "You have successfully found a new base at tile (" + tile.getId().getX() + "," +
                                    tile.getId().getY() + ").");
                }
            }
        } else {

            if (defenders.isEmpty()) {
                if (canBuildBase && !tile.getBase().isHome()) {
                    //take base
                    Base base = tile.getBase();
                    base.setOwner(action.getPlayer());

                    base = baseDAO.update(base);
                    action.getPlayer().getOwns().add(base);
                    playerDAO.update(action.getPlayer());

                    otherPlayer.getOwns().remove(base);
                    playerDAO.update(otherPlayer);

                    tile.getTroops().addAll(action.getConcerns());
                    for (Troop troop : action.getConcerns()) {
                        troop.setPosition(tile);
                        troopDAO.update(troop);
                    }

                    tileDAO.update(tile);

                    //write msg to both players
                    sendMessage(action.getPlayer(), action.getPlayer(), SUBJECT_BUILDBASE,
                            "You have captured a new base as  (" + tile.getId().getX() + "," + tile.getId().getY() +
                                    ").");

                    sendMessage(otherPlayer, otherPlayer, SUBJECT_BUILDBASE,
                            "Player " + action.getPlayer().getId() + " has taken your base at  (" + tile.getId().getX() + "," + tile.getId().getY() +
                                    ").");
                } else {
                    //rob base
                    ResourceValue booty = calculateBooty(tile.getBase().getOwner(), action.getConcerns());

                    ResourceValueHelper.add(action.getPlayer().getResources(), booty);
                    ResourceValueHelper.remove(otherPlayer.getResources(), booty);
                    playerDAO.update(action.getPlayer());
                    playerDAO.update(otherPlayer);

                    //write ms to both players
                    sendMessage(action.getPlayer(), action.getPlayer(), SUBJECT_FIGHTRESULT,
                            "You robed the base of player " + otherPlayer.getId() + " at tile (" +
                                    tile.getId().getX() + "," + tile.getId().getY() +
                                    "). \n Your troops have stolen something and are now on their way home.");
                    sendMessage(otherPlayer, otherPlayer, SUBJECT_FIGHTRESULT,
                            "Your base at tile (\" + tile.getId().getX() + \",\" + tile.getId().getY() + \") has been attacked by  " +
                                    action.getPlayer() +
                                    " . \n  Unfortunately, the attacking troops robed your base and stole some resources from you.");


                    //send attackers home
                    sendHome(action.getConcerns(), action.getSource(), tile);
                }
            } else {
                // oho - enemies....FIGHT!!!!
                List<Troop> attackers = new ArrayList<Troop>(action.getConcerns());

                boolean attackerWin = calculateFight(action.getPlayer(), otherPlayer,
                        attackers, defenders, tile);

                if (attackerWin) {
                    if (tile.getBase() != null) {
                        //rob base
                        ResourceValue booty = calculateBooty(otherPlayer, attackers);

                        ResourceValueHelper.add(action.getPlayer().getResources(), booty);
                        ResourceValueHelper.remove(otherPlayer.getResources(), booty);

                        playerDAO.update(action.getPlayer());
                        playerDAO.update(otherPlayer);


                        if (canBuildBase && !tile.getBase().isHome()) {
                            Base base = tile.getBase();

                            base.setOwner(action.getPlayer());
                            action.getPlayer().getOwns().add(base);
                            otherPlayer.getOwns().remove(base);

                            base = baseDAO.update(base);
                            playerDAO.update(action.getPlayer());
                            playerDAO.update(otherPlayer);

                            // attacking troops stay on the tile
                            tile.getTroops().addAll(attackers);
                            for (Troop troop : attackers) {
                                troop.setPosition(tile);
                                troopDAO.update(troop);
                            }

                            tileDAO.update(tile);


                            sendMessage(action.getPlayer(), action.getPlayer(), SUBJECT_BUILDBASE,
                                    "You have captured a new base as  (" + tile.getId().getX() + "," + tile.getId().getY() +
                                            ").");

                            sendMessage(otherPlayer, otherPlayer, SUBJECT_BUILDBASE,
                                    "Player " + action.getPlayer().getId() + " has taken your base at  (" + tile.getId().getX() + "," + tile.getId().getY() +
                                            ").");
                        } else {
                            sendHome(attackers, action.getSource(), tile);
                        }
                    } else if (canBuildBase) {
                        Base base = createBase(tile, action.getPlayer());

                        //write msg to player
                        sendMessage(action.getPlayer(), action.getPlayer(), SUBJECT_BUILDBASE,
                                "You have successfully found a new base at tile (" + tile.getId().getX() + "," +
                                        tile.getId().getY() + ").");


                        tile.getTroops().addAll(attackers);
                        for (Troop troop : attackers) {
                            troop.setPosition(tile);
                            troopDAO.update(troop);
                        }

                        tileDAO.update(tile);
                    } else {
                        //stay

                        tile.getTroops().addAll(attackers);
                        for (Troop troop : attackers) {
                            troop.setPosition(tile);
                            troopDAO.update(troop);
                        }

                        tileDAO.update(tile);
                    }
                } else {
                    if (!attackers.isEmpty()) {
                        // send attacking army home to mom
                        sendHome(attackers, action.getSource(), tile);
                    }
                }
            }
        }
    }

    @Transactional("swag49.map")
    private void sendHome(Collection<Troop> attackers, Tile destination, Tile source) {
        TroopAction homeAction = new TroopAction();
        homeAction.getConcerns().addAll(attackers);
        homeAction.setShouldFoundBase(Boolean.FALSE);
        homeAction.setTarget(destination);
        homeAction.setIsAbortable(Boolean.FALSE);
        homeAction.setSource(source);
        homeAction.setStartDate(new Date());
        homeAction.setDuration(calculateTravelTime(source, destination, attackers));
        homeAction.setPlayer(attackers.iterator().next().getOwner());

        homeAction = troopActionDAO.create(homeAction);
    }

    @Transactional("swag49.map")
    private boolean calculateFight(Player attackers_owner, Player defenders_owner,
                                   List<Troop> tmpAttackers, List<Troop> tmpDefenders, Tile tile) {


        Collection<Troop> deadTroops_attacker = null;
        Collection<Troop> deadTroops_defenders = null;

        do {
            // calculate victims
            deadTroops_defenders = calculateAttack(tmpAttackers, tmpDefenders);
            deadTroops_attacker = calculateAttack(tmpDefenders, tmpAttackers);

            // remove victims of the sets
            if (!deadTroops_defenders.isEmpty()) {
                for (Troop dead : deadTroops_defenders) {
                    Iterator<Troop> iter = tmpDefenders.iterator();
                    while (iter.hasNext()) {
                        Troop t = iter.next();
                        if (t.getId().equals(dead.getId())) {
                            iter.remove();
                        }
                    }
                }
            }


            if (!deadTroops_attacker.isEmpty()) {
                for (Troop dead : deadTroops_attacker) {

                    Iterator<Troop> iter = tmpAttackers.iterator();
                    while (iter.hasNext()) {
                        Troop t = iter.next();
                        if (t.getId().equals(dead.getId())) {
                            iter.remove();
                        }
                    }
                    /*         for (Troop att : tmpAttackers) {
                     if (dead.getId().equals(att.getId())) {
                         tmpAttackers.remove(att);
                         break;
                     }
                 }   */
                }
            }

            // remove victims in persistence layer
            for (Troop troop : deadTroops_attacker) {
                //update upkeep
                ResourceValueHelper.remove(troop.getOwner().getUpkeep(), troop.getIsOfLevel().getUpkeepCosts());
                playerDAO.update(troop.getOwner());

                troopDAO.delete(troop);

            }

            for (Troop troop : deadTroops_defenders) {
                //update upkeep
                ResourceValueHelper.remove(troop.getOwner().getUpkeep(), troop.getIsOfLevel().getUpkeepCosts());
                playerDAO.update(troop.getOwner());

                tile.getTroops().remove(troop);

                troopDAO.delete(troop);
            }

        } while (!deadTroops_attacker.isEmpty()
                && !deadTroops_defenders.isEmpty());

        if (tmpAttackers.size() > 0 && tmpDefenders.isEmpty()) {
            // attacker win
            // write MSG to both player about the result of the fight
            sendMessage(attackers_owner, attackers_owner, SUBJECT_FIGHTRESULT,
                    "Your army attacked the forces of player " + defenders_owner.getId() + " at tile (" +
                            tile.getId().getX() + "," + tile.getId().getY() +
                            "). \nYour troops fought courageously  and in the end, they killed all defending troops.");
            sendMessage(defenders_owner, defenders_owner, SUBJECT_FIGHTRESULT,
                    "Your army has been attacked by the forces of player " + attackers_owner.getId() + " at tile (" +
                            tile.getId().getX() + "," + tile.getId().getY() +
                            "). \n  Unfortunately, the defending troops where too strong and all your troops have been destroyed.");


            return true;
        } else if (tmpDefenders.size() > 0 && tmpAttackers.isEmpty()) {
            // defenders win
            // write MSG to both player about the result of the fight
            sendMessage(attackers_owner, attackers_owner, SUBJECT_FIGHTRESULT,
                    "Your army attacked the forces of player " + defenders_owner.getId() + " at tile (" +
                            tile.getId().getX() + "," + tile.getId().getY() +
                            "). \n Unfortunately, the defending troops where too strong and all your troops have been destroyed.");
            sendMessage(defenders_owner, defenders_owner, SUBJECT_FIGHTRESULT,
                    "Your army has been attacked by the forces of player " + attackers_owner.getId() + " at tile (" +
                            tile.getId().getX() + "," + tile.getId().getY() +
                            "). \n Your troops fought courageously  and in the end, they killed all attacking troops. ");


            return false;
        } else {
            // draw
            if (tmpDefenders.size() == 0 && tmpAttackers.size() == 0) {
                // war - what is it good for?

                // write MSG to both player that they lost their army
                sendMessage(attackers_owner, attackers_owner, SUBJECT_FIGHTRESULT,
                        "Your army attacked the forces of player " + defenders_owner.getId() + " at tile (" +
                                tile.getId().getX() + "," + tile.getId().getY() +
                                "). \n Your troops and the enemy troops have been defeated!");
                sendMessage(defenders_owner, defenders_owner, SUBJECT_FIGHTRESULT,
                        "Your army has been attacked by the forces of player " + attackers_owner.getId() +
                                " at tile (" + tile.getId().getX() + "," + tile.getId().getY() +
                                "). \n Your troops and the enemy troops have been defeated!");

                return false;
            } else {
                // non-mexican standoff

                // write MSG to both player about the result of the fight
                sendMessage(attackers_owner, attackers_owner, SUBJECT_FIGHTRESULT,
                        "Your army attacked the forces of player " + defenders_owner.getId() + " at tile (" +
                                tile.getId().getX() + "," + tile.getId().getY() +
                                "). At the end, some of the fighting troops survived, each unable to kill any other troops. So your troops have been decided to come home...");
                sendMessage(defenders_owner, defenders_owner, SUBJECT_FIGHTRESULT,
                        "Your army has been attacked by the forces of player " + attackers_owner.getId() +
                                " at tile (" + tile.getId().getX() + "," + tile.getId().getY() + ").");

                return false;
            }
        }
    }

    private ResourceValue calculateBooty(Player defenders_owner,
                                         Collection<Troop> attackers) {

        ResourceValue booty = new ResourceValue();
        // determine maximal resources by dividing players resources by
        // the number of Bases
        int noCities = defenders_owner.getOwns().size();
        ResourceValue maxSteal = new ResourceValue(defenders_owner
                .getResources().getAmount_wood() / noCities, defenders_owner
                .getResources().getAmount_crops() / noCities, defenders_owner
                .getResources().getAmount_gold() / noCities, defenders_owner
                .getResources().getAmount_stone() / noCities);

        int cargo_total = 0;
        for (Troop troop : attackers) {
            cargo_total += troop.getIsOfLevel().getCargo_capacity();
        }

        int i = getNoNonzeroResources(maxSteal);
        while (cargo_total > 0 && i > 0) {
            // steal c amount of crops
            int c = Math.min(maxSteal.getAmount_crops(), cargo_total / i);

            booty.setAmount_crops(booty.getAmount_crops() + c);
            maxSteal.setAmount_crops(maxSteal.getAmount_crops() - c);

            // steal s amount of stone
            int s = Math.min(maxSteal.getAmount_stone(), cargo_total / i);
            booty.setAmount_stone(booty.getAmount_stone() + s);
            maxSteal.setAmount_stone(maxSteal.getAmount_stone() - s);

            // steal w amount of wood
            int w = Math.min(maxSteal.getAmount_wood(), cargo_total / i);
            booty.setAmount_wood(booty.getAmount_wood() + w);
            maxSteal.setAmount_wood(maxSteal.getAmount_wood() - w);

            // steal g amount of gold
            int g = Math.min(maxSteal.getAmount_gold(), cargo_total / i);
            booty.setAmount_gold(booty.getAmount_gold() + g);
            maxSteal.setAmount_gold(maxSteal.getAmount_gold() - g);

            cargo_total -= (c + s + w + g);

            i = getNoNonzeroResources(maxSteal);
        }

        return booty;
    }

    private int getNoNonzeroResources(ResourceValue value) {
        int i = 0;

        if (value.getAmount_crops() > 0)
            i++;

        if (value.getAmount_gold() > 0)
            i++;

        if (value.getAmount_stone() > 0)
            i++;

        if (value.getAmount_wood() > 0)
            i++;
        return i;
    }

    private Collection<Troop> calculateAttack(List<Troop> attackers,
                                              List<Troop> defenders) {

        List<Troop> deadTroops = new ArrayList<Troop>();
        int strength_attacker = 0;

        for (Troop troop : attackers) {
            strength_attacker += troop.getIsOfLevel().getStrength();
        }
        Iterator<Troop> iterator = defenders.iterator();

        while (strength_attacker > 0 && iterator.hasNext()) {
            Troop troop = iterator.next();
            if (strength_attacker >= troop.getIsOfLevel().getDefense()) {
                deadTroops.add(troop);
                strength_attacker -= troop.getIsOfLevel().getDefense();
            }
        }

        return deadTroops;
    }

    private void sendMessage(Player sender, Player receiver, String subject, String content) {
        try {
            MessageDTO message = new MessageDTO(null, subject, content, sender.getUserId(), null, receiver.getUserId(),
                    null, new Date(), new Date(), sender.getPlays().getUrl());

            restTemplate.put("http://localhost:8080/messaging/send", message);
        } catch (Exception e) {
            logger.error("Error during messaging", e);
        }
    }

}
