package swag49.transfer.model;

public class TroopTypeDTO {
    private String name;
    private boolean canFoundBase;
    private ResourceValueDTO costs;
    private long id;

    public TroopTypeDTO(String name, boolean canFoundBase, ResourceValueDTO costs, long id) {
        this.name = name;
        this.canFoundBase = canFoundBase;
        this.costs = costs;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getCanFoundBase() {
        return canFoundBase;
    }

    public void setCanFoundBase(boolean canFoundBase) {
        this.canFoundBase = canFoundBase;
    }

    public ResourceValueDTO getCosts() {
        return costs;
    }

    public void setCosts(ResourceValueDTO costs) {
        this.costs = costs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
