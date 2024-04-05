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

    public List<Boite> getAllBoites() {
        return getObjects("from Boite");
    }

    public List<Boite> getAllBoitesWithStuff(String uuidPiece) {
        return getObjects("from Boite JOIN FETCH stuffs where piece.uuid='" + uuidPiece + "'");
    }

    public List<Boite> getAllBoitesByPiece(Piece piece) {
        return getObjects("from Boite where piece.uuid='" + piece.getUuid() + "'");
    }

}
