package swag49.web.model;

/**
 * @author michael
 */
public class MapLocationDTO {

    private Long id;
    private String url;

    public MapLocationDTO() {
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

    @Override
    public String toString() {
        return "MapLocationDTO{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
