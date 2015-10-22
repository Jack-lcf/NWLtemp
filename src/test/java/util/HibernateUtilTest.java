package util;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

public class HibernateUtilTest {

    @Test
    public void getSessionFactoryTest() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        assertNotNull("Factory is null", factory);
    }
    
    @Test
    public void getSessionTest() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        assertNotNull("Session is null", session);
    }

}
