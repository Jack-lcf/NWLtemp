package actions.word;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.Attributes;
import constants.Commands;
import constants.Messages;
import constants.Parameters;
import constants.Uri;
import service.ServiceException;
import dao.DaoException;
import domain.Word;
import actions.Action;

public class ActionCheckWord extends Action {

    @SuppressWarnings("unchecked")
    @Override
    public String exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, DaoException {
        HttpSession session = request.getSession(true);
        String testAction = (String) session.getAttribute(Attributes.TEST_ACTION_KEY);
        String reply = request.getParameter(Parameters.RUS_REPLY_KEY).trim();
        List<Word> words = null;
        Word testWord = null;
        try {
            words = (List<Word>) session.getAttribute(Attributes.TEST_LIST_KEY);
            testWord = words.get((Integer) session.getAttribute(Attributes.WORD_INDEX_KEY));
        } catch (NullPointerException e) {
            // TODO: handle exception
        }

        if (testWord != null & reply != null) {

            if (Commands.ENG_TO_RUS_COM.equals(testAction)) {

                if (reply.equalsIgnoreCase(testWord.getRus())) {
                    request.setAttribute(Attributes.RESULT_KEY, true);
                    request.setAttribute(Attributes.RESULT_TEXT_KEY, Messages.RIGHT_REPLY_TEST);
                    testWord.correctUp();
                } else {
                    request.setAttribute(Attributes.RESULT_KEY, false);
                    request.setAttribute(Attributes.RESULT_TEXT_KEY, Messages.WRONG_REPLY_TEST);
                }
                request.setAttribute(Attributes.TEST_NAME_KEY, Messages.ENG_TO_RUS_TEST);
                request.setAttribute(Attributes.TARGET_LABEL_KEY, Parameters.ENGLISH_LABEL);
                request.setAttribute(Attributes.TARGET_WORD_KEY, testWord.getEng());
                request.setAttribute(Attributes.REPLY_LABEL_KEY, Parameters.RUSSIAN_LABEL);
                request.setAttribute(Attributes.REPLY_WORD_KEY, testWord.getRus());
            } else {

                if (reply.equalsIgnoreCase(testWord.getEng())) {
                    request.setAttribute(Attributes.RESULT_KEY, true);
                    request.setAttribute(Attributes.RESULT_TEXT_KEY, Messages.RIGHT_REPLY_TEST);
                    testWord.correctUp();
                } else {
                    request.setAttribute(Attributes.RESULT_KEY, false);
                    request.setAttribute(Attributes.RESULT_TEXT_KEY, Messages.WRONG_REPLY_TEST);
                }
                request.setAttribute(Attributes.TEST_NAME_KEY, Messages.ENG_TO_RUS_TEST);
                request.setAttribute(Attributes.TARGET_LABEL_KEY, Parameters.RUSSIAN_LABEL);
                request.setAttribute(Attributes.TARGET_WORD_KEY, testWord.getRus());
                request.setAttribute(Attributes.REPLY_LABEL_KEY, Parameters.ENGLISH_LABEL);
                request.setAttribute(Attributes.REPLY_WORD_KEY, testWord.getEng());
            }
            testWord.totalUp();
            request.setAttribute(Attributes.BUTTON_TEXT_KEY, Messages.BUTTON_NEXT);
            request.setAttribute(Attributes.TEST_ACTION_KEY, testAction);
        } else {
            // TODO: handle exception
        }
        return Uri.JSP_PREFIX + Uri.LEARNING_WORD_URI + Uri.JSP_SUFFIX;
    }

}
