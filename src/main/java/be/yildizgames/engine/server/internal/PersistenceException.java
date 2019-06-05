package be.yildizgames.engine.server.internal;

/**
 * @author Grégory Van den Borre
 */
public class PersistenceException extends IllegalStateException {

    public PersistenceException(Exception cause) {
        super(cause);
    }

    public PersistenceException(String s) {
        super(s);
    }
}
