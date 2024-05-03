package com.msc.inventairews;

import com.msc.inventairews.dao.BoiteDAO;
import com.msc.inventairews.dao.HibernateFactory;
import com.msc.inventairews.dao.LieuDAO;
import com.msc.inventairews.dao.UUIDInitDAO;
import com.msc.inventairews.dao.PieceDAO;
import com.msc.inventairews.dao.StuffDAO;
import com.msc.inventairews.dao.TagDAO;
import com.msc.inventairews.entity.Boite;
import com.msc.inventairews.entity.Lieu;
import com.msc.inventairews.entity.Piece;
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
        UUIDInitDAO uuidinit = new UUIDInitDAO();
        uuidinit.init();
        LieuDAO ldao = new LieuDAO();
        PieceDAO pdao = new PieceDAO();
        BoiteDAO bdao = new BoiteDAO();

        Lieu l1 = ldao.get(Lieu.ROOT_ID);
        Piece p1 = pdao.get(Piece.ROOT_ID);
        p1.setLieu(l1);
        p1 = pdao.update(p1);
        Boite b1 = bdao.get(Boite.ROOT_ID);
        b1.setPiece(p1);
        bdao.update(b1);

        Lieu l2 = new Lieu();
        l2.setLieu("Residence Principal");
        l2 = ldao.insert(l2);
        Piece p = new Piece();
        p.setLieu(l2);
        p.setNom("Grenier");
        p.setUuid(null);
        p = pdao.insert(p);
        
        Piece p2 = new Piece();
        p2.setLieu(l2);
        p2.setNom("Garage");
        p2.setUuid(null);
        pdao.insert(p2);
        
        Boite b = new Boite();
        b.setNom("Ma 1ere boite");
        b.setPiece(p);
        b.setUuid(null);
        bdao.insert(b);
    }

    public void test() {
        BoiteDAO bdao = new BoiteDAO();
        TagDAO tdao = new TagDAO();
        StuffDAO sdao = new StuffDAO();
        PieceDAO pdao = new PieceDAO();

        Piece grenier = pdao.getAll().get(1); //grenier de Residence principal

        /////////INIT TAG///////////
        Tag tagTournevis = new Tag();
        Tag tagCrusi = new Tag();
        Tag tagPlat = new Tag();
        Tag tagPrecision = new Tag();
        Tag tagPerceuse = new Tag();

        tagPrecision.setTag("Precision");
        tagTournevis.setTag("tournevis");
        tagCrusi.setTag("crusi");
        tagPlat.setTag("plat");
        tagPerceuse.setTag("perceuse");

        tagTournevis = tdao.insert(tagTournevis);
        tagCrusi = tdao.insert(tagCrusi);
        tagPlat = tdao.insert(tagPlat);
        tagPrecision = tdao.insert(tagPrecision);
        tagPerceuse = tdao.insert(tagPerceuse);

        List<Tag> tagsTournevisCrucis = new ArrayList<>(2);
        List<Tag> tagsTournevisPlat = new ArrayList<>(2);
        List<Tag> tagsTournevisCrusiPresison = new ArrayList<>(3);
        List<Tag> tagsPerceuse = new ArrayList<>(1);

        tagsTournevisCrucis.add(tagTournevis);
        tagsTournevisCrucis.add(tagCrusi);

        tagsTournevisPlat.add(tagTournevis);
        tagsTournevisPlat.add(tagPlat);

        tagsTournevisCrusiPresison.add(tagTournevis);
        tagsTournevisCrusiPresison.add(tagCrusi);
        tagsTournevisCrusiPresison.add(tagPrecision);

        tagsPerceuse.add(tagPerceuse);

        ///////////////Init tournevis plat, crusi, precision 
        Stuff stuffTournevisCrusi = new Stuff();
        stuffTournevisCrusi.setQuantite(1);
        stuffTournevisCrusi.setDesrc("tournevis crusi");
        stuffTournevisCrusi.setTags(tagsTournevisCrucis);
        stuffTournevisCrusi = sdao.insert(stuffTournevisCrusi);

        Stuff stuffTournevisPlat = new Stuff();
        stuffTournevisPlat.setQuantite(1);
        stuffTournevisPlat.setDesrc("tournevis plat");
        stuffTournevisPlat.setTags(tagsTournevisPlat);
        stuffTournevisPlat = sdao.insert(stuffTournevisPlat);

        Stuff stuffTournevisCrusiPrecision = new Stuff();
        stuffTournevisCrusiPrecision.setQuantite(1);
        stuffTournevisCrusiPrecision.setDesrc("tournevis crusi precision");
        stuffTournevisCrusiPrecision.setTags(tagsTournevisCrusiPresison);
        stuffTournevisCrusiPrecision = sdao.insert(stuffTournevisCrusiPrecision);

        Stuff perceuse = new Stuff();
        perceuse.setQuantite(1);
        perceuse.setDesrc("perceuse");
        perceuse.setTags(tagsPerceuse);
        perceuse = sdao.insert(perceuse);

        List<Stuff> listStuffBoite1 = new ArrayList<>(2);
        List<Stuff> listStuffBoite2Perceuse = new ArrayList<>(1);
        List<Stuff> listStuffBoiteinBoite = new ArrayList<>(1);

        listStuffBoite1.add(stuffTournevisCrusi);
        listStuffBoite1.add(stuffTournevisPlat);
        listStuffBoiteinBoite.add(stuffTournevisCrusiPrecision);
        listStuffBoite2Perceuse.add(perceuse);

        ///////////Boite dans une boite//////////////
        Boite boite1 = bdao.getAllBoitesByPiece(grenier).get(0);
        boite1.setStuffs(listStuffBoite1);
        boite1.setPiece(grenier);
        boite1.setNom("grande boite");

        Boite inBoite = new Boite("petite boite");
        inBoite.setStuffs(listStuffBoiteinBoite);
        inBoite.setPiece(boite1.getPiece());
        inBoite.setRootBoite(false);
        inBoite = bdao.insert(inBoite);
        List<Boite> lInBoite = new ArrayList<>(1);
        lInBoite.add(inBoite);

        boite1.setBoites(lInBoite);
        bdao.update(boite1);

        ///////////////////////BOITE 2
        Boite boite2 = new Boite("Boite a outils");
        boite2.setStuffs(listStuffBoite2Perceuse);
        boite2.setPiece(grenier);
        bdao.insert(boite2);

    }

    public void test2() {
        PieceDAO pdao = new PieceDAO();
        Piece grenier = pdao.getAll().get(1); //grenier de Residence principal
        BoiteDAO bdao = new BoiteDAO();

        Boite plop = new Boite("plop");
        Boite q = new Boite("q");
        Boite w = new Boite("w");
        Boite a = new Boite("a");
        Boite s = new Boite("s");
        Boite d = new Boite("d");
        q.setRootBoite(false);
        w.setRootBoite(false);
        a.setRootBoite(false);
        s.setRootBoite(false);
        d.setRootBoite(false);
        plop.setPiece(grenier);
        q.setPiece(grenier);
        w.setPiece(grenier);
        a.setPiece(grenier);
        s.setPiece(grenier);
        d.setPiece(grenier);

        plop = bdao.insert(plop);
        d = bdao.insert(d);
        s = bdao.insert(s);
        a = bdao.insert(a);
        w = bdao.insert(w);

        plop.setBoites(new ArrayList<>(2));
        q.setBoites(new ArrayList<>(3));
        q.getBoites().add(a);
        q.getBoites().add(s);
        q.getBoites().add(d);

        q = bdao.insert(q);

        plop.getBoites().add(q);
        plop.getBoites().add(w);

        bdao.update(plop);

    }

    public static void main(String[] args) {
        Config.init();
        HibernateFactory.setUp();
        InventaireWS main = new InventaireWS();
        main.init();
        main.test();
        main.test2();

        URI baseUri = UriBuilder.fromUri("http://localhost/").port(Config.getInstance().getPort()).build();
        ResourceConfig config = new ResourceConfig();
        config.packages(true, "com.msc.inventairews.controller");
        config.register(CORSFilter.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config, true);
    }
}
