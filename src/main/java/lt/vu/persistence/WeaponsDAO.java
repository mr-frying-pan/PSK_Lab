package lt.vu.persistence;

import lt.vu.entities.Weapon;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class WeaponsDAO {
    @Inject
    @PersistenceContext
    private EntityManager em;

    public List<Weapon> get() {
        return this.em.createNamedQuery("Weapon.getAll", Weapon.class).getResultList();
    }

    public Weapon get(Integer id) {
        return this.em.find(Weapon.class, id);
    }

    public List<Weapon> getUnassociated(Integer fighterId) {
        return this.em.createNamedQuery("Weapon.getUnassociated", Weapon.class)
                .setParameter("id", fighterId).getResultList();
    }

    @Transactional
    public void persist(Weapon weapon) {
        this.em.persist(weapon);
    }

    @Transactional
    public void update(Weapon weapon) {
        this.em.merge(weapon);
    }

    @Transactional
    public void delete(Weapon weapon) {
        this.em.remove(this.get(weapon.getId()));
    }
}
