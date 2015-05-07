package com.antt.hibernate.oneone;

import java.io.Serializable;

/**
 * Created by antt on 4/26/15.
 */
public class Stock implements Serializable {
    private Integer stockId;
    private String stockCode;
    private String stockName;
    private StockDetail stockDetail;

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
