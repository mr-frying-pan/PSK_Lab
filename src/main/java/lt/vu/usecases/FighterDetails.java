package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Fighter;
import lt.vu.entities.Tavern;
import lt.vu.entities.Weapon;
import lt.vu.persistence.FightersDAO;
import lt.vu.persistence.TavernsDAO;
import lt.vu.persistence.WeaponsDAO;

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
public class FighterDetails implements Serializable {
    @Inject
    private FightersDAO fightersDAO;

    @Inject
    private TavernsDAO tavernsDAO;

    @Inject
    private WeaponsDAO weaponsDAO;

    @Getter
    private Fighter fighter;

    @Getter
    private List<Tavern> otherTaverns;

    @Getter
    private List<Weapon> otherWeapons;

    @Getter
    @Setter
    private Integer chosenTavernId = -1;

    @Getter
    @Setter
    private Integer chosenWeaponId = -1;

    @Getter
    private boolean reload;

    @PostConstruct
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.reload = requestParameters.getOrDefault("reload", "false").equals("true");
        Integer fighterId = Integer.parseInt(requestParameters.get("id"));
        this.fighter = this.fightersDAO.get(fighterId);

        this.otherTaverns = this.tavernsDAO.getUnassociated(fighterId);
        this.otherWeapons = this.weaponsDAO.getUnassociated(fighterId);
    }

    public String addWeapon() {
        Weapon weapon = this.otherWeapons.stream()
                .filter(w -> w.getId() == this.chosenWeaponId)
                .collect(Collectors.toList())
                .get(0);
        weapon.setOwner(this.fighter);
        this.weaponsDAO.update(weapon);
        return "fighterDetails.xhtml?faces-redirect=true&id=" + this.fighter.getId();
    }

    public String delete() {
        Fighter deleted = this.fightersDAO.get(this.fighter.getId());
        this.fightersDAO.delete(deleted);
        return "index.xhtml?faces-redirect=true";
    }
}
