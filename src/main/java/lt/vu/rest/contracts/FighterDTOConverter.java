package lt.vu.rest.contracts;

import lt.vu.entities.Fighter;

public class FighterDTOConverter {
    public static Fighter makeEntity(FighterDTO dto) {
        Fighter f = new Fighter();
        updateEntity(f, dto);
        return f;
    }

    public static void updateEntity(Fighter entity, FighterDTO dto) {
        entity.setName(dto.getName());
        entity.setLevel(dto.getLevel());
        entity.setPower(dto.getPower());
        entity.setPhrase(dto.getPhrase());
    }

    public static FighterDTO makeDTO(Fighter entity) {
        FighterDTO f = new FighterDTO();
        f.setId(entity.getId());
        f.setName(entity.getName());
        f.setLevel(entity.getLevel());
        f.setPower(entity.getPower());
        f.setPhrase(entity.getPhrase());
        return f;
    }
}
