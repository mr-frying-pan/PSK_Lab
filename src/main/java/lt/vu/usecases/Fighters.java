package lt.vu.usecases;

import lombok.Getter;
import lt.vu.entities.Fighter;
import lt.vu.persistence.FightersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class Fighters {
    @Inject
    private FightersDAO fightersDAO;

    @Getter
    private List<Fighter> allFighters;

    @PostConstruct
    public void init() {
        this.allFighters = this.fightersDAO.get();
    }
}
