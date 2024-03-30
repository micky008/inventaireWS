package com.msc.inventairews.dao;

import com.msc.inventairews.entity.Lieu;
import java.util.List;

/**
 *
 * @author Michael
 */
public class LieuDAO extends AbstractDAO<Lieu>{

    public List<Lieu> getAll(){
        return this.getObjects("from Lieu");
    }
    
}
