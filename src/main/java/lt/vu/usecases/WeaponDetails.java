package lt.vu.usecases;

import lombok.Getter;
import lt.vu.entities.Weapon;
import lt.vu.persistence.WeaponsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;

@Named
@ViewScoped
public class WeaponDetails implements Serializable {
    @Inject
    private WeaponsDAO weaponsDAO;

    @Getter
    private Weapon weapon;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int weaponId = Integer.parseInt(requestParameters.get("id"));
        this.weapon = this.weaponsDAO.get(weaponId);
    }

    public String delete() {
        this.weaponsDAO.delete(this.weapon);
        return "index.xhtml?faces-redirect=true";
    }
}
