package lt.vu.usecases;

import lombok.Getter;
import lt.vu.entities.Tavern;
import lt.vu.persistence.TavernsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class Taverns {
    @Inject
    private TavernsDAO tavernsDAO;

    @Getter
    private List<Tavern> allTaverns;

    @PostConstruct
    public void init() {
        this.allTaverns = this.tavernsDAO.get();
    }
}
