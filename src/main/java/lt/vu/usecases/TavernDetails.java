package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Fighter;
import lt.vu.entities.Tavern;
import lt.vu.persistence.FightersDAO;
import lt.vu.persistence.TavernsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class TavernDetails implements Serializable {
    @Inject
    private TavernsDAO tavernsDAO;

    @Inject
    private FightersDAO fightersDAO;

    @Getter
    private Tavern tavern;

    @Getter
    @Setter
    private Integer chosenFighterId = -1;

    @Getter
    private List<Fighter> otherFighters;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int tavernId = Integer.parseInt(requestParameters.get("id"));
        this.tavern = this.tavernsDAO.get(tavernId);

        this.otherFighters = this.fightersDAO.getUnassociated(tavernId);
    }

    public String addFighter() {
        Fighter fighter = this.otherFighters.stream()
                .filter(f -> f.getId() == chosenFighterId)
                .collect(Collectors.toList())
                .get(0);
        this.tavern.addFighter(fighter);
        this.tavernsDAO.update(this.tavern);
        return "tavernDetails.xhtml?faces-redirect=true&id=" + this.tavern.getId();
    }

    public String delete() {
        this.tavernsDAO.delete(this.tavern);
        return "index.xhtml?faces-redirect=true";
    }
}
