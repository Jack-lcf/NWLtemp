package actions;

public class ActionManagerFactory {
    public static ActionManager getActionManager() {
        return new ActionManagerImpl();
    }

}
