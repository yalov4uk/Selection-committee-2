package exceptions;

/**
 * Created by valera on 4/30/17.
 */
public class DaoUncheckedException extends RuntimeException {

    public DaoUncheckedException() {
    }

    public DaoUncheckedException(String msg) {
        super(msg);
    }

    public DaoUncheckedException(Exception e) {
        super(e);
    }
}
