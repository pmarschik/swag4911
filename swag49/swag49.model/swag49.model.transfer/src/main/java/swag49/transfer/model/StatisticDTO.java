package swag49.transfer.model;

import com.google.common.collect.Sets;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement(name = "statistic")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatisticDTO {

    @XmlAttribute(name = "id")
    private long id;

    @XmlElement(name = "name")
    private String name;

    private Set<StatisticEntryDTO> entries = Sets.newLinkedHashSet();

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

    public Set<StatisticEntryDTO> getEntries() {
        return entries;
    }

    public void setEntries(Set<StatisticEntryDTO> entries) {
        this.entries = entries;
    }
}
