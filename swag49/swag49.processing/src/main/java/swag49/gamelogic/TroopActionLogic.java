package swag49.gamelogic;

import gamelogic.MapLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import swag49.dao.DataAccessObject;
import swag49.model.*;

import java.util.*;

public class TroopActionLogic {

    @Autowired
    @Qualifier("troopActionDAO")
    private DataAccessObject<TroopAction> troopActionDao;

    @Autowired
    @Qualifier("troopDAO")
    private DataAccessObject<Troop> troopDao;

    @Autowired
    @Qualifier("baseDAO")
    private DataAccessObject<Base> baseDao;

    @Autowired
    private MapLogic mapLogic;

    @Autowired
    @Qualifier("squareDAO")
    private DataAccessObject<Square> squareDao;

    public void handleAction(TroopAction action) {
        Tile tile = action.getTarget();

        boolean canBuildBase = false;


        for (Troop troop : action.getConcerns()) {
            if (troop.getType().getCanFoundBase()) {
                canBuildBase = true;
                break;
            }
        }


        boolean enemyTerritory = false;

        if (tile.getBase() != null && tile.getBase().getOwner() != action.getPlayer()) {
            enemyTerritory = false;
        }

        // check if other troops are on that tile
        Set<Troop> defenders = tile.getTroops();

        if (!defenders.isEmpty() && defenders.iterator().next().getOwner() != action.getPlayer())
            enemyTerritory = false;


        if (!enemyTerritory) {
            tile.getTroops().addAll(action.getConcerns());
            if (canBuildBase && action.getShouldFoundBase()) {
                if (tile.getBase() != null) {
                    //TODO: write errormsg
                } else {
                    Base base = createBase(tile, action.getPlayer());
                    //TODO: write msg to player
                }
            }
        } else {
            if (defenders.isEmpty()) {
                if (canBuildBase && !tile.getBase().isHome()) {
                    //take base
                    Base base = tile.getBase();
                    base.setOwner(action.getPlayer());
                    base = baseDao.update(base);
                    //TODO: write msg to both players
                } else {
                    //rob base
                    ResourceValue booty = calculateBooty(tile.getBase().getOwner(), action.getConcerns());

                    action.getPlayer().getResources().add(booty);
                    //TODO: write ms to both players
                }
            } else {
                Player enemyOwner = defenders.iterator().next().getOwner();
                // oho - enemies....FIGHT!!!!
                Set<Troop> attackers = new HashSet<Troop>(action.getConcerns());
                boolean attackerWin = calculateFight(action.getPlayer(), enemyOwner,
                        attackers, defenders, tile);

                if (attackerWin) {
                    if (tile.getBase() != null) {
                        //rob base
                        ResourceValue booty = calculateBooty(enemyOwner, attackers);
                        action.getPlayer().getResources().add(booty);

                        if (canBuildBase && !tile.getBase().isHome()) {
                            Base base = tile.getBase();
                            base.setOwner(action.getPlayer());
                            base = baseDao.update(base);
                            //TODO: write msg to both players
                        } else {
                            sendHome(attackers, action.getSource());
                        }
                    } else if (canBuildBase) {
                        Base base = createBase(tile, action.getPlayer());
                        //TODO: write msg to player
                    } else {
                        //stay
                    }
                } else {
                    if (!attackers.isEmpty()) {
                        // send attacking army home to mom
                        sendHome(attackers, action.getSource());
                    }
                }

            }
        }
    }

    private Base createBase(Tile tile, Player owner) {
        Base base = mapLogic.createBase(tile, owner);

        //update players resources
        owner.getResources().add(base.getResourceProduction());

        return base;
    }

    private void sendHome(Set<Troop> attackers, Tile destination) {
        TroopAction homeAction = new TroopAction();
        homeAction.setConcerns(attackers);
        homeAction.setShouldFoundBase(false);
        homeAction.setTarget(destination);
        homeAction.setIsAbortable(false);

        homeAction = troopActionDao.create(homeAction);
    }

    private boolean calculateFight(Player attackers_owner, Player defenders_owner,
                                   Set<Troop> attackers, Set<Troop> defenders, Tile tile) {

        Collection<Troop> deadTroops_attacker = null;
        Collection<Troop> deadTroops_defenders = null;

        do {
            // calculate victims
            deadTroops_attacker = calculateAttack(attackers, defenders);
            deadTroops_defenders = calculateAttack(defenders, attackers);

            // remove victims of the sets
            if (!deadTroops_defenders.isEmpty())
                defenders.removeAll(deadTroops_defenders);

            if (!deadTroops_attacker.isEmpty())
                attackers.removeAll(deadTroops_attacker);

            // remove victims in persistence layer
            for (Troop troop : deadTroops_attacker) {
                troopDao.delete(troop);
            }

            for (Troop troop : deadTroops_defenders) {
                troopDao.delete(troop);

                tile.getTroops().remove(troop);
            }

        } while (!deadTroops_attacker.isEmpty()
                && !deadTroops_defenders.isEmpty());

        if (attackers.size() > 0 && defenders.isEmpty()) {
            // attacker win
            // TODO: write MSG to both player about the result of the fight

            // attacking troops stay on the tile
            tile.getTroops().addAll(attackers);

            return true;
        } else if (defenders.size() > 0 && attackers.isEmpty()) {
            // defenders win
            // TODO: write MSG to both player about the result of the fight

            return false;
        } else {
            // draw
            if (defenders.size() == 0 && attackers.size() == 0) {
                // war - what is it good for?

                // TODO: write MSG to both player that they lost their army
                return false;
            } else {
                // mexican standoff

                // TODO: write MSG to both player about the result of the fight
                return false;
            }
        }
    }

    private ResourceValue calculateBooty(Player defenders_owner,
                                         Set<Troop> attackers) {

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

    private Collection<Troop> calculateAttack(Set<Troop> attackers,
                                              Set<Troop> defenders) {

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


}
