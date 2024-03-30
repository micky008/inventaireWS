package com.msc.inventairews.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Objects;
import org.hibernate.annotations.UuidGenerator;

/**
 *
 * @author Michael
 */
@Entity
public class Tag {

    @Id
    @UuidGenerator
    private String uuid;

    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUuid() {
        return uuid;
    }

    public void UUID(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.uuid);
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
        final Tag other = (Tag) obj;
        return Objects.equals(this.uuid, other.uuid);
    }

}
