package lt.vu.services;

import lt.vu.interceptors.Log;
import lt.vu.mybatis.model.Fighter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@Alternative
public class FastMyBatisBrawl implements TavernBrawl {
    @Inject
    private MagicAction magicAction;

    @Log
    @Override
    public List<Fighter> brawl(List<Fighter> fighters) {
        System.out.println("SHORT BRAWL");
        try {
            Thread.sleep(magicAction.doIt());
        } catch (InterruptedException e) {
        }
        return fighters;
    }
}
