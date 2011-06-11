package swag49.transfer.model;

public class BuildingTypeDTO {
    private long id;
    private ResourceValueDTO costs;
    private String name;

    public BuildingTypeDTO(long id, String name) {
        this.id = id;
        this.name = name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
