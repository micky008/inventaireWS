package com.msc.inventairews;

import com.msc.inventairews.controller.BoiteController;
import com.msc.inventairews.dao.HibernateFactory;
import com.msc.inventairews.dao.LieuDAO;
import com.msc.inventairews.entity.Lieu;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Michael
 */
public class InventaireWS {

    public static void main(String[] args) {        
        Config.init();
        HibernateFactory.setUp();
        LieuDAO ld = new LieuDAO();
        Lieu l = new Lieu();
        l.setLieu("Grenier");
        l = ld.insert(l);
        System.out.println(l.getUuid());
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        ResourceConfig config = new ResourceConfig(BoiteController.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config, true);
    }
}
