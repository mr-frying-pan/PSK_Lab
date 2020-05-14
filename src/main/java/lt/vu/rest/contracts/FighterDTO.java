package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FighterDTO {
    private int id;
    private String name;
    private int power;
    private int level;
    private String phrase;
}
