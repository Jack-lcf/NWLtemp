package service.word;

import java.util.List;

import dao.DaoException;
import dao.DaoFactory;
import dao.WordDao;
import domain.Word;
import service.ServiceImpl;

public class WordServiceImpl extends ServiceImpl implements WordService{

    public WordServiceImpl(DaoFactory daoFactory) {
        super(daoFactory);
    }

    public Integer create(Word word) throws DaoException {
        return getWordDao().create(word);
    }

    public Word getById(Integer id) throws DaoException {
        return getWordDao().getById(id);
    }

    public List<Word> getAll() throws DaoException {
        return getWordDao().getAll();
    }

    public void update(Word word) throws DaoException {
        getWordDao().update(word);
    }

    public void delete(Integer id) throws DaoException {
        getWordDao().delete(id);
    }

    public Word getByEng(String eng) throws DaoException {
        return getWordDao().getByEng(eng);
    }
    
    private WordDao getWordDao(){
        return getDaoFactory().createDao(WordDao.class);
    }
    
}
