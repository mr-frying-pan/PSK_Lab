package lt.vu.decorators;

import lt.vu.mybatis.model.Fighter;
import lt.vu.services.TavernBrawl;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.List;
import java.util.Random;

@Decorator
public class BrawlParamDecorator implements TavernBrawl {
    @Inject
    @Delegate
    @Any
    TavernBrawl decorated;

    public List<Fighter> brawl(List<Fighter> fighters) {
        Fighter unexpected = new Fighter();
        unexpected.setId(0);
        unexpected.setName("Don Cavalho");
        unexpected.setLevel(new Random().nextInt(420));
        unexpected.setPhrase("Nobody expects Spanish Inquisition");
        fighters.add(unexpected);
        System.out.println("DECORATED");
        return decorated.brawl(fighters);
    }
}
