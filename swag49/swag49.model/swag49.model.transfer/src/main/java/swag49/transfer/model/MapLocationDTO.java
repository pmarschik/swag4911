package swag49.transfer.model;

/**
 * @author michael
 */
public class MapLocationDTO {

    private long id;
    private String url;
    private String mapName;

    public MapLocationDTO() {
    }

    public MapLocationDTO(long id, String url, String mapName) {
        this.id = id;
        this.url = url;
        this.mapName = mapName;
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
