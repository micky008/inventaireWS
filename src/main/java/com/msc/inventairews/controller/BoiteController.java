package com.msc.inventairews.controller;

import com.msc.inventairews.dao.BoiteDAO;
import com.msc.inventairews.dao.PieceDAO;
import com.msc.inventairews.entity.Boite;
import com.msc.inventairews.entity.Piece;
import java.util.Iterator;
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

    BoiteDAO bdao = new BoiteDAO();

    @GET
    public List<Boite> getAllRootBoites() {
        return bdao.getAllRootBoites();
    }

    @GET
    @Path("{uuidPiece}/noRoot")
    public List<Boite> getAllBoitesByPiece(@PathParam("uuidPiece") String uuidpiece) {
        return bdao.getAllBoitesByPiece(new Piece(uuidpiece));
    }

    @GET
    @Path("{uuidPiece}/stuff")
    public List<Boite> getAllBoiteByPieceWithStuff(@PathParam("uuidPiece") String uuidpiece) {
        return bdao.getAllBoitesWithStuff(new Piece(uuidpiece));
    }

    @GET
    @Path("{uuidPiece}")
    public List<Boite> getAllRootBoiteByPiece(@PathParam("uuidPiece") String uuidpiece) {
        return bdao.getAllRootBoitesByPiece(new Piece(uuidpiece));
    }

    @PUT
    @Path("{uuidPiece}")
    public Boite insertRootBoite(@PathParam("uuidPiece") String uuidPiece, Boite newB) {
        PieceDAO pdao = new PieceDAO();
        Piece piece = pdao.get(uuidPiece);
        newB.setUuid(null);
        newB.setPiece(piece);
        return bdao.insert(newB);
    }

    @PUT
    @Path("{uuidBoiteParent}/child")
    public Boite insertChild(@PathParam("uuidBoiteParent") String uuidBoiteParent, Boite newB) {
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
    public Boite updatePiece(Boite b) {
        PieceDAO pdao = new PieceDAO();

        Boite oldBoite = bdao.get(b.getUuid());
        Piece newPiece = pdao.get(b.getPiece().getUuid());

        oldBoite.setNote(b.getNote());
        oldBoite.setPiece(newPiece);

        return bdao.update(oldBoite);
    }

    @POST
    @Path("{newRootBoiteUuid}")
    public Boolean moveBoiteParent(@PathParam("newRootBoiteUuid") String uuid, Boite bFromUser) {
        try {
            Boite oldBoite = bdao.get(bFromUser.getUuid());
            Boite newRootBoite = bdao.get(uuid);
            oldBoite.setPiece(newRootBoite.getPiece());
            oldBoite.setRootBoite(false);
            bdao.update(oldBoite);
            Boite parentBoite = bdao.getParent(oldBoite);
            if (parentBoite != null) {
                Iterator<Boite> itb = parentBoite.getBoites().iterator();
                while (itb.hasNext()) {
                    Boite b = itb.next();
                    if (b.getUuid().equals(oldBoite.getUuid())) {
                        itb.remove();
                        break;
                    }
                }
                bdao.update(parentBoite);
            }
            newRootBoite.getBoites().add(oldBoite);
            bdao.update(newRootBoite);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @DELETE
    @Path("{uuidBoite}")
    public Boite delete(@PathParam("uuidBoite") String uuid) {
        Boite toDelete = bdao.get(uuid);
        try {
            return bdao.delete(toDelete);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Boite();
    }
}
