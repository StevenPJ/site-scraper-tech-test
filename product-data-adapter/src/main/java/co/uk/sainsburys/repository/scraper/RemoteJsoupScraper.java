package co.uk.sainsburys.repository.scraper;

import org.jsoup.Jsoup;

import java.io.IOException;

public class RemoteJsoupScraper extends StaticJsoupScraper {

    @Override
    public void load(String pageLink) throws UnableToScrapeException {
        try {
            this.document = Jsoup.connect(pageLink).get();
            this.cachedLink = pageLink;
        } catch (IOException | IllegalArgumentException e) {
            throw new UnableToScrapeException(e.getMessage(), e);
        }
    }
}
