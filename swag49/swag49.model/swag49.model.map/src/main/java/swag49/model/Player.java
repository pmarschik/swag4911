package swag49.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Boolean online;

    @Column(nullable = false)
    private Boolean deleted;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne(optional = false)
    private Map plays;

    @OneToMany(mappedBy = "owner")
    private Set<Base> owns = new HashSet<Base>();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount_gold", column = @Column(name = "resources_gold")),
            @AttributeOverride(name = "amount_stone", column = @Column(name = "resources_stone")),
            @AttributeOverride(name = "amount_wood", column = @Column(name = "resources_wood")),
            @AttributeOverride(name = "amount_crops", column = @Column(name = "resources_crops"))})
    private ResourceValue resources = new ResourceValue();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount_gold", column = @Column(name = "income_gold")),
            @AttributeOverride(name = "amount_stone", column = @Column(name = "income_stone")),
            @AttributeOverride(name = "amount_wood", column = @Column(name = "income_wood")),
            @AttributeOverride(name = "amount_crops", column = @Column(name = "income_crops"))})
    private ResourceValue income = new ResourceValue();

    public ResourceValue getIncome() {
        return income;
    }

    public void setIncome(ResourceValue income) {
        this.income = income;
    }

    @OneToMany(mappedBy = "player")
    private Set<Action> actions = new HashSet<Action>();

    public Player() {
    }

    public Set<Action> getActions() {
        return actions;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public Long getId() {
        return id;
    }

    public Boolean getOnline() {
        return online;
    }

    public void getOwns(Set<Base> bases) {
        this.owns = bases;
    }

    public Map getPlays() {
        return plays;
    }

    public ResourceValue getResources() {
        return resources;
    }

    public Long getUserId() {
        return userId;
    }

    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Set<Base> setOwns() {
        return owns;
    }

    public void setPlays(Map plays) {
        this.plays = plays;
    }

    public void setResources(ResourceValue resources) {
        this.resources = resources;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}