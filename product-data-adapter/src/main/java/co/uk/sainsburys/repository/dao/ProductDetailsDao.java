package co.uk.sainsburys.repository.dao;

import co.uk.sainsburys.domain.exception.PageLoadException;
import co.uk.sainsburys.driven.data.Dao;
import co.uk.sainsburys.repository.ProductDetails;
import co.uk.sainsburys.repository.scraper.Scraper;
import co.uk.sainsburys.repository.scraper.UnableToScrapeException;
import lombok.AllArgsConstructor;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ProductDetailsDao implements Dao<List<ProductDetails>> {

    static String LINKS = "links";
    static String TITLE = "title";
    static String DESCRIPTION = "desc";
    static String CALORIES = "calories";
    static String PRICE = "price";

    private Scraper scraper;

    /**
     * Eventually it may be better to implement some strategy to determine
     * if invalid/corrupt/unreachable pages should be skipped. Without additional
     * information any error will be returned to the caller who can retry or abort.
     * @param page a link to a webpage
     * @return List of product details on the page
     * @throws PageLoadException when a page cannot be loaded.
     */
    @Override
    public List<ProductDetails> extractFrom(String page) throws PageLoadException {
        try {
            List<String> relativeLinks = scraper.extractAll(page, LINKS, "href");
            List<ProductDetails> details = new ArrayList<>();
            for (String link : relativeLinks) {
                String absoluteLink = convertToAbsoluteLink(page, link);
                details.add(extractProduct(absoluteLink));
            }
            return details;
        } catch (UnableToScrapeException e) {
            throw new PageLoadException(e.getMessage(), page, e);
        }
    }

    private ProductDetails extractProduct(String productPage)  throws UnableToScrapeException {
        String title = scraper.extract(productPage, TITLE).orElse(null);
        String description = scraper.extract(productPage, DESCRIPTION).orElse(null);
        String calories = scraper.extract(productPage, CALORIES).orElse(null);
        String price = scraper.extract(productPage, PRICE).orElse(null);
        return ProductDetails.builder()
            .title(title)
            .description(description)
            .calories(calories)
            .price(price)
            .build();
    }

    private String convertToAbsoluteLink(String base, String relative) throws PageLoadException {
        try {
            return new URL(new URL(base), relative).toString();
        } catch (MalformedURLException e) {
            throw new PageLoadException(e.getMessage(), relative, e);
        }
    }
}
