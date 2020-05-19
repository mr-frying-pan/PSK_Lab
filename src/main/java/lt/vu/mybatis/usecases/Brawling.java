package lt.vu.mybatis.usecases;

import lt.vu.mybatis.dao.FightersMapper;
import lt.vu.mybatis.dao.TavernFighterMapper;
import lt.vu.mybatis.model.Fighter;
import lt.vu.mybatis.model.TavernFighter;
import lt.vu.services.TavernBrawl;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@SessionScoped
@Named
public class Brawling implements Serializable {
    @Inject
    private FightersMapper fightersMapper;

    @Inject
    private TavernFighterMapper tavernFighterMapper;

    @Inject
    private TavernBrawl tavernBrawl;

    private List<Fighter> fighters;

    private CompletableFuture<List<Fighter>> brawling = null;

    public String brawl() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int tavernId = Integer.parseInt(requestParameters.get("id"));
        List<TavernFighter> tavernFighters = tavernFighterMapper.selectByTavernId(tavernId);
        fighters = tavernFighters.stream().map(tf -> fightersMapper.selectByPrimaryKey(tf.getFighterid())).collect(Collectors.toList());
        brawling = CompletableFuture.supplyAsync(() -> tavernBrawl.brawl(fighters));
        return "brawlDetails.xhtml?faces-redirect=true&id=" + tavernId;
    }

    public boolean isRunning() {
        return brawling != null && !brawling.isDone();
    }

    public String getBrawlStatus() throws ExecutionException, InterruptedException {
        if (brawling == null) {
            return null;
        } else if (isRunning()) {
            return "Brawl in progress...";
        }
        return "Winner: " + brawling.get().get(0).getName();
    }
}
