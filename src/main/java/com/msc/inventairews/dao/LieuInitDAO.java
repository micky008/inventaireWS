package com.msc.inventairews.dao;

import com.msc.inventairews.entity.Lieu;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Michael
 */
public class LieuInitDAO extends LieuDAO {

    public void init() {
        Session s = this.getSession();
        s.beginTransaction();
        Query q = s.createNativeQuery("INSERT INTO Lieu (uuid, lieu) values ('"+Lieu.ROOT_ID+"','A Ranger')", Integer.class);
        q.executeUpdate();
        s.getTransaction().commit();
        s.close();
    }

}
