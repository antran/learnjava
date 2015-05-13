package com.antt.hibernate.onemany;

import com.antt.hibernate.BaseApp;
import com.antt.hibernate.model.Stock;
import com.antt.hibernate.model.StockDailyRecord;
import com.antt.hibernate.model.StockDetail;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

/**
 * Created by antt on 4/25/15.
 */
public class App extends BaseApp {

    public static void main(String[] args) {
        createSessionFactory("hibernate.onemany.cfg.xml");
        Transaction tx = null;
        try {
            Session session = getSession();
            tx = session.beginTransaction();
            Stock stock = new Stock();
            stock.setStockName("PADINI");
            stock.setStockCode("7052");

            StockDailyRecord record = new StockDailyRecord();
            record.setPriceOpen((float) 11.1);
            record.setPriceClose(12.1f);
            record.setPriceChange(1.0f);
            record.setDate(new Date());
            record.setVolume(30000L);

            record.setStock(stock);
            stock.getStockDailyRecords().add(record);

            session.save(stock);
            //Don't need 'cause cascade is save-update
//            session.save(record);
            tx.commit();
            session.close();
//            pressToExit();
        } catch (Exception ex) {
            System.out.println(ex);
//            ExceptionUtil.getStackTrace(ex);
//            ExceptionUtil.getStackTrace(ex);
            if(tx!=null) tx.rollback();
        } finally {
            getSession().close();
        }
    }

    private  static void printObject(Object obj) {
        Stock st = (Stock) obj;
        System.out.println("\tId " + st.getStockId());
        System.out.println("\tDetail CompName  " + st.getStockDetail().getCompName());
        System.out.println("\tDetail Remark  " + st.getStockDetail().getRemark());
        System.out.println("\tStock detail obj " + st.getStockDetail());
        System.out.println("\tRecord " + st.getStockDailyRecords().size());

    }
}
