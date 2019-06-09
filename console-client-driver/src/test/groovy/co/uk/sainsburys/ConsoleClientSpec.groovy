package co.uk.sainsburys

import co.uk.sainsburys.driver.GetProducts
import spock.lang.Specification


class ConsoleClientSpec extends Specification {

    ConsoleClient client;
    GetProducts getProducts = Mock()
    String link = "a link"

    def setup() {
        client = new ConsoleClient(getProducts, link)
    }

    def "should run getProducts use case"() {
        when:
            client.run()
        then:
            1 * getProducts.fromPage(link)
    }

}