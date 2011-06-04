package swag49.web.model;

import swag49.model.MapLocation;

/**
 * @author michael
 */
public class MapLocationDTO {

    private Long id;
    private String url;
    private String mapName;

    public MapLocationDTO() {
    }

    public MapLocationDTO(MapLocation mapLocation) {
        this.id = mapLocation.getId();
        this.url = mapLocation.getUrl();
        this.mapName = mapLocation.getMapName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    @Override
    public String toString() {
        return "MapLocationDTO{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", mapName='" + mapName + '\'' +
                '}';
    }
}
