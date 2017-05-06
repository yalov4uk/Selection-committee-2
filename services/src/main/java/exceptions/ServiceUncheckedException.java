package exceptions;

/**
 * Created by valera on 5/6/17.
 */
public class ServiceUncheckedException extends RuntimeException {

    public ServiceUncheckedException() {
    }

    public ServiceUncheckedException(String msg) {
        super(msg);
    }

    public ServiceUncheckedException(Exception e) {
        super(e);
    }
}
