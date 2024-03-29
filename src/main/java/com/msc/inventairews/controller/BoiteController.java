package com.msc.inventairews.controller;

import com.msc.inventairews.dao.BoiteDAO;
import com.msc.inventairews.entity.Boite;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Michael
 */
@Path("boite")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BoiteController {

    @GET
    public List<Boite> getAllBoites() {
        BoiteDAO bdao = new BoiteDAO();
        return bdao.getAllBoites();
    }
    
    @PUT
    public Boite insertBoite(Boite b) {
        BoiteDAO bdao = new BoiteDAO();
        return bdao.insert(b);
    }
    
}
