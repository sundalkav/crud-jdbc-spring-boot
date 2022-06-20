package ru.company.crud.exception;

public class DaoException extends RuntimeException {

    public DaoException(Throwable throwable) {
        super(throwable);
    }
}
