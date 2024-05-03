package com.msc.inventairews.controller;

import com.msc.inventairews.dao.BoiteDAO;
import com.msc.inventairews.dao.LieuDAO;
import com.msc.inventairews.dao.PieceDAO;
import com.msc.inventairews.entity.Boite;
import com.msc.inventairews.entity.Lieu;
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
@Path("api/piece")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PieceController {

    @GET
    public List<Piece> getAll() {
        PieceDAO ldao = new PieceDAO();
        return ldao.getAll();
    }

    @GET
    @Path("lieu/{lieuUuid}")
    public List<Piece> getAllByLieu(@PathParam("lieuUuid") String uuidLieu) {
        PieceDAO pdao = new PieceDAO();
        LieuDAO ldao = new LieuDAO();
        Lieu l = ldao.get(uuidLieu);
        return pdao.getByLieu(l);
    }

    @PUT
    public Piece insert(Piece p) {
        PieceDAO bdao = new PieceDAO();
        LieuDAO ldao = new LieuDAO();
        p.setUuid(null);
        Lieu lieu = ldao.get(p.getLieu().getUuid());
        p.setLieu(lieu);
        return bdao.insert(p);
    }

    @POST
    public Piece update(Piece b) {
        PieceDAO bdao = new PieceDAO();
        LieuDAO ldao = new LieuDAO();
        Piece oldPiece = bdao.get(b.getUuid());
        oldPiece.setNom(b.getNom());
        Lieu lieu = ldao.get(b.getLieu().getUuid());
        oldPiece.setLieu(lieu);
        return bdao.update(oldPiece);
    }

    @DELETE
    @Path("{uuidPiece}")
    public Piece delete(@PathParam("uuidPiece") String uuidPiece) {
        PieceDAO pdao = new PieceDAO();
        BoiteDAO bdao = new BoiteDAO();

        Piece root = pdao.get(Piece.ROOT_ID);
        Piece oldPiece = pdao.get(uuidPiece);

        List<Boite> lboite = bdao.getAllBoitesByPiece(oldPiece);
        for (Boite b : lboite) {
            b.setPiece(root);
        }
        bdao.update(lboite);

        return oldPiece;

    }
}
