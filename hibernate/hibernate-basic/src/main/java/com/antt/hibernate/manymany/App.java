package com.antt.hibernate.manymany;

import com.antt.hibernate.BaseApp;
import com.antt.hibernate.model.Category;
import com.antt.hibernate.model.Stock;
import com.antt.hibernate.model.StockDailyRecord;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

/**
 * Created by antt on 4/25/15.
 */
public class App extends BaseApp {

    public static void main(String[] args) {
        createSessionFactory("hibernate.mm.cfg.xml");
        Transaction tx = null;
        try {
            Session session = getSession();
            tx = session.beginTransaction();
            Stock stock = new Stock();
            stock.setStockName("PADINI");
            stock.setStockCode("7052");

            Category cat1 = new Category("CONSUMER", "CONSUMER COMPANY");
            Category cat2 = new Category("INVESMENT", "INVESMENT COMPANY");
            Category cat3 = new Category("IT", "IT COMPANY");

            stock.getCategories().add(cat1);
            stock.getCategories().add(cat2);

            session.save(cat3);
            session.save(stock);
            tx.commit();

            session.close();

            getSingleStockLoadCategory(stock.getStockId());

            Stock stock2 = getSingleStock(stock.getStockId());
//            System.out.printf("Category size " + stock2.getCategories().size()); // Cause exception 'cause in detached state
//            pressToExit();
        } catch (Exception ex) {
            System.out.println(ex);
            if(tx!=null) tx.rollback();
        } finally {
            getSession().close();
        }
    }

    private static Stock getSingleStock(Integer id) {
        System.out.println(">> Query a stock from DBs");
        Session session = getSession();
        Stock st = (Stock) session.get(Stock.class, id);
        session.close();
        System.out.println("<<<");
        return st;
    }

    private static Stock getSingleStockLoadCategory(Integer id) {
        System.out.println(">>> Query a stock and load category from DBs");
        Session session = getSession();
        Stock st = (Stock) session.get(Stock.class, id);
        System.out.println("Category size " + st.getCategories().size());
        session.close();
        System.out.println("<<<");
        return st;
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
