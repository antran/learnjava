package com.antt.hibernate;

import com.antt.hibernate.basic.Test;
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
public class BaseApp {
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

    }

    public static Object selectOne(Session session, String query) {
        List ret = session.createQuery(query).list();
        if (ret != null && ret.size() > 0)
            return ret.get(0);
        return null;
    }
}
