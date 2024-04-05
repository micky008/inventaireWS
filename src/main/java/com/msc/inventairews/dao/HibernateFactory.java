package com.msc.inventairews.dao;

import com.msc.inventairews.Config;
import com.msc.inventairews.entity.Boite;
import com.msc.inventairews.entity.Lieu;
import com.msc.inventairews.entity.Piece;
import com.msc.inventairews.entity.Stuff;
import com.msc.inventairews.entity.Tag;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Michael
 */
public class HibernateFactory {

    private static SessionFactory sessionFactory;

    public static void setUp() {

        Configuration cfg = new Configuration()
                .addAnnotatedClass(Boite.class)
                .addAnnotatedClass(Stuff.class)
                .addAnnotatedClass(Tag.class)
                .addAnnotatedClass(Lieu.class)
                .addAnnotatedClass(Piece.class)
                .setProperty("connection.driver_class", Config.getInstance().getSqlDriver())
                .setProperty("jakarta.persistence.jdbc.url", Config.getInstance().getJdbcURL())
                .setProperty("hibernate.connection.username", Config.getInstance().getUsername())
                .setProperty("hibernate.connection.password", Config.getInstance().getPassword())
                .setProperty("hibernate.dialect", Config.getInstance().getDialect())
                .setProperty("hibernate.connection.autocommit", "false")
                .setProperty("hibernate.connection.CharSet", "utf8")
                .setProperty("hibernate.connection.characterEncoding", "utf8")
                .setProperty("hibernate.connection.useUnicode", "true")
                .setProperty("hibernate.cache.provider_class", "org.hibernate.cache.internal.NoCacheProvider")
                .setProperty("show_sql", "false")
                .setProperty("hibernate.hbm2ddl.auto", "create");

        try {
            sessionFactory = cfg.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static SessionFactory getInstance() {
        if (sessionFactory == null) {
            setUp();
        }
        return sessionFactory;
    }
}
