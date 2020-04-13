package lt.vu.usecases;

import lombok.Getter;
import lt.vu.entities.Tavern;
import lt.vu.persistence.TavernsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;

@Named
@ViewScoped
public class TavernUpdate implements Serializable {
    @Inject
    private TavernsDAO tavernsDAO;

    @Getter
    private Tavern tavern;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int tavernId = Integer.parseInt(requestParameters.get("id"));
        this.tavern = this.tavernsDAO.get(tavernId);
    }

    public String update() {
        this.tavernsDAO.update(this.tavern);
        return "tavernDetails.xhtml?faces-redirect=true&id=" + this.tavern.getId();
    }
}
