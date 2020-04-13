package lt.vu.usecases;

import lombok.Getter;
import lt.vu.entities.Tavern;
import lt.vu.persistence.TavernsDAO;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class TavernCreation implements Serializable {
    @Inject
    private TavernsDAO tavernsDAO;

    @Getter
    private final Tavern tavernToCreate = new Tavern();

    public String create() {
        this.tavernsDAO.persist(this.tavernToCreate);
        return "index.xhtml?faces-redirect=true";
    }
}
