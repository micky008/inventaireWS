package com.msc.inventairews.controller;

import com.msc.inventairews.dao.StuffDAO;
import com.msc.inventairews.entity.Boite;
import com.msc.inventairews.entity.Stuff;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Michael
 */
@Path("api/stuff")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StuffController {

    private StuffDAO sdao = new StuffDAO();
    
    @GET
    public List<Stuff> getAllStuff() {
        return sdao.getAll();
    }

    @GET
    @Path("{uuidBoite}")
    public List<Stuff> getAllStuffByBoite(@PathParam("uuidBoite") String uuid) {
        Boite b = new Boite();
        b.setUuid(uuid);
        return sdao.getStuffByBoite(b);
    }

    @PUT
    public Stuff insert(Stuff b) {
        return sdao.insert(b);
    }

    @POST
    public Stuff update(Stuff b) {
        return sdao.update(b);
    }

    @DELETE
    public Stuff delete(Stuff b) {
        return sdao.delete(b);
    }
}
