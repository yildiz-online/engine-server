package be.yildizgames.engine.server.internal;

import be.yildizgames.common.exception.technical.TechnicalException;

/**
 * @author Grégory Van den Borre
 */
public class PersistenceException extends TechnicalException {

    public PersistenceException(Exception cause) {
        super(cause);
    }

    public PersistenceException(String s) {
        super(s);
    }
}
