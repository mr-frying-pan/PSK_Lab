package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Weapons")
@NamedQueries({
        @NamedQuery(name = "Weapon.getAll",
                query = "select w from Weapon as w"),
        @NamedQuery(name = "Weapon.getUnassociated",
                query = "select w from Weapon as w where w.owner is null or w.owner.id <> :id")
})
@Getter
@Setter
public class Weapon {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "power")
    private int power;

    @Version
    @Column(name = "opt_lock_version")
    private int version;

    @ManyToOne
    @JoinColumn(name = "fighterId")
    private Fighter owner;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weapon weapon = (Weapon) o;
        return Objects.equals(id, weapon.id) &&
                Objects.equals(name, weapon.name) &&
                Objects.equals(power, weapon.power);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, power);
    }
}
