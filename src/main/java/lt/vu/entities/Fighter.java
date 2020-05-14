package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Fighters")
@NamedQueries({
        @NamedQuery(name = "Fighter.getAll",
                query = "select f from Fighter as f"),
        @NamedQuery(name = "Fighter.getByName",
                query = "select f from Fighter as f where f.name = :name")
})
@Getter
@Setter
public class Fighter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "power", nullable = false)
    private int power = 0;

    @Column(name = "level", nullable = false)
    private int level = 0;

    @Column(name = "phrase", length = 100)
    private String phrase;

    @Version
    @Column(name = "opt_lock_version")
    private int version;

    @ManyToMany(mappedBy = "fighters", fetch = FetchType.LAZY)
    private List<Tavern> taverns;

    @OneToMany(mappedBy = "owner")
    private List<Weapon> weapons;

    public int getEffectivePower() {
        int efPower = this.power + this.level;
        for (Weapon w :
                this.weapons) {
            efPower += w.getPower();
        }
        return efPower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fighter)) return false;
        Fighter fighter = (Fighter) o;
        return getId() == fighter.getId() &&
                getPower() == fighter.getPower() &&
                getLevel() == fighter.getLevel() &&
                getVersion() == fighter.getVersion() &&
                getName().equals(fighter.getName()) &&
                Objects.equals(getPhrase(), fighter.getPhrase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPower(), getLevel(), getPhrase(), getVersion());
    }
}
