package com.antt.hibernate.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by antt on 4/26/15.
 */
public class Stock implements Serializable {
    private Integer stockId;
    private String stockCode;
    private String stockName;
    private StockDetail stockDetail;
    private Set<StockDailyRecord> stockDailyRecords = new HashSet<StockDailyRecord>();
    private Set<Category> categories = new HashSet<Category>();

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<StockDailyRecord> getStockDailyRecords() {
        return stockDailyRecords;
    }

    public void setStockDailyRecords(Set<StockDailyRecord> stockDailyRecords) {
        this.stockDailyRecords = stockDailyRecords;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public StockDetail getStockDetail() {
        return stockDetail;
    }

    public void setStockDetail(StockDetail stockDetail) {
        this.stockDetail = stockDetail;
    }

    public String getStockCode() {

        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }
}
