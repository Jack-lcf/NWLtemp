package service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import service.word.WordService;
import service.word.WordServiceImpl;

public class ServiceLocatorImplTest {

    private ServiceLocator locator;

    @Before
    public void setServiceLocatorFactory() {
        locator = ServiceLocatorFactory.getServiceLocator();
    }

    @Test
    public void getServiceTest() {
        Class<? extends Service> expected = WordServiceImpl.class;
        Service actual = null;
        try {
            actual = locator.getService(WordService.class);
        } catch (ServiceException e) {
            fail("ServiceException in test: " + e.getMessage());
        }
        assertNotNull(actual);
        assertEquals(expected, actual.getClass());
    }

}
