package com.msc.inventairews.controller;

import com.msc.inventairews.dao.TagDAO;
import com.msc.inventairews.entity.Tag;
import com.msc.inventairews.entity.Tag;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Michael
 */
@Path("tag")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TagController {

    @GET
    public List<Tag> getAll() {
        TagDAO ldao = new TagDAO();
        return ldao.getAll();
    }
    
    @PUT
    public Tag insert(Tag b) {
        TagDAO bdao = new TagDAO();
        return bdao.insert(b);
    }
    
    @POST
    public Tag update(Tag b) {
        TagDAO bdao = new TagDAO();
        return bdao.update(b);
    }
    
    @DELETE
    public Tag delete(Tag b) {
        TagDAO bdao = new TagDAO();
        return bdao.delete(b);
    }
}
