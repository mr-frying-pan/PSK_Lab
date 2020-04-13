package lt.vu.usecases;

import lombok.Getter;
import lt.vu.entities.Weapon;
import lt.vu.persistence.WeaponsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class Weapons {
    @Inject
    private WeaponsDAO weaponsDAO;

    @Getter
    private List<Weapon> allWeapons;

    @PostConstruct
    public void init() {
        this.allWeapons = this.weaponsDAO.get();
    }
}
