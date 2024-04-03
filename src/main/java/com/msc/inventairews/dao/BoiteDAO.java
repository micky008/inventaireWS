package com.msc.inventairews.dao;

import com.msc.inventairews.entity.Boite;
import com.msc.inventairews.entity.Lieu;
import java.util.List;

/**
 *
 * @author Michael
 */
public class BoiteDAO extends AbstractDAO<Boite> {

    public List<Boite> getAllBoites() {
        return getObjects("from Boite");
    }

    public List<Boite> getAllBoitesbyLieu(Lieu lieu) {
        return getObjects("from Boite where lieu.uuid='" + lieu.getUuid()+"'");
    }

}
