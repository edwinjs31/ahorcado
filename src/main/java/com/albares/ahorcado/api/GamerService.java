package com.albares.ahorcado.api;

import com.albares.ahorcado.utils.Parameters;
import com.albares.ahorcado.utils.JWTUtils;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/gamer")
public class GamerService {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGamer(Gamer newGamer) {
        try {
            Response r = new Response();
            Gamer g = new Gamer();
            g.setToken(JWTUtils.generateToken(Parameters.match.addGamer(newGamer)));
            r.setGamer(g);
            r.setResponseCode(1);
            return r;
        } catch (Exception e) {
            Response r = new Response();
            r.setResponseCode(0);
            return r;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser() {
        try {
            Response r = new Response();
            r.setGamers(Parameters.match.getGamers());
            r.setResponseCode(1);
            return r;
        } catch (Exception e) {
            Response r = new Response();
            r.setResponseCode(0);
            return r;
        }

    }
}
