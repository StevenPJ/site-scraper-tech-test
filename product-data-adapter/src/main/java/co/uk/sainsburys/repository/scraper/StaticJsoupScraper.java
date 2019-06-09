package co.uk.sainsburys.repository.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StaticJsoupScraper implements Scraper {

    protected String cachedLink;
    protected Document document;

    @Override
    public Optional<String> extract(String page, String query) throws UnableToScrapeException {
        checkLoad(page);
        return Optional.ofNullable(document.selectFirst(query)).map(Element::text);
    }

    @Override
    public List<String> extractAll(String page, String query, String attrKey) throws UnableToScrapeException {
        checkLoad(page);
        return document.select(query).stream()
            .map(e -> e.attr(attrKey))
            .collect(Collectors.toList());
    }

    @Override
    public void load(String html) throws UnableToScrapeException {
        this.document = Jsoup.parse(html);
        this.cachedLink = html;
    }

    protected void checkLoad(String pageLink) throws UnableToScrapeException {
        if (cachedLink == null || !cachedLink.equals(pageLink)) {
            load(pageLink);
        }
    }
}
