package lt.vu.persistence;

import lombok.Getter;
import lt.vu.entities.Tavern;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TavernsDAO {

    @Inject
    @PersistenceContext
    @Getter
    private EntityManager em;

    public List<Tavern> get() {
        return this.em.createNamedQuery("Tavern.getAll", Tavern.class).getResultList();
    }

    public Tavern get(int id) {
        return em.find(Tavern.class, id);
    }

    @Transactional
    public void persist(Tavern tavern) {
        System.out.println("SAVING TAVERN: " + tavern.getName());
        this.em.persist(tavern);
    }

    public List<Tavern> getUnassociated(Integer fighterId) {
        return this.get().stream()
                .filter(t ->
                        t.getFighters().stream()
                                .noneMatch(f -> f.getId() == fighterId))
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(Tavern tavern) {
        this.em.merge(tavern);
    }

    @Transactional
    public void delete(Tavern tavern) {
        this.em.remove(this.get(tavern.getId()));
    }
}
