package com.antt.hibernate.oneone;

import java.util.Date;

/**
 * Created by antt on 4/26/15.
 */
public class StockDetail {
    private Integer stockId;
    private Stock stock;
    private String compName;
    private String compDesc;
    private String remark;
    private Date listDate;

    public Date getListDate() {
        return listDate;
    }

    public void setListDate(Date listDate) {
        this.listDate = listDate;
    }

    public String getRemark() {

        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCompDesc() {

        return compDesc;
    }

    public void setCompDesc(String compDesc) {
        this.compDesc = compDesc;
    }

    public String getCompName() {

        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public Stock getStock() {

        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Integer getStockId() {

        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }
}
