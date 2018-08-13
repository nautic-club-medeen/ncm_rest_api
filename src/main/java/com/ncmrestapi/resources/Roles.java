package com.ncmrestapi.resources;

import com.ncmrestapi.persistences.RoleEntity;
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

        List<RoleEntity> roles = session.createQuery("from RolesEntity ").getResultList();
        session.getTransaction().commit();

        GenericEntity<List<RoleEntity>> generic = new GenericEntity< List<RoleEntity> >( roles ) { };
        return Response.ok( generic ).build();
    }

    /*@GET
    @Path("{id}")
    public Response getRole(@PathParam("id") int id) {
        Session session = HibernateUtil.getSessionJavaConfigFactory().getCurrentSession();

        session.beginTransaction();

        RoleEntity role = session.createQuery("from RoleEntity R where R.id = id ").getSingleResult();
        session.getTransaction().commit();

        GenericEntity<List<RoleEntity>> genericRoles = new GenericEntity<List<RoleEntity>>(roles) {};
        return Response.ok(genericRoles).build();
    }*/
}
