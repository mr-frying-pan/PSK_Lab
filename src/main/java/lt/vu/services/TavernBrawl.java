package lt.vu.services;

import lt.vu.mybatis.model.Fighter;

import java.util.List;

public interface TavernBrawl {
    List<Fighter> brawl(List<Fighter> fighters);
}
