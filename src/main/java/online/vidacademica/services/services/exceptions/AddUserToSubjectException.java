package online.vidacademica.services.services.exceptions;

public class AddUserToSubjectException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AddUserToSubjectException(Object id) {
        super("Failed to add user with id: " + id + " User is student");
    }

    public AddUserToSubjectException(String msg) {
        super(msg);
    }
}
