package swag49.web.model;

import swag49.model.TroopType;

/**
 * Created by IntelliJ IDEA.
 * User: Martin
 * Date: 10.06.11
 * Time: 23:41
 * To change this template use File | Settings | File Templates.
 */
public class TroopTypeDTO {
    private String name;
    private Boolean canFoundBase;

    private ResourceValueDTO costs;
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCanFoundBase() {
        return canFoundBase;
    }

    public void setCanFoundBase(Boolean canFoundBase) {
        this.canFoundBase = canFoundBase;
    }

    public ResourceValueDTO getCosts() {
        return costs;
    }

    public void setCosts(ResourceValueDTO costs) {
        this.costs = costs;
    }

    public TroopTypeDTO(TroopType type) {
        this.name = type.getName();
        this.canFoundBase = type.getCanFoundBase();
        this.id = type.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
