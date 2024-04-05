package com.msc.inventairews.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import java.util.Objects;
import org.hibernate.annotations.UuidGenerator;

/**
 *
 * @author Michael
 */
@Entity
public class Piece {

    @Transient
    public static final String ROOT_ID = "00000000-0000-0000-0000-000000000001";

    @Id
    @UuidGenerator
    private String uuid;

    private String nom;

    @OneToOne(fetch = FetchType.LAZY)
    private Lieu lieu;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.uuid);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Piece other = (Piece) obj;
        return Objects.equals(this.uuid, other.uuid);
    }

    
    
}
