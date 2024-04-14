package com.msc.inventairews.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import java.util.List;
import org.hibernate.annotations.UuidGenerator;

/**
 *
 * @author Michael
 */
@Entity
public class Boite {

    @Transient
    public static final String ROOT_ID = "00000000-0000-0000-0000-000000000001";

    @Id
    @UuidGenerator
    private String uuid;

    private String nom;

    @ManyToOne(fetch = FetchType.EAGER)    
    private Piece piece;

    @Column(nullable = true)
    private String note;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Stuff> stuffs;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Boite> boites;
    
    private boolean rootBoite = true;
    
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

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public List<Boite> getBoites() {
        return boites;
    }

    public void setBoites(List<Boite> boites) {
        this.boites = boites;
    }

    public boolean isRootBoite() {
        return rootBoite;
    }

    public void setRootBoite(boolean rootBoite) {
        this.rootBoite = rootBoite;
    }
}
