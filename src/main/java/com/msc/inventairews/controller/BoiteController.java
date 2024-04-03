package com.msc.inventairews.controller;

import com.msc.inventairews.dao.BoiteDAO;
import com.msc.inventairews.entity.Boite;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Michael
 */
@Path("api/boite")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BoiteController {

    @GET
    public List<Boite> getAll() {
        BoiteDAO bdao = new BoiteDAO();
        return bdao.getAllBoites();
    }

    @PUT
    public Boite insert(Boite b) {
        BoiteDAO bdao = new BoiteDAO();
        return bdao.insert(b);
    }

    @POST
    public Boite update(Boite b) {
        BoiteDAO bdao = new BoiteDAO();
        return bdao.update(b);
    }

    @DELETE
    public Boite delete(Boite b) {
        BoiteDAO bdao = new BoiteDAO();
        return bdao.delete(b);
    }
}
