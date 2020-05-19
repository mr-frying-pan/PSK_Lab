package lt.vu.services;

import lt.vu.interceptors.Log;
import lt.vu.mybatis.model.Fighter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.List;
import java.util.Random;

@ApplicationScoped
@Alternative
public class RandomMyBatisBrawl implements TavernBrawl {
    @Log
    @Override
    public List<Fighter> brawl(List<Fighter> fighters) {
        System.out.println("RANDOM BRAWL");
        int idx = new Random().nextInt(fighters.size());
        Fighter tmp = fighters.get(0);
        fighters.set(0, fighters.get(idx));
        fighters.set(idx, tmp);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        return fighters;
    }
}
