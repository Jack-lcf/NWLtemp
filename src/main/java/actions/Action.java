package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ServiceException;
import dao.DaoException;

public abstract class Action {
    abstract public String exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException,
            DaoException;
}
