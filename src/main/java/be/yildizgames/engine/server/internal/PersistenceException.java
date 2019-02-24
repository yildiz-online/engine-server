package be.yildizgames.engine.server.internal;

import be.yildizgames.common.exception.technical.TechnicalException;

public class PersistenceException extends TechnicalException {

    public PersistenceException(Exception cause) {
        super(cause);
    }

    public PersistenceException(String s) {
        super(s);
    }
}
