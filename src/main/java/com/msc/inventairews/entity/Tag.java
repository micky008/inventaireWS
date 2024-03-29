package com.msc.inventairews.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Michael
 */
@Entity
public class Tag {

    @Id
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
