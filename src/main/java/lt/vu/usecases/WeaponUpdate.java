package lt.vu.usecases;

import lombok.Getter;
import lt.vu.entities.Weapon;
import lt.vu.persistence.WeaponsDAO;

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
public class WeaponUpdate implements Serializable {
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

    public String update() {
        try {
            this.weaponsDAO.update(this.weapon);
            return "weaponDetails.xhtml?faces-redirect=true&id=" + this.weapon.getId();
        } catch (OptimisticLockException e) {
            return "weaponDetails.xhtml?faces-redirect=true&id=" + this.weapon.getId() + "&reload=true";
        }
    }
}
