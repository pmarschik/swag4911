package swag49.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class  Map {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private Integer maxUsers;

    @Column(nullable = false)
    private String url;

	@OneToMany(mappedBy="map")
	private Set<Tile> consistsOf = new HashSet<Tile>();

	public Set<Tile> getConsistsOf() {
		return consistsOf;
	}

	public Long getId() {
		return id;
	}

	public Integer getMaxUsers() {
		return maxUsers;
	}

	public void setConsistsOf(Set<Tile> tiles) {
		this.consistsOf = tiles;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMaxUsers(Integer maxUsers) {
		this.maxUsers = maxUsers;
	}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
