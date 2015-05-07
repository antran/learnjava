package com.antt.hibernate.basic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

/**
 * Created by antt on 4/25/15.
 */
public class App {
    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceReg;

    static {
        try {
            Configuration config = new Configuration();
            config.configure();

            serviceReg = new ServiceRegistryBuilder().applySettings(
                    config.getProperties()).buildServiceRegistry();
            sessionFactory = config.buildSessionFactory(serviceReg);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void main(String[] args) {
        Transaction tx = null;
        try {
            Session s = getSession();
            tx = s.beginTransaction();
            Test t = new Test();
            t.setName("Test Name 1");
            s.save(t);
            t = new Test();
            t.setName("Test Name 222");
            s.save(t);
            tx.commit();
            s.close();

            System.out.println("Get id from detached object " + t.getId());

            s = getSession();
            s.beginTransaction();
            List result = s.createQuery("from Test").list();
            System.out.println("Result size " + result.size());
            for(Test el : (List<Test>) result) {
                System.out.println("Result " + el);
            }
            s.getTransaction().commit();
            s.close();
        } catch (Exception ex) {
            System.out.println(ex);
            if(tx!=null) tx.rollback();
        } finally {
            getSession().close();
        }
    }
}
