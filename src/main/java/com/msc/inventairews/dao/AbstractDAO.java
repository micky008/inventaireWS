package com.msc.inventairews.dao;

import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Michael
 */
class AbstractDAO<T> {

    public synchronized T insert(T f) {

        Session session = HibernateFactory.getInstance().openSession();
        session.beginTransaction();
        try {
            session.persist(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();

        return f;
    }

    public synchronized List<T> insert(List<T> lm) {
        Session session = HibernateFactory.getInstance().openSession();
        session.beginTransaction();

        for (T f : lm) {
            session.persist(f);
        }

        session.getTransaction().commit();
        session.close();
        return lm;
    }

    public synchronized T[] insert(T[] lm) {
        Session session = HibernateFactory.getInstance().openSession();
        session.beginTransaction();

        for (T f : lm) {
            session.persist(f);
        }

        session.getTransaction().commit();
        session.close();
        return lm;
    }

    public synchronized T update(T m) {

        Session session = HibernateFactory.getInstance().openSession();
        session.beginTransaction();

        session.merge(m);

        session.getTransaction().commit();
        session.close();
        return m;
    }

    public synchronized List<T> update(List<T> m) {

        Session session = HibernateFactory.getInstance().openSession();
        session.beginTransaction();

        for (T t : m) {
            session.merge(t);
        }

        session.getTransaction().commit();
        session.close();
        return m;
    }

    public synchronized T[] update(T[] m) {

        Session session = HibernateFactory.getInstance().openSession();
        session.beginTransaction();

        for (T t : m) {
            session.merge(t);
        }

        session.getTransaction().commit();
        session.close();
        return m;
    }

    public synchronized T delete(T m) {

        Session session = HibernateFactory.getInstance().openSession();
        session.beginTransaction();

        session.remove(m);

        session.getTransaction().commit();
        session.close();
        return m;
    }

    public synchronized List<T> delete(List<T> m) {
        Session session = HibernateFactory.getInstance().openSession();
        session.beginTransaction();

        for (T t : m) {
            session.remove(t);
        }
        session.getTransaction().commit();
        session.close();
        return m;
    }

    public synchronized T[] delete(T[] m) {
        Session session = HibernateFactory.getInstance().openSession();
        session.beginTransaction();

        for (T t : m) {
            session.remove(t);
        }

        session.getTransaction().commit();
        session.close();
        return m;
    }

    protected T getObject(String hql) {
        Session session = HibernateFactory.getInstance().openSession();
        session.beginTransaction();
        T obj = (T) session.createQuery(hql).uniqueResult();
        session.getTransaction().commit();
        session.close();
        return obj;
    }

    protected List<T> getObjects(String hql) {
        Session session = HibernateFactory.getInstance().openSession();
        session.beginTransaction();
        List<T> obj = session.createQuery(hql).list();
        session.getTransaction().commit();
        session.close();
        return obj;
    }

    protected Session getSession() {
        return HibernateFactory.getInstance().openSession();
    }
}
