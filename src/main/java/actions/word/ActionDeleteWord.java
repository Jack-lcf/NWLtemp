package actions.word;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.Attributes;
import constants.Messages;
import constants.Parameters;
import constants.Uri;
import service.ServiceException;
import service.ServiceLocatorFactory;
import service.word.WordService;
import dao.DaoException;
import domain.Word;
import actions.Action;

public class ActionDeleteWord extends Action {

    @Override
    public String exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, DaoException {
        try {
            Integer delID = Integer.valueOf(request.getParameter(Parameters.DEL_ID_KEY).trim());
            WordService service = ServiceLocatorFactory.getServiceLocator().getService(WordService.class);
            List<Word> words = null;
            if (service != null) {
                service.delete(delID);
                words = service.getAll();
                request.setAttribute(Attributes.WORDS_KEY, words);
            }
        } catch (NullPointerException e) {
            request.setAttribute(Attributes.ERROR_KEY, Messages.GET_DEL_ID_ERROR);
            return Uri.JSP_PREFIX + Uri.ERROR_URI + Uri.JSP_SUFFIX;
        }
        return Uri.JSP_PREFIX + Uri.ALL_WORDS_URI + Uri.JSP_SUFFIX;
    }

}
