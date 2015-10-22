package actions;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import actions.word.ActionAddWord;
import actions.word.ActionDeleteWord;
import actions.word.ActionLearning;
import actions.word.ActionWords;
import constants.Commands;

public class ActionManagerImpl implements ActionManager {

    private static Map<String, Class<? extends Action>> actions = new ConcurrentHashMap<String, Class<? extends Action>>();

    static {
        actions.put(Commands.ALL_WORDS_COM, ActionWords.class);
        actions.put(Commands.ADD_WORD_COM, ActionAddWord.class);
        actions.put(Commands.DEL_WORD_COM, ActionDeleteWord.class);
        actions.put(Commands.ENG_TO_RUS_COM, ActionLearning.class);
        actions.put(Commands.RUS_TO_ENG_COM, ActionLearning.class);
    }

    public String execute(String actionName, HttpServletRequest request, HttpServletResponse response)
            throws ActionException {
        Class<? extends Action> actionClass = actions.get(actionName);
        String result = null;
        if(actionClass!= null){
            try {
                Action action = actionClass.getConstructor().newInstance();
                result = action.exec(request, response);
            } catch (Exception e) {
                throw new ActionException(e);
            }
        } 
        return result;
    }
}
