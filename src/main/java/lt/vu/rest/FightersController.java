package lt.vu.rest;

import lt.vu.entities.Fighter;
import lt.vu.persistence.FightersDAO;
import lt.vu.rest.contracts.FighterDTO;
import lt.vu.rest.contracts.FighterDTOConverter;
import lt.vu.rest.contracts.TavernDTOConverter;
import lt.vu.rest.contracts.WeaponDTOConverter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("fighters")
public class FightersController implements RESTController<FighterDTO> {
    @Inject
    private FightersDAO fightersDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getAll() {
        try {
            return Response
                    .ok(fightersDAO.get().stream()
                            .map(FighterDTOConverter::makeDTO)
                            .collect(Collectors.toList()))
                    .build();
        } catch (PersistenceException e) {
            return Response
                    .status(400, e.getClass().getName() + ": " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getOne(@PathParam("id") int id) {
        try {
            Fighter fighter = fightersDAO.get(id);
            if (fighter == null)
                return Response.status(404, "Fighter id " + id + " not found").build();
            return Response
                    .ok(FighterDTOConverter.makeDTO(fighter))
                    .build();
        } catch (PersistenceException e) {
            return Response
                    .status(400, e.getClass().getName() + ": " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("{id}/taverns")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTaverns(@PathParam("id") int id) {
        try {
            Fighter f = fightersDAO.get(id);
            if (f == null)
                return Response.status(404, "Fighter id " + id + " not found").build();
            return Response
                    .ok(f.getTaverns().stream()
                            .map(TavernDTOConverter::makeDTO)
                            .collect(Collectors.toList()))
                    .build();
        } catch (PersistenceException e) {
            return Response
                    .status(400, e.getClass().getName() + ": " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("{id}/weapons")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWeapons(@PathParam("id") int id) {
        try {
            Fighter f = fightersDAO.get(id);
            if (f == null)
                return Response.status(404, "Fighter id " + id + " not found").build();
            return Response
                    .ok(f.getWeapons().stream()
                            .map(WeaponDTOConverter::makeDTO)
                            .collect(Collectors.toList()))
                    .build();
        } catch (PersistenceException e) {
            return Response
                    .status(400, e.getClass().getName() + ": " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response create(FighterDTO dto) {
        try {
            Fighter f = FighterDTOConverter.makeEntity(dto);
            fightersDAO.persist(f);
            dto.setId(f.getId());
            return Response.ok(dto).build();
        } catch (PersistenceException e) {
            return Response
                    .status(400, e.getClass().getName() + ": " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    @Transactional
    public Response update(@PathParam("id") int id, FighterDTO dto) {
        try {
            Fighter fighter = fightersDAO.get(id);
            if (fighter == null)
                return Response.status(404, "Fighter id " + id + " not found").build();
            FighterDTOConverter.updateEntity(fighter, dto);
            fightersDAO.update(fighter);
            dto.setId(id);
            return Response.ok(dto).build();
        } catch (PersistenceException e) {
            return Response
                    .status(400, e.getClass().getName() + ": " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    @Override
    public Response delete(@PathParam("id") int id) {
        try {
            Fighter f = fightersDAO.get(id);
            if (f != null)
                fightersDAO.delete(f);
            return Response.noContent().build();
        } catch (PersistenceException e) {
            return Response
                    .status(400, "Invalid id")
                    .build();
        }
    }
}
