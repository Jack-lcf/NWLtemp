package service.word;

import java.util.List;

import dao.DaoException;
import domain.Word;
import service.Service;

public interface WordService extends Service {

    public Integer create(Word word) throws DaoException;

    public Word getById(Integer id) throws DaoException;

    public List<Word> getAll() throws DaoException;

    public void update(Word word) throws DaoException;

    public void delete(Integer id) throws DaoException;

    public Word getByEng(String eng) throws DaoException;
}
