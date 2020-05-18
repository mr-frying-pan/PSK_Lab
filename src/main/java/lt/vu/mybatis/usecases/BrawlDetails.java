package lt.vu.mybatis.usecases;

import lombok.Getter;
import lt.vu.mybatis.dao.FightersMapper;
import lt.vu.mybatis.dao.TavernFighterMapper;
import lt.vu.mybatis.dao.TavernsMapper;
import lt.vu.mybatis.model.Fighter;
import lt.vu.mybatis.model.Tavern;
import lt.vu.mybatis.model.TavernFighter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class BrawlDetails implements Serializable {
    @Inject
    private TavernsMapper tavernMapper;

    @Inject
    private TavernFighterMapper tavernFighterMapper;

    @Inject
    private FightersMapper fightersMapper;

    @Getter
    private Tavern tavern;

    @Getter
    private List<Fighter> fighters;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int tavernId = Integer.parseInt(requestParameters.get("id"));
        this.tavern = this.tavernMapper.selectByPrimaryKey(tavernId);
        List<TavernFighter> tfs = this.tavernFighterMapper.selectByTavernId(tavernId);
        this.fighters = new ArrayList<>();
        for (TavernFighter tf : tfs) {
            Fighter fighter = fightersMapper.selectByPrimaryKey(tf.getFighterid());
            this.fighters.add(fighter);
        }
    }
}
