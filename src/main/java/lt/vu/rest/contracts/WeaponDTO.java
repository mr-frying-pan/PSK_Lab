package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeaponDTO {
    private String name;
    private int power;
    private String ownerName;
}
