package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Taverns")
@NamedQueries({
        @NamedQuery(name = "Tavern.getAll",
                query = "select t from Tavern as t")
})
@Getter
@Setter
public class Tavern implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TavernFighter",
            joinColumns = @JoinColumn(name = "tavernId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fighterId", referencedColumnName = "id"))
    private List<Fighter> fighters = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tavern tavern = (Tavern) o;
        return Objects.equals(id, tavern.id) &&
                Objects.equals(name, tavern.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public void addFighter(Fighter fighter) {
        this.fighters.add(fighter);
    }
}
