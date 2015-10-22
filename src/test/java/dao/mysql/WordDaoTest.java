package dao.mysql;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DaoException;
import dao.DaoFactory;
import dao.WordDao;
import domain.Word;

public class WordDaoTest extends Assert {

    private WordDao wordDao = null;

    @Before
    public void setWordDao() {
        DaoFactory factory = null;
        try {
            factory = new DaoFactoryImpl();
        } catch (DaoException e) {
            fail("Create DaoFactory error: " + e.getMessage());
        }
        assertNotNull(factory);
        wordDao = factory.createDao(WordDao.class);
    }

    @Test
    public void createTest() {
        Word tempWord = new Word("test", "тест");
        Integer createdID = null;
        List<Word> list = null;
        try {
            list = wordDao.getAll();
        } catch (DaoException e) {
        }
        assertNotNull(list);
        int oldSize = list.size();

        try {
            createdID = wordDao.create(tempWord);
        } catch (DaoException e1) {
            e1.printStackTrace();
            fail("CreateTest error: " + e1.getMessage());
        }
        assertNotNull(createdID);
        try {
            list = wordDao.getAll();
        } catch (DaoException e) {
        }
        assertNotNull(list);
        int newSize = list.size();
        assertEquals(1, newSize - oldSize);
    }

    @Test
    public void getByIdTest() {
        int id = 5;
        Word word = null;
        try {
            word = wordDao.getById(id);
        } catch (DaoException e) {
            e.printStackTrace();
            fail("GetById error: " + e.getMessage());
        }
        assertNotNull(word);
        assertEquals(id, word.getId());
    }

    @Test
    public void getAllTest() throws DaoException {
        List<Word> list = wordDao.getAll();
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void deleteTest() {
        List<Word> list = null;
        try {
            list = wordDao.getAll();
        } catch (DaoException e) {
            e.printStackTrace();
            fail("GetAll in deleteTest error: " + e.getMessage());
        }
        assertNotNull(list);
        int oldSize = list.size();
        assertTrue(oldSize > 0);

        try {
            wordDao.delete(oldSize);
        } catch (DaoException e1) {
            e1.printStackTrace();
            fail("deleteTest error: " + e1.getMessage());
        }

        try {
            list = wordDao.getAll();
        } catch (DaoException e) {
            e.printStackTrace();
            fail("GetAll in deleteTest error: " + e.getMessage());
        }
        assertNotNull(list);
        int newSize = list.size();
        assertEquals(1, oldSize - newSize);
    }

    @Test
    public void getByEngTest() {
        String eng = "tap";
        Word word = null;
        try {
            word = wordDao.getByEng(eng);
        } catch (DaoException e) {
            e.printStackTrace();
            fail("GetByEng error: " + e.getMessage());
        }
        assertNotNull(word);
        assertEquals(eng, word.getEng());
    }

}
