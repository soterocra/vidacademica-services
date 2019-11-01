package online.vidacademica.services.services.exceptions;

public class UpdateDateTestException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UpdateDateTestException(Object id) {
        super("Failed to update test with id: " + id + " New date is before today's date");
    }

    public UpdateDateTestException(String msg) {
        super(msg);
    }
}
