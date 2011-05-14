package swag49.gamelogic;

import java.util.Set;

import swag49.model.Tile;
import swag49.model.Troop;
import swag49.model.TroopAction;

public class TroopActionLogic {

	public void handleAction(TroopAction action) {
		Tile tile = action.getTarget();

		// check if other troops are on that tile
		Set<Troop> otherTroops = tile.getTroops();

		if (otherTroops.isEmpty()) {
			tile.getTroops().addAll(action.getConcerns());
		} else {
			// check if this are allied troops

			// get first troop of collection
			// assuming that only troops of one player can stay at a tile at a
			// moment
			Troop troop = otherTroops.iterator().next();
			if (troop.getOwner() == action.getPlayer()) {
				// lets play a party!!!
				tile.getTroops().addAll(action.getConcerns());
			} else {
				// oho - enemies....FIGHT!!!!
				calculateFight(action.getConcerns(), otherTroops);
			}
		}

	}

	private void calculateFight(Set<Troop> concerns, Set<Troop> otherTroops) {
		// TODO Auto-generated method stub

	}

}
