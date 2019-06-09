package co.uk.sainsburys.repository.scraper

import spock.lang.Specification


class ScraperSpec extends Specification {

    Scraper scraper

    def setup() {
        scraper = new StaticJsoupScraper()
    }

    def "should get 17 links"() {
        given:
            String html = fromFile('/sampleGroceryPageWith17ProductLinks.html')
        when:
            List<String> links = scraper.extractAll(html, "div#productLister ul li h3 a", "href")
        then:
            links.size() == 17
            links.first() == "../../../../../../shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html"
    }

    def "should get title"() {
        given:
            String html = fromFile('/sampleProductPage.html')
        when:
            Optional<String> title = scraper.extract(html, "h1")
        then:
            title.get() == "Sainsbury's Strawberries 400g"
    }

    def "should get description"() {
        given:
            String html = fromFile('/sampleProductPage.html')
        when:
            Optional<String> description = scraper.extract(html, "div.mainProductInfo h3 + div")
        then:
            description.get() == "by Sainsbury's strawberries"
    }

    def "should get calories"() {
        given:
            String html = fromFile('/sampleProductPage.html')
        when:
            Optional<String> calories = scraper.extract(html, "table.nutritionTable tr.tableRow0 td")
        then:
            calories.get() == "33kcal"
    }

    def "should get price"() {
        given:
            String html = fromFile('/sampleProductPage.html')
        when:
            Optional<String> price = scraper.extract(html, "p.pricePerUnit")
        then:
            price.get() == "£1.75/unit"
    }

    def "should handle multiple successive scrapes"() {
        given:
            String linksPage = fromFile('/sampleGroceryPageWith17ProductLinks.html')
            String productPage = fromFile('/sampleProductPage.html')
        when:
            List<String> links = scraper.extractAll(linksPage, "div#productLister ul li h3 a", "href")
            Optional<String> price = scraper.extract(productPage, "p.pricePerUnit")
        then:
            links.size() == 17
            price.get() == "£1.75/unit"
    }

    def "should return empty if not html or empty"() {
        expect:
            !scraper.extract("", "h1").isPresent()
            scraper.extractAll("", "h1", "href").isEmpty()
    }


    String fromFile(String path) {
        def url = this.getClass().getResource(path)
        return new File(url.toURI()).text
    }

}