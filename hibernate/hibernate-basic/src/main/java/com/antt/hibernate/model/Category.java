package com.antt.hibernate.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by antt on 5/11/15.
 */
public class Category implements Serializable {
    private Integer categoryId;
    private String  name;
    private String  desc;
    private Set<Stock> stocks = new HashSet<Stock>();

    public Category() {}

    public Category(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Set<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
    }
}
