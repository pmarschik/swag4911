package swag49.model;

import com.google.common.collect.Sets;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Statistic {
    @Id
	@GeneratedValue
	private Long id;

    private String name;

    @SuppressWarnings({"JpaQlInspection"})
    @OneToMany(mappedBy = "statistic", cascade = CascadeType.ALL)
    @OrderBy("id.ranking asc")
    private Set<StatisticEntry> entries = Sets.newLinkedHashSet();

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

    public Set<StatisticEntry> getEntries() {
        return entries;
    }

    public void setEntries(Set<StatisticEntry> entries) {
        this.entries = entries;
    }
}
