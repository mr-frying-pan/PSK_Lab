package lt.vu.mybatis.usecases;

import lombok.Getter;
import lt.vu.mybatis.dao.TavernsMapper;
import lt.vu.mybatis.model.Tavern;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Model
public class TavernSelect implements Serializable {
    @Inject
    private TavernsMapper tavernMapper;

    @Getter
    private List<Tavern> allTaverns;

    @PostConstruct
    public void init() {
        this.allTaverns = this.tavernMapper.selectAll();
    }
}
