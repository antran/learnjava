package com.antt.hibernate.basic;

import java.io.Serializable;

/**
 * Created by antt on 4/25/15.
 */
public class Test implements Serializable {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[id] " + id + " [name] " + name;
    }
}
