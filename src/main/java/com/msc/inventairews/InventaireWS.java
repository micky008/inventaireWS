package com.msc.inventairews;

import com.msc.inventairews.dao.BoiteDAO;
import com.msc.inventairews.dao.HibernateFactory;
import com.msc.inventairews.dao.LieuDAO;
import com.msc.inventairews.dao.LieuInitDAO;
import com.msc.inventairews.dao.StuffDAO;
import com.msc.inventairews.dao.TagDAO;
import com.msc.inventairews.entity.Boite;
import com.msc.inventairews.entity.Lieu;
import com.msc.inventairews.entity.Stuff;
import com.msc.inventairews.entity.Tag;
import com.msc.inventairews.providers.CORSFilter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Michael
 */
public class InventaireWS {

    public void init() {
        LieuInitDAO ld = new LieuInitDAO();
        ld.init();
        Lieu l2 = new Lieu();
        l2.setLieu("Toto");
        ld.insert(l2);
    }

    public void test() {
        BoiteDAO bdao = new BoiteDAO();
        TagDAO tdao = new TagDAO();
        StuffDAO sdao = new StuffDAO();
        LieuDAO ld = new LieuDAO();

        Lieu l = ld.getAll().get(1); //TOTO

        Tag tag1Boite1 = new Tag();
        Tag tag2Boite1 = new Tag();
        tag1Boite1.setTag("tournevis");
        tag2Boite1.setTag("crusi");
        tag1Boite1 = tdao.insert(tag1Boite1);
        tag2Boite1 = tdao.insert(tag2Boite1);
        List<Tag> tags1 = new ArrayList<>(2);
        List<Tag> tags2 = new ArrayList<>(2);
        tags1.add(tag2Boite1);
        tags1.add(tag1Boite1);

        Stuff stuff1 = new Stuff();
        stuff1.setQuantite(1);
        stuff1.setDesrc("tournevis crusi");
        stuff1.setTags(tags1);
        stuff1 = sdao.insert(stuff1);

        List<Stuff> listStuff = new ArrayList<>(1);
        listStuff.add(stuff1);

        Boite boite1 = new Boite();
        boite1.setLieu(l);
        boite1.setNom("boite 1");
        boite1.setStuffs(listStuff);
        boite1 = bdao.insert(boite1);

        ///////////////////////
        Tag tag3Boite1 = new Tag();
        tag3Boite1.setTag("plat");
        tag3Boite1 = tdao.insert(tag3Boite1);
        tags2.add(tag3Boite1);
        tags2.add(tag1Boite1);

        Stuff stuff2 = new Stuff();
        stuff2.setQuantite(1);
        stuff2.setDesrc("tournevis plat");
        stuff2.setTags(tags2);
        stuff2 = sdao.insert(stuff2);

        boite1.getStuffs().add(stuff2);

        bdao.update(boite1);

    }

    public static void main(String[] args) {
        Config.init();
        HibernateFactory.setUp();
        InventaireWS main = new InventaireWS();
        main.init();
        main.test();

        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        ResourceConfig config = new ResourceConfig();
        config.packages(true, "com.msc.inventairews.controller");        
        config.register(CORSFilter.class);        
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config, true);
    }
}
