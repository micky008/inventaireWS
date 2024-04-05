package com.msc.inventairews.controller;

import com.msc.inventairews.dao.BoiteDAO;
import com.msc.inventairews.dao.LieuDAO;
import com.msc.inventairews.entity.Boite;
import com.msc.inventairews.entity.Lieu;
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
@Path("api/lieu")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LieuController {

    @GET
    public List<Lieu> getAll() {
        LieuDAO ldao = new LieuDAO();
        return ldao.getAll();

    }

    @PUT
    public Lieu insert(Lieu b) {
        LieuDAO bdao = new LieuDAO();
        b.setUuid(null);
        return bdao.insert(b);
    }

    @POST
    public Lieu update(Lieu b) {
        LieuDAO bdao = new LieuDAO();
        Lieu oldLieu = bdao.get(b.getUuid());
        oldLieu.setLieu(b.getLieu());
        return bdao.update(oldLieu);
    }

    @DELETE
    @Path("{id}")
    public Lieu delete(@PathParam("id") String uuid) {
        LieuDAO ldao = new LieuDAO();
        BoiteDAO bdao = new BoiteDAO();
        Lieu root = ldao.get(Lieu.ROOT_ID);
        Lieu old = ldao.get(uuid);
        List<Boite> boites = bdao.getAllBoitesbyLieu(old);
        for (Boite boite : boites) {
            boite.setLieu(root);
        }
        bdao.update(boites);
        return ldao.delete(old);

    }
}
