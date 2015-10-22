package dao.mysql;

import org.junit.Assert;
import org.junit.Test;

import dao.DaoException;
import dao.DaoFactory;
import dao.WordDao;
import dao.mysql.DaoFactoryImpl;
import dao.mysql.WordDaoImpl;

public class DaoFactoryTest {

    @Test
    public void createDaoTest() {
        WordDao expected = new WordDaoImpl();
        DaoFactory factory = null;
        try {
            factory = new DaoFactoryImpl();
        } catch (DaoException e) {
            Assert.fail("Error in test: " + e.getMessage());
        }
        if (factory != null) {
            Assert.assertEquals(expected.getClass(), factory.createDao(WordDao.class).getClass());
        } else {
            Assert.fail("DaoFactory is null");
        }

    }

}
