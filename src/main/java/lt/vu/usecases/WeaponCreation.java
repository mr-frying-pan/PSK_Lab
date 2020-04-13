package lt.vu.usecases;

import lombok.Getter;
import lt.vu.entities.Weapon;
import lt.vu.persistence.WeaponsDAO;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class WeaponCreation implements Serializable {
    @Inject
    private WeaponsDAO weaponsDAO;

    @Getter
    private final Weapon weapon = new Weapon();

    public String create() {
        this.weaponsDAO.persist(this.weapon);
        return "index.xhtml?faces-redirect=true";
    }
}
