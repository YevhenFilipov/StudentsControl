package exeptions;


public class WebTasksException extends Exception {
    private static final long serialVersionUID = 7803484175197842231L;

    public WebTasksException(String message) {
        super(message);
    }

    public WebTasksException(Throwable cause) {
        super(cause);
    }

    public WebTasksException(String message, Throwable cause) {
        super(message, cause);
    }
}
