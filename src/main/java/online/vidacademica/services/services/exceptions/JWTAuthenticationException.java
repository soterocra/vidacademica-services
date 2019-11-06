package online.vidacademica.services.services.exceptions;

public class JWTAuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public JWTAuthenticationException(Object id) {
        super("Access Denied");
    }
}
