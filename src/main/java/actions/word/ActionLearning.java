package actions.word;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.Uri;
import service.ServiceException;
import dao.DaoException;
import actions.Action;

public class ActionLearning extends Action{

    @Override
    public String exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, DaoException {
        String uri = request.getRequestURI();
        int index = uri.lastIndexOf(Uri.URI_SLASH_DELIMITER);
        String actionName = uri.substring(index);
        
        return Uri.JSP_PREFIX + Uri.LEARNING_WORD_URI + Uri.JSP_SUFFIX;
    }

}
