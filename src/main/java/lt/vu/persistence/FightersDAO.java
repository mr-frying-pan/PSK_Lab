package lt.vu.persistence;

import lombok.Getter;
import lt.vu.entities.Fighter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class FightersDAO {

    @Inject
    @PersistenceContext
    @Getter
    private EntityManager em;

    @Transactional
    public void persist(Fighter fighter) {
        this.em.persist(fighter);
    }

    public List<Fighter> get() {
        return this.em.createNamedQuery("Fighter.getAll", Fighter.class).getResultList();
    }

    public Fighter get(Integer id) {
        return em.find(Fighter.class, id);
    }

    public Fighter get(String name) {
        return em.createNamedQuery("Fighter.getByName", Fighter.class).setParameter("name", name).getSingleResult();
    }

    public List<Fighter> getUnassociated(Integer tavernId) {
        return this.get().stream()
                .filter(f ->
                        f.getTaverns().stream()
                                .noneMatch(t -> t.getId() == tavernId))
                .collect(Collectors.toList());
    }

    @Transactional
    public Fighter update(Fighter fighter) {
        return em.merge(fighter);
    }

    @Transactional
    public void delete(Fighter fighter) {
        this.em.remove(fighter);
    }
}
