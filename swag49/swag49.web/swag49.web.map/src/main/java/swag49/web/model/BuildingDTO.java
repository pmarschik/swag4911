package swag49.web.model;

import swag49.transfer.model.ResourceValueDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Martin
 * Date: 12.06.11
 * Time: 01:31
 * To change this template use File | Settings | File Templates.
 */
public class BuildingDTO {
    private int level;
    private String name;
    private ResourceValueDTO resourceProduction;
    private ResourceValueDTO upkeepCosts;

    private boolean canUpgrade;
    private ResourceValueDTO upgradeCosts;
    private long upgradeDuration;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResourceValueDTO getResourceProduction() {
        return resourceProduction;
    }

    public void setResourceProduction(ResourceValueDTO resourceProduction) {
        this.resourceProduction = resourceProduction;
    }


    public ResourceValueDTO getUpkeepCosts() {
        return upkeepCosts;
    }

    public void setUpkeepCosts(ResourceValueDTO upkeepCost) {
        this.upkeepCosts = upkeepCost;
    }

    public boolean isCanUpgrade() {
        return canUpgrade;
    }

    public void setCanUpgrade(boolean canUpgrade) {
        this.canUpgrade = canUpgrade;
    }

    public ResourceValueDTO getUpgradeCosts() {
        return upgradeCosts;
    }

    public void setUpgradeCosts(ResourceValueDTO upgradeCost) {
        this.upgradeCosts = upgradeCost;
    }

    public long getUpgradeDuration() {
        return upgradeDuration;
    }

    public void setUpgradeDuration(long upgradeDuration) {
        this.upgradeDuration = upgradeDuration;
    }
}
