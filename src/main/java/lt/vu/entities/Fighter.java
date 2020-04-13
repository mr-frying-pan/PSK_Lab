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
                query = "select f from Fighter as f")
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

    @ManyToMany(mappedBy = "fighters", fetch = FetchType.LAZY)
    private List<Tavern> taverns;

    @OneToMany(mappedBy = "owner")
    private List<Weapon> weapons;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fighter fighter = (Fighter) o;
        return Objects.equals(id, fighter.id) &&
                Objects.equals(name, fighter.name) &&
                Objects.equals(power, fighter.power) &&
                Objects.equals(level, fighter.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, power, level);
    }

    public int getEffectivePower() {
        int efPower = this.power + this.level;
        for (Weapon w :
                this.weapons) {
            efPower += w.getPower();
        }
        return efPower;
    }
}
