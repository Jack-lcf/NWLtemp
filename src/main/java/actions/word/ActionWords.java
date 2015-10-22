package actions.word;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.Attributes;
import constants.Uri;
import dao.DaoException;
import domain.Word;
import service.ServiceException;
import service.ServiceLocatorFactory;
import service.word.WordService;
import actions.Action;

public class ActionWords extends Action {

    @Override
    public String exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, DaoException {
        WordService service = null;
        List<Word> words = null;

        service = ServiceLocatorFactory.getServiceLocator().getService(WordService.class);
        words = service.getAll();

        request.setAttribute(Attributes.WORDS_KEY, words);
        return Uri.JSP_PREFIX + Uri.ALL_WORDS_URI + Uri.JSP_SUFFIX;
    }

}
