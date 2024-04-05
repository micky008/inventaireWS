package com.msc.inventairews.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.List;
import org.hibernate.annotations.UuidGenerator;

/**
 *
 * @author Michael
 */
@Entity
public class Boite {

    @Id
    @UuidGenerator
    private String uuid;

    private String nom;

    @ManyToOne(fetch = FetchType.EAGER)
    private Lieu lieu;

    @Column(nullable = true)
    private String note;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Stuff> stuffs;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<Stuff> getStuffs() {
        return stuffs;
    }

    public void setStuffs(List<Stuff> stuffs) {
        this.stuffs = stuffs;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

}
