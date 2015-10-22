package actions.word;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ServiceException;
import service.ServiceLocatorFactory;
import service.word.WordService;
import constants.Attributes;
import constants.Messages;
import constants.Parameters;
import constants.Uri;
import dao.DaoException;
import domain.Word;
import actions.Action;

public class ActionAddWord extends Action {

    @Override
    public String exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, DaoException {
        String eng = request.getParameter(Parameters.ENG_KEY);
        String rus = request.getParameter(Parameters.RUS_KEY);

        if (eng != null && rus != null) {
            WordService service = ServiceLocatorFactory.getServiceLocator().getService(WordService.class);

            if (service != null) {
                System.out.println("rus - " + rus);
                Word word = new Word(eng, rus);
                Integer createdId = service.create(word);

                if (createdId != null) {
                    request.setAttribute(Attributes.CREATED_ID_KEY, createdId);
                } else {
                    request.setAttribute(Attributes.ERROR_KEY, Messages.ADD_WORD_ERROR);
                }
            }
        } else {
            request.setAttribute(Attributes.ERROR_KEY, Messages.ADD_WORD_ERROR);
        }
        return Uri.JSP_PREFIX + Uri.ADD_WORD_URI + Uri.JSP_SUFFIX;
    }

}
