package com.ncmrestapi.resources;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ncmrestapi.persistences.UserInfoEntity;
import com.ncmrestapi.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

@Path("/authentication")
public class Authentication {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(
            @QueryParam("email") String email,
            @QueryParam("password") String password) {

        System.out.println("email = " + email + ", password = " + password);

        try {

            // Issue a token for the user
            String token = issueToken(authenticate(email, password));

            // Return the token on the response
            return Response.ok(token).build();

        } catch (HibernateException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    private UserInfoEntity authenticate(String email, String password) throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        UserInfoEntity uc = (UserInfoEntity) session
                .createQuery("SELECT ui FROM UserInfoEntity ui WHERE ui.email = :email AND password = '"+ password + "'")
                .setParameter("email", email).getSingleResult();


        return uc;
    }

    private String issueToken(UserInfoEntity uie) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        return Jwts.builder()
                .setIssuer("ncm_rest_api")
                .setSubject(uie.getEmail())
                .claim("role_id", uie.getRoleId())
                .signWith(key).compact();
    }
}