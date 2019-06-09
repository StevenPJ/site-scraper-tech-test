package co.uk.sainsburys.driven.data;

import co.uk.sainsburys.domain.exception.PageLoadException;

public interface Dao<T> {
    T extractFrom(String page) throws PageLoadException;
}
