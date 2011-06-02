package swag49.model;

public class TileOverviewDTO {
	private Integer x;
	private Integer y;

	private String info;
	private ResourceType specialResource;
	
	private boolean hasBase;
	private boolean enemyTerritory;

	public TileOverviewDTO(Tile tile) {
		this.x = tile.getId().getX();
		this.y = tile.getId().getY();
		
		
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setSpecialResource(ResourceType special) {
		this.specialResource = special;
	}

	public ResourceType getSpecialResource() {
		return specialResource;
	}

	public boolean isHasBase() {
		return hasBase;
	}

	public void setHasBase(boolean hasBase) {
		this.hasBase = hasBase;
	}

	public boolean isEnemyTerritory() {
		return enemyTerritory;
	}

	public void setEnemyTerritory(boolean enemyTerritory) {
		this.enemyTerritory = enemyTerritory;
	}

}
