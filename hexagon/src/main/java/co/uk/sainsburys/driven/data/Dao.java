package co.uk.sainsburys.driven.data;

public interface Dao<T> {
    T extractFrom(String page);
}
