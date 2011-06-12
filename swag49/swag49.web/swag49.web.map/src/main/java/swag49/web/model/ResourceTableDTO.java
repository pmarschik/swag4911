package swag49.web.model;

import swag49.model.ResourceValue;

/**
 * Created by IntelliJ IDEA.
 * User: Ben
 * Date: 6/12/11
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResourceTableDTO {
    public ResourceValue getAmount() {
        return amount;
    }

    public void setAmount(ResourceValue amount) {
        this.amount = amount;
    }

    public ResourceValue getIncome() {
        return income;
    }

    public void setIncome(ResourceValue income) {
        this.income = income;
    }

    public ResourceValue getUpkeep() {
        return upkeep;
    }

    public void setUpkeep(ResourceValue upkeep) {
        this.upkeep = upkeep;
    }

    private ResourceValue amount;
    private ResourceValue income;
    private ResourceValue upkeep;
}
