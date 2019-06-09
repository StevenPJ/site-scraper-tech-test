package co.uk.sainsburys.repository.dao

import co.uk.sainsburys.domain.exception.PageLoadException
import co.uk.sainsburys.repository.ProductDetails
import co.uk.sainsburys.repository.scraper.Scraper
import co.uk.sainsburys.repository.scraper.UnableToScrapeException
import spock.lang.Specification


class ProductDaoSpec extends Specification {

    private static final String MOCK_GROCERY_LINK = "http://www.base.com/groceries/categories/berries"
    private static final String MOCK_RELATIVE_PRODUCT_LINK = "../../product"
    private static final String MOCK_ABSOLUTE_PRODUCT_LINK = "http://www.base.com/product"


    ProductDetailsDao dao
    Scraper scraper

    def setup() {
        scraper = Mock()
        dao = new ProductDetailsDao(scraper)
    }

    def "should build product detail from link"() {
        given:
            scraper.extractAll(MOCK_GROCERY_LINK, ProductDetailsDao.LINKS, "href") >> [MOCK_RELATIVE_PRODUCT_LINK]
            scraper.extract(MOCK_ABSOLUTE_PRODUCT_LINK, ProductDetailsDao.TITLE) >> Optional.of("title")
            scraper.extract(MOCK_ABSOLUTE_PRODUCT_LINK, ProductDetailsDao.DESCRIPTION) >> Optional.of("description")
            scraper.extract(MOCK_ABSOLUTE_PRODUCT_LINK, ProductDetailsDao.CALORIES) >> Optional.of("calories")
            scraper.extract(MOCK_ABSOLUTE_PRODUCT_LINK, ProductDetailsDao.PRICE) >> Optional.of("price")
        when:
            List<ProductDetails> details = dao.extractFrom(MOCK_GROCERY_LINK)
        then:
            details.size() == 1
            details.first().getTitle() == "title"
            details.first().getDescription() == "description"
            details.first().getCalories() == "calories"
            details.first().getPrice() == "price"
    }

    def "should convert relative links to absolute"() {
        given:
            scraper.extractAll(MOCK_GROCERY_LINK, ProductDetailsDao.LINKS, "href") >> [MOCK_RELATIVE_PRODUCT_LINK]
        when:
            dao.extractFrom(MOCK_GROCERY_LINK)
        then:
            1 * scraper.extract(MOCK_ABSOLUTE_PRODUCT_LINK, ProductDetailsDao.TITLE) >> Optional.empty();
            1 * scraper.extract(MOCK_ABSOLUTE_PRODUCT_LINK, ProductDetailsDao.DESCRIPTION) >> Optional.empty();
            1 * scraper.extract(MOCK_ABSOLUTE_PRODUCT_LINK, ProductDetailsDao.CALORIES) >> Optional.empty();
            1 * scraper.extract(MOCK_ABSOLUTE_PRODUCT_LINK, ProductDetailsDao.PRICE) >> Optional.empty();
    }

    def "should return null when no matching elements found"() {
        given:
            scraper.extractAll(MOCK_GROCERY_LINK, ProductDetailsDao.LINKS, "href") >> [MOCK_RELATIVE_PRODUCT_LINK]
            scraper.extract(MOCK_ABSOLUTE_PRODUCT_LINK, ProductDetailsDao.TITLE) >> Optional.empty()
            scraper.extract(MOCK_ABSOLUTE_PRODUCT_LINK, ProductDetailsDao.DESCRIPTION) >> Optional.empty()
            scraper.extract(MOCK_ABSOLUTE_PRODUCT_LINK, ProductDetailsDao.CALORIES) >> Optional.empty()
            scraper.extract(MOCK_ABSOLUTE_PRODUCT_LINK, ProductDetailsDao.PRICE) >> Optional.empty()
        when:
            List<ProductDetails> details = dao.extractFrom(MOCK_GROCERY_LINK)
        then:
            details.first().getTitle() == null
            details.first().getDescription() == null
            details.first().getCalories() == null
            details.first().getPrice() == null
    }

    def "should throw PageLoadException when scraper has issues"() {
        given:
            scraper.extractAll(MOCK_GROCERY_LINK, ProductDetailsDao.LINKS, "href") >> {throw new UnableToScrapeException("message", null)}
        when:
            dao.extractFrom(MOCK_GROCERY_LINK)
        then:
            thrown PageLoadException
    }
}