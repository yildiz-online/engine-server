package be.yildizgames.engine.server.internal;

import be.yildizgames.common.exception.technical.TechnicalException;

public class PersistenceException extends TechnicalException {

    public PersistenceException(String message, Exception cause) {
        super(message, cause);
    }

    public PersistenceException(Exception cause) {
        super(cause);
    }

    public PersistenceException(String s) {
        super(s);
    }
}
