package swag49.model;

import javax.persistence.Embeddable;

@Embeddable
public class ResourceValue {

    private Integer amount_gold;
    private Integer amount_stone;
    private Integer amount_wood;
    private Integer amount_crops;

    public ResourceValue() {
        amount_gold = 0;
        amount_stone = 0;
        amount_wood = 0;
        amount_crops = 0;
    }

    public ResourceValue(int wood, int crops, int gold, int stone) {
        amount_wood = wood;
        amount_crops = crops;
        amount_gold = gold;
        amount_stone = stone;
    }

    public ResourceValue(ResourceValue value) {
        amount_wood = value.getAmount_wood();
        amount_crops = value.getAmount_crops();
        amount_gold = value.getAmount_gold();
        amount_stone = value.getAmount_stone();

    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof ResourceValue) {
            return this.amount_gold.equals(((ResourceValue) obj).amount_gold)
                    && this.amount_stone
                    .equals(((ResourceValue) obj).amount_stone)
                    && this.amount_wood
                    .equals(((ResourceValue) obj).amount_wood)
                    && this.amount_crops
                    .equals(((ResourceValue) obj).amount_crops);
        } else {
            return false;
        }
    }

    public Integer getAmount_crops() {
        return amount_crops;
    }

    public Integer getAmount_gold() {
        return amount_gold;
    }

    public Integer getAmount_stone() {
        return amount_stone;
    }

    public Integer getAmount_wood() {
        return amount_wood;
    }

    @Override
    public int hashCode() {
        return amount_gold.hashCode() + amount_stone.hashCode()
                + amount_wood.hashCode() + amount_crops.hashCode();
    }

    public void setAmount_crops(Integer amount_crops) {
        this.amount_crops = amount_crops;
    }

    public void setAmount_gold(Integer amount_gold) {
        this.amount_gold = amount_gold;
    }

    public void setAmount_stone(Integer amount_stone) {
        this.amount_stone = amount_stone;
    }

    public void setAmount_wood(Integer amount_wood) {
        this.amount_wood = amount_wood;
    }


}
