package com.msc.inventairews.dao;

import com.msc.inventairews.entity.Boite;
import com.msc.inventairews.entity.Stuff;
import java.util.List;

/**
 *
 * @author Michael
 */
public class StuffDAO extends AbstractDAO<Stuff> {

    public List<Stuff> getAll() {
        return this.getObjects("from Stuff");
    }
    
    public List<Stuff> getStuffByBoite(Boite boite) {
        String sql = "select stuff from Stuff as stuff, Boite as boite where boite.uuid='%1'";
        sql = sql.replace("%1", boite.getUuid());
        try{
        List<Stuff> ls = this.getObjects(sql);
       return ls;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

//    @Override
//    public synchronized Stuff[] insert(Stuff[] lm) {
//         for (Stuff s : lm) {
//            if (s.getTags() != null && !s.getTags().isEmpty()) {
//                TagDAO tdao = new TagDAO();
//                List<Tag> t1 = tdao.insert(extractNewTag(s.getTags()));
//                List<Tag> t2 = tdao.getObjects("from Tag where uuid in " + agregUUID(s.getTags()));
//                t1.addAll(t2);
//                s.setTags(t1);
//            }
//        }
//        return super.insert(lm);
//    }
//
//    @Override
//    public synchronized List<Stuff> insert(List<Stuff> lm) {
//        for (Stuff s : lm) {
//            if (s.getTags() != null && !s.getTags().isEmpty()) {
//                TagDAO tdao = new TagDAO();
//                List<Tag> t1 = tdao.insert(extractNewTag(s.getTags()));
//                List<Tag> t2 = tdao.getObjects("from Tag where uuid in " + agregUUID(s.getTags()));
//                t1.addAll(t2);
//                s.setTags(t1);
//            }
//        }
//        return super.insert(lm); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
//    }
//
//    @Override
//    public synchronized Stuff insert(Stuff s) {
//        if (s.getTags() != null && !s.getTags().isEmpty()) {
//            TagDAO tdao = new TagDAO();
//            List<Tag> t1 = tdao.insert(extractNewTag(s.getTags()));
//            List<Tag> t2 = tdao.getObjects("from Tag where uuid in " + agregUUID(s.getTags()));
//            t1.addAll(t2);
//            s.setTags(t1);
//        }
//        return super.insert(s); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
//    }
//
//    private List<Tag> extractNewTag(List<Tag> tags) {
//        List<Tag> res = new ArrayList<>();
//        for (Tag t : tags) {
//            if (t.getUuid() == null || t.getUuid().isEmpty()) {
//                res.add(t);
//            }
//        }
//        return res;
//    }
//
//    private String agregUUID(List<Tag> tags) {
//        StringBuilder sb = new StringBuilder();
//        sb.append('(');
//        int index = 0;
//        for (Tag t : tags) {
//            if (t.getUuid() != null && !t.getUuid().isEmpty()) {
//                index += t.getUuid().length() + 1;
//                sb.append(t.getUuid());
//                sb.append(',');
//            }
//        }
//        sb = sb.deleteCharAt(index);
//        sb.append(')');
//        return sb.toString();
//    }

}
