package co.uk.sainsburys.repository.dao

import co.uk.sainsburys.repository.ProductDetails
import co.uk.sainsburys.repository.scraper.Scraper
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
}