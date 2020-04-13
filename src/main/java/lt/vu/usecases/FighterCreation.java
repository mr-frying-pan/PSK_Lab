package lt.vu.usecases;

import lombok.Getter;
import lt.vu.entities.Fighter;
import lt.vu.persistence.FightersDAO;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class FighterCreation implements Serializable {
    @Inject
    private FightersDAO fightersDAO;
    @Getter
    private final Fighter fighter = new Fighter();

    public String create() {
        fightersDAO.persist(fighter);
        return "index?faces-redirect=true";
    }
}
