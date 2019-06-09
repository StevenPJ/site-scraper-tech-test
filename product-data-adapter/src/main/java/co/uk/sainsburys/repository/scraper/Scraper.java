package co.uk.sainsburys.repository.scraper;

import java.util.List;
import java.util.Optional;

public interface Scraper {
    List<String> extractAll(String page, String query, String attrName) throws UnableToScrapeException;
    Optional<String> extract(String page, String query) throws UnableToScrapeException;
    void load(String page) throws UnableToScrapeException;
}
