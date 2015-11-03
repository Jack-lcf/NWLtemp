package actions.word;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constants.Attributes;
import constants.Commands;
import constants.Messages;
import constants.Parameters;
import constants.Uri;
import service.ServiceException;
import service.ServiceLocatorFactory;
import service.word.WordService;
import dao.DaoException;
import domain.Word;
import actions.Action;

public class ActionLearning extends Action {

    private static Map<String, String> tests = new HashMap<String, String>();

    static {
        tests.put(Commands.ENG_TO_RUS_COM, Messages.ENG_TO_RUS_TEST);
        tests.put(Commands.RUS_TO_ENG_COM, Messages.RUS_TO_ENG_TEST);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public String exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, DaoException {
        HttpSession session = request.getSession(true);

        String testName = null;
        String targetLabel = null;
        String targerWord = null;
        String replyLabel = null;
        Integer wordIndex = null;

        List<Word> words = null;

        // Getting action
        String action = null;
        try {
            action = (String) session.getAttribute(Attributes.TEST_ACTION_KEY);
        } catch (NullPointerException e) {
            //TODO: 
        }
        if (action == null) {
            String uri = request.getRequestURI();
            int index = uri.lastIndexOf(Uri.URI_SLASH_DELIMITER);
            action = uri.substring(index);
            testName = tests.get(action);
            session.setAttribute(Attributes.TEST_ACTION_KEY, action);
            
            // Getting words
            WordService service = ServiceLocatorFactory.getServiceLocator().getService(WordService.class);
            words = service.getAll();
            if (words != null & words.size() > 0) {
                Collections.sort(words);
                for (Word w : words) {
                    System.out.println(w);
                }
                session.setAttribute(Attributes.TEST_LIST_KEY, words);
                wordIndex = 0;
            }
        } else {
            try {
                wordIndex = (Integer) session.getAttribute(Attributes.WORD_INDEX_KEY);
                words = (List<Word>) session.getAttribute(Attributes.TEST_LIST_KEY);
                if(wordIndex < (words.size()-1)) {
                    wordIndex++;
                } else {
                    // TODO: finish the test and clean session
                }
            } catch (NullPointerException e) {
                // TODO: handle exception
            }
        }
        session.setAttribute(Attributes.WORD_INDEX_KEY, wordIndex);
        if (Commands.ENG_TO_RUS_COM.equals(action)) {
            targetLabel = Parameters.ENGLISH_LABEL;
            targerWord = words.get(wordIndex).getEng();
            replyLabel = Parameters.RUSSIAN_LABEL;
        } else if (Commands.RUS_TO_ENG_COM.equals(action)) {
            targetLabel = Parameters.RUSSIAN_LABEL;
            targerWord = words.get(wordIndex).getRus();
            replyLabel = Parameters.ENGLISH_LABEL;
        }
        request.setAttribute(Attributes.TEST_NAME_KEY, testName);
        request.setAttribute(Attributes.RESULT_TEXT_KEY, Messages.TRANSLATE);
        request.setAttribute(Attributes.TARGET_LABEL_KEY, targetLabel);
        request.setAttribute(Attributes.TARGET_WORD_KEY, targerWord);
        request.setAttribute(Attributes.REPLY_LABEL_KEY, replyLabel);
        request.setAttribute(Attributes.BUTTON_TEXT_KEY, Messages.BUTTON_CHECK);

        return Uri.JSP_PREFIX + Uri.LEARNING_WORD_URI + Uri.JSP_SUFFIX;
    }

}
