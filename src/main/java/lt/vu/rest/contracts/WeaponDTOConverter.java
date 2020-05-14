package lt.vu.rest.contracts;

import lt.vu.entities.Weapon;

public class WeaponDTOConverter {
    public static Weapon makeEntity(WeaponDTO dto) {
        Weapon w = new Weapon();
        updateEntity(w, dto);
        return w;
    }

    public static void updateEntity(Weapon entity, WeaponDTO dto) {
        entity.setName(dto.getName());
        entity.setPower(dto.getPower());
    }

    public static WeaponDTO makeDTO(Weapon entity) {
        WeaponDTO w = new WeaponDTO();
        w.setId(entity.getId());
        w.setName(entity.getName());
        w.setPower(entity.getPower());
        w.setOwnerName(entity.getOwner().getName());
        return w;
    }
}
