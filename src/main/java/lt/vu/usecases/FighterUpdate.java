package lt.vu.usecases;

import lombok.Getter;
import lt.vu.entities.Fighter;
import lt.vu.persistence.FightersDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import java.io.Serializable;
import java.util.Map;

@Named
@ViewScoped
public class FighterUpdate implements Serializable {
    @Inject
    private FightersDAO fightersDAO;

    @Getter
    private Fighter fighter;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer fighterId = Integer.parseInt(requestParameters.get("id"));
        this.fighter = this.fightersDAO.get(fighterId);
    }

    public String update() {
        try {
            this.fightersDAO.update(this.fighter);
            return "fighterDetails.xhtml?faces-redirect=true&id=" + this.fighter.getId();
        } catch (OptimisticLockException e) {
            return "fighterDetails.xhtml?faces-redirect=true&id=" + this.fighter.getId() + "&reload=true";
        }
    }
}
