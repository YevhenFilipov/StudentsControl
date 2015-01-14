package exeptions;

public class InvalidDataException extends WebTasksException {
    private static final long serialVersionUID = 2025717014644106349L;

    public InvalidDataException(String message) {
        super(message);
    }
}
