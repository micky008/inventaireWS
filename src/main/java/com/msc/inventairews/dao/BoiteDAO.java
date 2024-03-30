package com.msc.inventairews.dao;

import com.msc.inventairews.entity.Boite;
import java.util.List;

/**
 *
 * @author Michael
 */
public class BoiteDAO extends AbstractDAO<Boite> {

    public List<Boite> getAllBoites(){
        return getObjects("from Boite");
    }
    
    
    
}
