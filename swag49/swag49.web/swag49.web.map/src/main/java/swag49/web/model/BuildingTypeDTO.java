package swag49.web.model;

import swag49.model.ResourceValue;

public class BuildingTypeDTO {
     private Long id;


    public BuildingTypeDTO(Long id, String name) {
        this.id = id;
        this.name = name;

    }

    private String name;

    public ResourceValue getCosts() {
        return costs;
    }

    public void setCosts(ResourceValue costs) {
        this.costs = costs;
    }

    private ResourceValue costs;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
