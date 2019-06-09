package co.uk.sainsburys.repository.scraper

import spock.lang.Specification


class ScraperSlowSpec extends Specification {

    Scraper scraper

    def setup() {
        scraper = new RemoteJsoupScraper()
    }

    def "should throw UnableToScrapeException if malformed url"() {
        when:
            scraper.extract("notaurl", "h1")
        then:
            thrown UnableToScrapeException
    }

    def "should throw UnableToScrapeException if empty url"() {
        when:
            scraper.extract("", "h1")
        then:
            thrown UnableToScrapeException
    }

    def "should throw UnableToScrapeException if null url"() {
        when:
            scraper.extract(null, "h1")
        then:
            thrown UnableToScrapeException
    }

    def "should throw UnableToScrapeException if url doesnt exist"() {
        when:
            scraper.extract("http://www.notavalidresourceaddress.fail", "h1")
        then:
            thrown UnableToScrapeException
    }

    def "should return empty if valid url without products"() {
        when:
            Optional<String> string = scraper.extract("http://www.google.com", "div.wontbethere")
        then:
            !string.isPresent()
    }

}