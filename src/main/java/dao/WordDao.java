package dao;

import domain.Word;

public interface WordDao extends Dao<Word>{
    
    public Word getByEng(String eng) throws DaoException;

}
