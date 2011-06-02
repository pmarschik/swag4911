package swag49.gamelogic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import swag49.model.Player;
import swag49.model.ResourceValue;
import swag49.model.Tile;
import swag49.model.Troop;
import swag49.model.TroopAction;

public class TroopActionLogic {

	public void handleAction(TroopAction action) {
		Tile tile = action.getTarget();

		// check if other troops are on that tile
		Set<Troop> defenders = tile.getTroops();

		if (defenders.isEmpty()) {
			tile.getTroops().addAll(action.getConcerns());
		} else {
			// check if this are allied troops

			// get first troop of collection
			// assuming that only troops of one player can stay at a tile at a
			// moment
			Troop troop = defenders.iterator().next();
			if (troop.getOwner() == action.getPlayer()) {
				// lets play a party!!!
				tile.getTroops().addAll(action.getConcerns());
			} else {
				// oho - enemies....FIGHT!!!!
				calculateFight(action.getPlayer(), troop.getOwner(),
						action.getConcerns(), defenders, tile);
			}
		}

	}

	private void calculateFight(Player attackers_owner, Player defenders_owner,
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

			// TODO: remove victims in persistence layer

		} while (!deadTroops_attacker.isEmpty()
				&& !deadTroops_defenders.isEmpty());

		if (attackers.size() > 0 && defenders.isEmpty()) {
			// attacker win
			// TODO: write MSG to both player about the result of the fight

			if (tile.getBase() != null) {
				// attacking troops rob the bank and go home

				ResourceValue booty = calculateBooty(defenders_owner, attackers);

				// TODO: send troops with booty back

			} else {
				// attacking troops stay on the tile
			}

		} else if (defenders.size() > 0 && attackers.isEmpty()) {
			// defenders win
			// TODO: write MSG to both player about the result of the fight

		} else {
			// draw
			if (defenders.size() == 0 && attackers.size() == 0) {
				// war - what is it good for?

				// TODO: write MSG to both player that they lost their army
			} else {
				// mexican standoff

				// TODO: write MSG to both player about the result of the fight
				// TODO: send attacking army home to mom
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
