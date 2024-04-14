package com.msc.inventairews.controller;

import com.msc.inventairews.dao.BoiteDAO;
import com.msc.inventairews.dao.PieceDAO;
import com.msc.inventairews.entity.Boite;
import com.msc.inventairews.entity.Piece;
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
@Path("api/boite")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BoiteController {

    @GET
    public List<Boite> getAll() {
        BoiteDAO bdao = new BoiteDAO();
        return bdao.getAllBoites();
    }

    @GET
    @Path("{uuidPiece}/stuff")
    public List<Boite> getAllByPieceWithStuff(@PathParam("uuidPiece") String uuidpiece) {
        BoiteDAO bdao = new BoiteDAO();
        return bdao.getAllBoitesWithStuff(new Piece(uuidpiece));
    }

    @GET
    @Path("{uuidPiece}")
    public List<Boite> getAllByPiece(@PathParam("uuidPiece") String uuidpiece) {
        BoiteDAO bdao = new BoiteDAO();
        return bdao.getAllBoitesByPiece(new Piece(uuidpiece));
    }

    @PUT
    @Path("{uuidPiece}")
    public Boite insertRootBoite(@PathParam("uuidPiece") String uuidPiece, Boite newB) {
        BoiteDAO bdao = new BoiteDAO();
        PieceDAO pdao = new PieceDAO();
        Piece piece = pdao.get(uuidPiece);
        newB.setUuid(null);
        newB.setPiece(piece);
        return bdao.insert(newB);
    }

    @PUT
    @Path("{uuidBoiteParent}/child")
    public Boite insertChild(@PathParam("uuidBoiteParent") String uuidBoiteParent, Boite newB) {
        BoiteDAO bdao = new BoiteDAO();
        Boite boiteParent = bdao.get(uuidBoiteParent);
        newB.setUuid(null);
        newB.setRootBoite(false);
        newB.setPiece(boiteParent.getPiece());
        newB = bdao.insert(newB);
        boiteParent.getBoites().add(newB);
        bdao.update(boiteParent);
        return newB;
    }


    @POST
    public Boite update(Boite b) {
        BoiteDAO bdao = new BoiteDAO();
        PieceDAO pdao = new PieceDAO();

        Boite oldBoite = bdao.get(b.getUuid());
        Piece newPiece = pdao.get(b.getPiece().getUuid());

        oldBoite.setPiece(newPiece);

        return bdao.update(b);
    }

    @DELETE
    public Boite delete(Boite b) {
        BoiteDAO bdao = new BoiteDAO();
        return bdao.delete(b);
    }
}
