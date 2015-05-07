package com.antt.hibernate.oneone;

import com.antt.hibernate.BaseApp;
import com.antt.hibernate.basic.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.Date;
import java.util.List;

/**
 * Created by antt on 4/25/15.
 */
public class App extends BaseApp {

    public static void main(String[] args) {
        Transaction tx = null;
        try {
            Session session = getSession();
            tx = session.beginTransaction();
            Stock stock = new Stock();
            stock.setStockName("GENM");
            stock.setStockCode("4715");

            StockDetail stockDetail = new StockDetail();
            stockDetail.setCompName("GENTING Malay");
            stockDetail.setCompDesc("Best resort in the world");
            stockDetail.setListDate(new Date());

            stock.setStockDetail(stockDetail);
            stockDetail.setStock(stock);

            session.save(stock);
            System.out.println(stockDetail);
            System.out.println("DONE saving\n");

            List stocks = session.createQuery("from Stock").list();
            for (Stock st : (List<Stock>) stocks) {
                printObject(st);
            }

            System.out.println("\nAdd remark\n");
            stockDetail.setRemark("Nothing special");
            Stock st = (Stock) selectOne(session, "from Stock S where S.stockId = " + stock.getStockId());
            printObject(st);
            tx.commit();
            session.close();
        } catch (Exception ex) {
            System.out.println(ex);
            if(tx!=null) tx.rollback();
        } finally {
            getSession().close();
        }
    }

    private  static void printObject(Object obj) {
        Stock st = (Stock) obj;
        System.out.println("Id " + st.getStockId());
        System.out.println("Detail CompName  " + st.getStockDetail().getCompName());
        System.out.println("Detail Remark  " + st.getStockDetail().getRemark());
        System.out.println(st.getStockDetail());
    }
}
