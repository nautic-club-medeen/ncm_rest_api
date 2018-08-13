package com.ncmrestapi.resources;

import com.ncmrestapi.persistences.RolesEntity;
import com.ncmrestapi.utils.HibernateUtil;
import org.hibernate.Session;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by marom on 27/09/16.
 */
@Path("roles")
public class Roles {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getRoles() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        List<RolesEntity> roles = session.createQuery("from RolesEntity ").getResultList();
        session.getTransaction().commit();

        GenericEntity<List<RolesEntity>> generic = new GenericEntity< List< RolesEntity > >( roles ) { };
        return Response.ok( generic ).build();
    }

    /*@GET
    @Path("{id}")
    public Response getRole(@PathParam("id") int id) {
        Session session = HibernateUtil.getSessionJavaConfigFactory().getCurrentSession();

        session.beginTransaction();

        RolesEntity role = session.createQuery("from RolesEntity R where R.id = id ").getSingleResult();
        session.getTransaction().commit();

        GenericEntity<List<RolesEntity>> genericRoles = new GenericEntity<List<RolesEntity>>(roles) {};
        return Response.ok(genericRoles).build();
    }*/
}
