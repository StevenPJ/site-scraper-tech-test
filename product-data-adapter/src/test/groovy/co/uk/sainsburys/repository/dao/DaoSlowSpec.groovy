package co.uk.sainsburys.repository.dao

import co.uk.sainsburys.domain.exception.PageLoadException
import co.uk.sainsburys.repository.scraper.RemoteJsoupScraper
import spock.lang.Specification


class DaoSlowSpec extends Specification {

    private static final String INTEGRATION_LINK = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html"

    ProductDetailsDao dao

    def setup() {
        dao = new ProductDetailsDao(new RemoteJsoupScraper())
    }

    def "should get 17 product links"() {
        expect:
            dao.extractFrom(INTEGRATION_LINK).size() == 17
    }

    def "should throw PageLoadException when link is not well formed"() {
        when:
            dao.extractFrom("bloop")
        then:
            thrown PageLoadException
    }
}