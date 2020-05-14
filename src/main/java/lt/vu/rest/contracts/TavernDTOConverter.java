package lt.vu.rest.contracts;

import lt.vu.entities.Tavern;

public class TavernDTOConverter {
    public static Tavern makeEntity(TavernDTO dto) {
        Tavern t = new Tavern();
        updateEntity(t, dto);
        return t;
    }

    public static void updateEntity(Tavern entity, TavernDTO dto) {
        entity.setName(dto.getName());
    }

    public static TavernDTO makeDTO(Tavern entity) {
        TavernDTO t = new TavernDTO();
        t.setId(entity.getId());
        t.setName(entity.getName());
        return t;
    }
}
