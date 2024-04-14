package com.msc.inventairews.dao;

import com.msc.inventairews.entity.Boite;
import com.msc.inventairews.entity.Lieu;
import com.msc.inventairews.entity.Piece;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Michael
 */
public class UUIDInitDAO extends LieuDAO {

    public void init() {
        Session s = this.getSession();
        s.beginTransaction();
        Query q = s.createNativeQuery("INSERT INTO Lieu (uuid, lieu) values ('" + Lieu.ROOT_ID + "','Lieu inconnu')", Integer.class);
        q.executeUpdate();
        q = s.createNativeQuery("INSERT INTO Piece (uuid, nom) values ('" + Piece.ROOT_ID + "','Piece inconnue')", Integer.class);
        q.executeUpdate();
        q = s.createNativeQuery("INSERT INTO Boite (uuid, nom, rootBoite) values ('" + Boite.ROOT_ID + "','Boite inconnue', 1)", Integer.class);
        q.executeUpdate();
        s.getTransaction().commit();
        s.close();
    }

}
