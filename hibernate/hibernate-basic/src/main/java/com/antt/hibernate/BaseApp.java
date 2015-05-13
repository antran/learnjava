package com.antt.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;
import java.util.Scanner;

/**
 * Created by antt on 4/25/15.
 */
public class BaseApp {
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceReg;
    public static String hbnConfig = "hibernate-ad.cfg";
//    static {
//        System.out.println("In BasicApp static block. hbnConfig " + hbnConfig);
//        try {
//            Configuration config = new Configuration();
//            config.configure(hbnConfig);
//
//            serviceReg = new ServiceRegistryBuilder().applySettings(
//                    config.getProperties()).buildServiceRegistry();
//            sessionFactory = config.buildSessionFactory(serviceReg);
//        } catch (Throwable ex) {
//            throw new ExceptionInInitializerError(ex);
//        }
//    }

    public static void createSessionFactory(String configName) {
        System.out.println("In BasicApp static block. hbnConfig " + hbnConfig);
        try {
            Configuration config = new Configuration();
            if (config != null )
                config.configure(configName);
            else
                config.configure(hbnConfig);

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

    protected static void pressToExit() {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }
}
