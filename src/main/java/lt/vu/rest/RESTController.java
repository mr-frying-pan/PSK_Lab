package lt.vu.rest;

import javax.ws.rs.core.Response;

public interface RESTController<E> {
    Response getAll();

    Response getOne(int id);

    Response create(E entity);

    Response update(int id, E entity);

    Response delete(int id);
}
