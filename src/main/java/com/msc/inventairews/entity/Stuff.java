package com.msc.inventairews.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.List;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Michael
 */
@Entity
public class Stuff {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid;

    private int quantite;
    private String desrc;

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(nullable = true)
    private List<Tag> tags;

    @Column(nullable = true)
    private String note;

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDesrc() {
        return desrc;
    }

    public void setDesrc(String desrc) {
        this.desrc = desrc;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

}
