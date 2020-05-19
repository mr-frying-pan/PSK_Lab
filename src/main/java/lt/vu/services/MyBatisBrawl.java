package lt.vu.services;

import lt.vu.mybatis.model.Fighter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.List;

@ApplicationScoped
@Alternative
public class MyBatisBrawl implements TavernBrawl {
    @Override
    public List<Fighter> brawl(List<Fighter> fighters) {
        System.out.println("LONG BRAWL");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        return fighters;
    }
}
