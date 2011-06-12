package swag49.web.model;

import swag49.model.Base;
import swag49.model.Square;
import swag49.transfer.model.TroopDTO;

import java.util.List;
import java.util.Set;

public class TileOverviewDTOFull {
	private int x;
	private int y;
	private String info;
	private String specialResource;
    private long baseId;
	private boolean enemyTerritory;
    private Set<TroopDTO> troops;
    private Base base;
    private List<SquareDTO> squares;

    public long getBaseId() {
        return baseId;
    }

    public void setBaseId(long baseId) {
        this.baseId = baseId;
    }

    public List<SquareDTO> getSquares() {
        return squares;
    }

    public void setSquares(List<SquareDTO> squares) {
        this.squares = squares;
    }

    public boolean isHasTroops() {
        return getTroops() != null && !getTroops().isEmpty();
    }

    public boolean isHasBase() {
        return getBase() != null;
    }

    public TileOverviewDTOFull(int tileX, int tileY) {
		this.x = tileX;
		this.y = tileY;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setSpecialResource(String special) {
		this.specialResource = special;
	}

	public String getSpecialResource() {
		return specialResource;
	}

	public boolean isEnemyTerritory() {
		return enemyTerritory;
	}

	public void setEnemyTerritory(boolean enemyTerritory) {
		this.enemyTerritory = enemyTerritory;
	}

    public Set<TroopDTO> getTroops() {
        return troops;
    }

    public void setTroops(Set<TroopDTO> troops) {
        this.troops = troops;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }
}
