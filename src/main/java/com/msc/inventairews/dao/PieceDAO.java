package com.msc.inventairews.dao;

import com.msc.inventairews.entity.Lieu;
import com.msc.inventairews.entity.Piece;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;

/**
 *
 * @author Michael
 */
public class PieceDAO extends AbstractDAO<Piece> {

    public List<Piece> getAll(boolean initializeLieu) {
        List<Piece> pieces = this.getObjects("from Piece");
        if (initializeLieu) {
            for (Piece p : pieces) {
                Hibernate.initialize(p);
            }
        }
        return pieces;
    }

    public Piece get(String uuid) {
        return this.getObject("from Piece where uuid='" + uuid + "'");
    }

    public List<Piece> getByLieu(Lieu l) {
        List<Piece> lp = this.getObjects("from Piece where lieu.uuid='" + l.getUuid() + "'");
        for (Piece p : lp) {
            p.setLieu(null);
        }
        return lp;
    }

    public synchronized Piece deletebyUUID(String uuid) {
        Session session = HibernateFactory.getInstance().openSession();
        session.beginTransaction();
        Piece lieu = session.byId(Piece.class).load(uuid);
        lieu = super.delete(lieu);
        session.getTransaction().commit();
        session.close();
        return lieu;
    }

}
