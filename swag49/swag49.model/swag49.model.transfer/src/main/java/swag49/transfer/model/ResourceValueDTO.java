package swag49.transfer.model;

public class ResourceValueDTO {
    private int amount_gold;
    private int amount_wood;
    private int amount_crops;
    private int amount_stone;

    public ResourceValueDTO(int amountGold, int amountWood, int amountStone, int amountCrops) {
        this.amount_gold = amountGold;
        this.amount_wood = amountWood;
        this.amount_stone = amountStone;
        this.amount_crops = amountCrops;
    }

    public int getAmount_gold() {
        return amount_gold;
    }

    public void setAmount_gold(int amount_gold) {
        this.amount_gold = amount_gold;
    }

    public int getAmount_wood() {
        return amount_wood;
    }

    public void setAmount_wood(int amount_wood) {
        this.amount_wood = amount_wood;
    }

    public int getAmount_crops() {
        return amount_crops;
    }

    public void setAmount_crops(int amount_crops) {
        this.amount_crops = amount_crops;
    }

    public int getAmount_stone() {
        return amount_stone;
    }

    public void setAmount_stone(int amount_stone) {
        this.amount_stone = amount_stone;
    }
}
