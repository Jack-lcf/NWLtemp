package dao.mysql;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import constants.Messages;
import util.HibernateUtil;
import dao.DaoException;
import dao.WordDao;
import domain.Word;

@SuppressWarnings("deprecation")
public class WordDaoImpl implements WordDao {

    @SuppressWarnings("unchecked")
    public Integer create(Word object) throws DaoException {
        Session session = null;
        Integer id = null;
        List<Word> list = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.save(object);
            String query = "select * from dictionary where id = (select max(id) from dictionary)";
            list = session.createSQLQuery(query).addEntity(Word.class).list();
            Hibernate.initialize(list);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.isOpen()) {
                session.getTransaction().rollback();
            }
            throw new DaoException(Messages.IO_ERROR, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        // Get a just inserted record
        if (list != null && list.size() > 0) {
            id = list.get(0).getId();
        }
        return id;
    }

    public Word getById(Integer id) throws DaoException {
        Session session = null;
        Word word = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            word = (Word) session.load(Word.class, id);
            Hibernate.initialize(word);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.isOpen()) {
                session.getTransaction().rollback();
            }
            throw new DaoException(Messages.IO_ERROR, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return word;
    }

    @SuppressWarnings("unchecked")
    public List<Word> getAll() throws DaoException {
        Session session = null;
        List<Word> list = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            list = session.createCriteria(Word.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.isOpen()) {
                session.getTransaction().rollback();
            }
            throw new DaoException(Messages.IO_ERROR, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    public void update(Word object) throws DaoException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoException(Messages.IO_ERROR, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void delete(Integer id) throws DaoException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM `dictionary` WHERE `id` = " + id).addEntity(Word.class).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.isOpen()) {
                session.getTransaction().rollback();
            }
            throw new DaoException(Messages.IO_ERROR, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Word getByEng(String eng) throws DaoException {
        Session session = null;
        Word word = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            word = (Word) session.createCriteria(Word.class).add(Expression.eq("eng", eng)).list().get(0);
            // word = (Word) session.load(Word.class, eng);
            Hibernate.initialize(word);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.isOpen()) {
                session.getTransaction().rollback();
            }
            throw new DaoException(Messages.IO_ERROR, e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return word;
    }

}
