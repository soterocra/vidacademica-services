package online.vidacademica.services.services.exceptions;

public class PostScoreException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PostScoreException(Object id) {
        super("Failed to assign score to test with id:" + id + ". Reported grade is greater than maximum score of test in question");
    }

    public PostScoreException(String msg) {
        super(msg);
    }
}