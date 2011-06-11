package swag49.transfer.model;

public class TileOverviewDTO {
    private int x;
    private int y;
    private String info;
    private ResourceType specialResource;
    private boolean hasBase;
    private boolean enemyTerritory;
    private boolean hasTroops;

    public boolean isHasTroops() {
        return hasTroops;
    }

    public void setHasTroops(boolean hasTroops) {
        this.hasTroops = hasTroops;
    }

    public TileOverviewDTO(int tileX, int tileY) {
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
