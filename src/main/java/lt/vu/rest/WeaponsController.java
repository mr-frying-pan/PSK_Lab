package lt.vu.rest;

import lt.vu.entities.Fighter;
import lt.vu.entities.Weapon;
import lt.vu.persistence.FightersDAO;
import lt.vu.persistence.WeaponsDAO;
import lt.vu.rest.contracts.WeaponDTO;
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
@Path("weapons")
public class WeaponsController implements RESTController<WeaponDTO> {
    @Inject
    private WeaponsDAO weaponsDAO;

    @Inject
    private FightersDAO fightersDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getAll() {
        try {
            return Response
                    .ok(weaponsDAO.get().stream()
                            .map(WeaponDTOConverter::makeDTO)
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
    public Response getOne(int id) {
        try {
            Weapon w = weaponsDAO.get(id);
            if (w == null)
                return Response.status(404, "Weapon id " + id + " not found").build();
            return Response
                    .ok(WeaponDTOConverter.makeDTO(w))
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
    public Response create(WeaponDTO dto) {
        try {
            Weapon weapon = WeaponDTOConverter.makeEntity(dto);
            if (dto.getOwnerName() != null) {
                Fighter owner = fightersDAO.get(dto.getOwnerName());
                if (owner != null)
                    weapon.setOwner(owner);
            }
            weaponsDAO.persist(weapon);
            dto.setId(weapon.getId());
            return Response.ok(dto).build();
        } catch (Exception e) {
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
    public Response update(@PathParam("id") int id, WeaponDTO dto) {
        try {
            Weapon weapon = weaponsDAO.get(id);
            if (weapon == null)
                return Response.status(404, "Weapin id " + id + " not found").build();
            WeaponDTOConverter.updateEntity(weapon, dto);
            if (dto.getOwnerName() != null) {
                Fighter owner = fightersDAO.get(dto.getOwnerName());
                if (owner != null)
                    weapon.setOwner(owner);
            } else {
                weapon.setOwner(null);
            }
            weaponsDAO.update(weapon);
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
            Weapon w = weaponsDAO.get(id);
            if (w != null)
                weaponsDAO.delete(w);
            return Response.noContent().build();
        } catch (PersistenceException e) {
            return Response
                    .status(400, e.getClass().getName() + ": " + e.getMessage())
                    .build();
        }
    }
}
