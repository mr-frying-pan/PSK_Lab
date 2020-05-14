package lt.vu.rest;

import lt.vu.entities.Tavern;
import lt.vu.persistence.TavernsDAO;
import lt.vu.rest.contracts.FighterDTOConverter;
import lt.vu.rest.contracts.TavernDTO;
import lt.vu.rest.contracts.TavernDTOConverter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("taverns")
public class TavernsController implements RESTController<TavernDTO> {
    @Inject
    private TavernsDAO tavernsDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getAll() {
        try {
            return Response
                    .ok(tavernsDAO.get().stream()
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
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getOne(@PathParam("id") int id) {
        try {
            Tavern t = tavernsDAO.get(id);
            if (t == null)
                return Response.status(404, "Tavern id " + id + " not found").build();
            return Response
                    .ok(TavernDTOConverter.makeDTO(t))
                    .build();
        } catch (PersistenceException e) {
            return Response
                    .status(400, e.getClass().getName() + ": " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("{id}/fighters")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFighters(@PathParam("id") int id) {
        try {
            Tavern t = tavernsDAO.get(id);
            if (t == null)
                return Response.status(404, "Tavern id " + id + " not found").build();
            return Response
                    .ok(t.getFighters().stream()
                            .map(FighterDTOConverter::makeDTO)
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
    public Response create(TavernDTO dto) {
        try {
            Tavern tavern = TavernDTOConverter.makeEntity(dto);
            tavernsDAO.persist(tavern);
            dto.setId(tavern.getId());
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
    public Response update(@PathParam("id") int id, TavernDTO dto) {
        try {
            Tavern tavern = tavernsDAO.get(id);
            if (tavern == null)
                return Response.status(404, "Tavern id " + id + " not found").build();
            TavernDTOConverter.updateEntity(tavern, dto);
            dto.setId(id);
            tavernsDAO.update(tavern);
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
            Tavern t = tavernsDAO.get(id);
            if (t != null)
                tavernsDAO.delete(t);
            return Response.noContent().build();
        } catch (PersistenceException e) {
            return Response
                    .status(400, e.getClass().getName() + ": " + e.getMessage())
                    .build();
        }
    }
}
