package com.msc.inventairews.dao;

import com.msc.inventairews.entity.Lieu;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Michael
 */
public class LieuDAO extends AbstractDAO<Lieu> {

    public List<Lieu> getAll() {
        return this.getObjects("from Lieu");
    }

    public Lieu get(String uuid) {
        return this.getObject("from Lieu where uuid='"+uuid+"'");
    }
    
    public synchronized Lieu deletebyUUID(String uuid) {
        Session session = HibernateFactory.getInstance().openSession();
        session.beginTransaction();
        Lieu lieu = session.byId(Lieu.class).load(uuid);        
        lieu = super.delete(lieu);
        session.getTransaction().commit();
        session.close();
        return lieu;
    }

}
