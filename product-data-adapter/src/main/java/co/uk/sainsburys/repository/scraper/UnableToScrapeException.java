package co.uk.sainsburys.repository.scraper;

public class UnableToScrapeException extends Exception {
    public UnableToScrapeException(String message, Exception e) {
        super(message, e);
    }
}
