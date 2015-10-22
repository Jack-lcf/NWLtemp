package util;

import logger.Log;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    
    private static SessionFactory sessionFactory = null;
    
    static {
        try {
            
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
           Log.error("Build session error: " + e);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
