package co.uk.sainsburys.domain.exception;

public class PageLoadException extends Exception {

    public PageLoadException(String message, String link, Throwable t) {
        super("Unable to load page " + link + " Reason: " + message, t);
    }
}
