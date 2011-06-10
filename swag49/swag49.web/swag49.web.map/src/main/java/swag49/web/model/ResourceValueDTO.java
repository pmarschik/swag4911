package swag49.web.model;

import swag49.model.ResourceValue;

/**
 * Created by IntelliJ IDEA.
 * User: Martin
 * Date: 09.06.11
 * Time: 14:49
 * To change this template use File | Settings | File Templates.
 */
public class ResourceValueDTO {
    private Integer amount_gold;
    private Integer amount_wood;
    private Integer amount_crops;
    private Integer amount_stone;

    public ResourceValueDTO(ResourceValue resources) {
        this.amount_gold = resources.getAmount_gold();
        this.amount_wood = resources.getAmount_wood();
        this.amount_stone = resources.getAmount_stone();
        this.amount_crops = resources.getAmount_crops();
    }

    public Integer getAmount_gold() {
        return amount_gold;
    }

    public void setAmount_gold(Integer amount_gold) {
        this.amount_gold = amount_gold;
    }

    public Integer getAmount_wood() {
        return amount_wood;
    }

    public void setAmount_wood(Integer amount_wood) {
        this.amount_wood = amount_wood;
    }

    public Integer getAmount_crops() {
        return amount_crops;
    }

    public void setAmount_crops(Integer amount_crops) {
        this.amount_crops = amount_crops;
    }

    public Integer getAmount_stone() {
        return amount_stone;
    }

    public void setAmount_stone(Integer amount_stone) {
        this.amount_stone = amount_stone;
    }
}
