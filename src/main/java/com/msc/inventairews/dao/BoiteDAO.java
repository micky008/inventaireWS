package com.msc.inventairews.dao;

import com.msc.inventairews.entity.Boite;
import com.msc.inventairews.entity.Piece;
import java.util.List;

/**
 *
 * @author Michael
 */
public class BoiteDAO extends AbstractDAO<Boite> {

    public Boite get(String uuid) {
        return getObject("from Boite where uuid='" + uuid + "'");
    }

    public List<Boite> getAllRootBoites() {
        return getObjects("from Boite where rootBoite=true");
    }

    public List<Boite> getAllBoitesByPiece(Piece piece) {
        List<Boite> lb = getObjects("from Boite where piece.uuid='" + piece.getUuid() + "'");
        for (Boite b : lb) {
            clean(b);
        }
        return lb;
    }

    public Boite getParent(Boite child) {
        return getObject("from Boite as b where element(b.boites).uuid = '" + child.getUuid() + "'");        
    }

   
    public List<Boite> getAllBoitesWithStuff(Piece piece) {
        return getObjects("from Boite JOIN FETCH stuffs where piece.uuid='" + piece.getUuid() + "'");
    }

    public List<Boite> getAllRootBoitesByPiece(Piece piece) {
        List<Boite> lb = getObjects("from Boite where piece.uuid='" + piece.getUuid() + "' and rootBoite=true");
        for (Boite b : lb) {
            clean(b);
        }
        return lb;
    }

    private void clean(Boite b) {
        b.setStuffs(null);
        b.getPiece().setLieu(null);
        if (b.getBoites() != null && !b.getBoites().isEmpty()) {
            for (Boite inBoite : b.getBoites()) {
                clean(inBoite);
            }
        } else {
            b.setBoites(null);
        }
    }

    public Boite delete(Boite b) {
        deepDelete(b);
        b = super.delete(b);
        return b;
    }

    private void deepDelete(Boite boite) {
        if (boite.getBoites() == null || boite.getBoites().isEmpty()) {
            super.delete(boite);
            return;
        }
        for (Boite b : boite.getBoites()) {
            deepDelete(b);
        }
        super.delete(boite);
    }

}
