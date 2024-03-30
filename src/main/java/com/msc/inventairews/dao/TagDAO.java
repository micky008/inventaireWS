package com.msc.inventairews.dao;

import com.msc.inventairews.entity.Lieu;
import com.msc.inventairews.entity.Tag;
import java.util.List;

/**
 *
 * @author Michael
 */
public class TagDAO extends AbstractDAO<Tag>{

    public List<Tag> getAll(){
        return this.getObjects("from Tag");
    }
    
}
