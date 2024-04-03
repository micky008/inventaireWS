package com.msc.inventairews.controller;

import com.msc.inventairews.dao.StuffDAO;
import com.msc.inventairews.entity.Stuff;
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
@Path("api/stuff")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StuffController {

    @GET
    public List<Stuff> getAllStuff() {
        StuffDAO ldao = new StuffDAO();
        return ldao.getAll();
    }

    @PUT
    public Stuff insert(Stuff b) {
        StuffDAO bdao = new StuffDAO();
        return bdao.insert(b);
    }

    @POST
    public Stuff update(Stuff b) {
        StuffDAO bdao = new StuffDAO();
        return bdao.update(b);
    }

    @DELETE
    public Stuff delete(Stuff b) {
        StuffDAO bdao = new StuffDAO();
        return bdao.delete(b);
    }
}
