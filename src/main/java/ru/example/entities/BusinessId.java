package ru.example.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BusinessId {
    @Id
    private String id;

    private Long lastId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getLastId() {
        return lastId;
    }

    public void setLastId(Long lastId) {
        this.lastId = lastId;
    }
}
