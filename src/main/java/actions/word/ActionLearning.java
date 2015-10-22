package actions.word;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.Attributes;
import constants.Commands;
import constants.Messages;
import constants.Uri;
import service.ServiceException;
import dao.DaoException;
import actions.Action;

public class ActionLearning extends Action{
    
    private static Map<String, String> tests = new HashMap<String, String>();
    
    static {
        tests.put(Commands.ENG_TO_RUS_COM, Messages.ENG_TO_RUS_TEST);
        tests.put(Commands.RUS_TO_ENG_COM, Messages.RUS_TO_ENG_TEST);
    }

    @Override
    public String exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, DaoException {
        String uri = request.getRequestURI();
        int index = uri.lastIndexOf(Uri.URI_SLASH_DELIMITER);
        String actionName = uri.substring(index);
        request.setAttribute(Attributes.LEARNING_KEY, tests.get(actionName));
        return Uri.JSP_PREFIX + Uri.LEARNING_WORD_URI + Uri.JSP_SUFFIX;
    }

}
