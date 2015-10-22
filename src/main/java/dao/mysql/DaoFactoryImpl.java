package dao.mysql;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import constants.Messages;
import logger.Log;
import dao.Dao;
import dao.DaoException;
import dao.DaoFactory;
import dao.WordDao;
import domain.AbstractEntity;

public class DaoFactoryImpl implements DaoFactory {
    
    private static final Map<Class<? extends Dao<? extends AbstractEntity>>, 
                             Class<? extends Dao<? extends AbstractEntity>>>
                                    classes = new ConcurrentHashMap
                                             <Class<? extends Dao<? extends AbstractEntity>>, 
                                              Class<? extends Dao<? extends AbstractEntity>>>();
    static {
        classes.put(WordDao.class, WordDaoImpl.class);
    }
    
    public DaoFactoryImpl() throws DaoException {
        
    }

    @SuppressWarnings("unchecked")
    public <T extends Dao<? extends AbstractEntity>> T createDao(Class<T> key) {
        Class<? extends Dao<? extends AbstractEntity>> value = classes.get(key);
        if(value != null) {
            try {
                return (T) value.getConstructor().newInstance();
            } catch (Exception e) {
                Log.error(Messages.CREATE_DAO_ERROR + e);
            } 
        }
        return null;
    }

}
