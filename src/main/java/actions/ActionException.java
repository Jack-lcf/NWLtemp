package actions;

public class ActionException extends Exception {

    private static final long serialVersionUID = 1L;

    public ActionException() {
        super();
    }

    public ActionException(String message) {
        super(message);
    }

    public ActionException(Throwable e) {
        super(e);
    }

    public ActionException(String message, Throwable e) {
        super(message, e);
    }

}
