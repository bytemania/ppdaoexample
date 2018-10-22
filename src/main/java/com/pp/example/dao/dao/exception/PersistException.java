package com.pp.example.dao.dao.exception;

import java.text.MessageFormat;

public class PersistException extends Exception {

    public PersistException(String s, Object... args) {
        super(MessageFormat.format(s, args));
    }

    public PersistException(Throwable throwable) {
        super(throwable);
    }
}
