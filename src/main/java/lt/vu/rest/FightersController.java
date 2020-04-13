package lt.vu.rest;

import lt.vu.entities.Fighter;
import lt.vu.persistence.FightersDAO;
import lt.vu.rest.contracts.FighterDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Path("/fighters")
public class FightersController {
    @Inject
    private FightersDAO fightersDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFighters() {
        List<Fighter> fighters = fightersDAO.get();
        List<FighterDTO> fighterDTOs = new ArrayList<>();
        for (Fighter f : fighters) {
            FighterDTO fDTO = new FighterDTO();
            fDTO.setName(f.getName());
            fDTO.setLevel(f.getLevel());
            fDTO.setPower(f.getPower());
        }
        return Response.ok(fighterDTOs).build();
    }
}
