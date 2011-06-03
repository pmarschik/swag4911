package swag49.gamelogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import swag49.dao.DataAccessObject;
import swag49.model.*;

import java.util.*;

public class BaseControl {
    // TODO: distancefactor
    private static final int DISTANCEFACTOR = 600;

    private Base base;

    @Autowired
    @Qualifier("buildingDao")
    private DataAccessObject<Building> buildingDao;

    @Autowired
    @Qualifier("buildingLevelDao")
    private DataAccessObject<BuildingLevel> buildingLevelDao;

    @Autowired
    @Qualifier("buildActionDao")
    private DataAccessObject<BuildAction> buildActionDao;

    @Autowired
    @Qualifier("troopoActionDao")
    private DataAccessObject<TroopAction> troopActionDao;

    @Autowired
    @Qualifier("troopDao")
    private DataAccessObject<Troop> troopDao;

    public ResourceValue getReourceProduction() {
        return base.getResourceProduction();
    }

    public Collection<Square> getEmptySquares() {
        List<Square> result = new ArrayList<Square>();

        for (Square s : base.getConsistsOf()) {
            if (s.getBuilding() == null) {
                result.add(s);
            }
        }

        return result;
    }

    public void build(Square square, BuildingType type) throws Exception {
        if (square.getBuilding() != null) {
            throw new Exception("Square not empty");
        } else {
            Building constructionYard = new Building(square);

            constructionYard.setType(type);

            // get zero-level
            BuildingLevel.Id id = new BuildingLevel.Id(0, type.getId());
            BuildingLevel level = buildingLevelDao.get(id);

            constructionYard.setIsOfLevel(level);

            constructionYard = buildingDao.create(constructionYard);

            // create BuildAction
            BuildAction action = new BuildAction();
            action.setConcerns(constructionYard);
            action.setTarget(square.getBase().getLocatedOn());
            action.setDuration(level.getUpgradeDuration());
            action.setStartDate(new Date());
            action.setPlayer(square.getBase().getOwner());
            action.setIsAbortable(true);


            action = buildActionDao.create(action);
        }
    }

    public void sendTroops(Tile source, Tile target, Set<Troop> troops) throws Exception {

        if (troops.isEmpty())
            throw new Exception("Troops must not be empty");

        long timeNeeded = calculateTravelTime(source, target, troops);
        //  multipleStartPlacesTravelTime(troops, target);

        TroopAction action = new TroopAction();

        action.setConcerns(troops);
        action.setDuration(Long.valueOf(timeNeeded));
        action.setPlayer(troops.iterator().next().getOwner());
        action.setStartDate(new Date());
        action.setIsAbortable(true);
        action.setSource(source);
        action.setTarget(target);

        //remove troop location of troops
        for (Troop troop : troops) {
            troop.setPosition(null);
            troopDao.update(troop);
        }

        action = troopActionDao.create(action);
    }

    private long multipleStartPlacesTravelTime(Collection<Troop> troops,
                                               Tile destination) {

        // sort troops according to start position
        HashMap<Tile, ArrayList<Troop>> map = new HashMap<Tile, ArrayList<Troop>>();
        for (Troop troop : troops) {
            if (!map.containsKey(troop.getPosition())) {
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
     * @param start
     * @param destination
     * @param troops
     * @return
     */
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

        long timeNeeded = (distance * DISTANCEFACTOR) / minSpeed;

        return timeNeeded;
    }
}
