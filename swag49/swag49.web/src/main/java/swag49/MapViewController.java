package swag49;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import swag49.dao.DataAccessObject;
import swag49.model.Map;
import swag49.model.Player;
import swag49.model.ResourceType;
import swag49.model.Tile;
import swag49.model.TileOverviewDTO;
import swag49.model.Troop;
import swag49.model.TroopDTO;

@Controller
public class MapViewController {

	@Autowired
	@Qualifier("tileDAO")
	private DataAccessObject<Tile> tileDAO;

	private Player player;

	private Map map;

	@RequestMapping(value = "overview/", method = RequestMethod.GET)
	public String getMapOverview(@RequestParam("xLow") int x_low,
			@RequestParam("yLow") int y_low, @RequestParam("xHigh") int x_high,
			@RequestParam("yHigh") int y_high, Model model) {

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
