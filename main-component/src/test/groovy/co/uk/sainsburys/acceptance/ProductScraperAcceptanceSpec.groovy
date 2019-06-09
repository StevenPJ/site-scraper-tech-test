package co.uk.sainsburys.acceptance

import co.uk.sainsburys.base.ConsoleIntegrationSlowTest
import co.uk.sainsburys.base.OperatesOnJson
import co.uk.sainsburys.base.TestData
import co.uk.sainsburys.driver.GetProducts
import org.springframework.beans.factory.annotation.Autowired

class ProductScraperAcceptanceSpec extends ConsoleIntegrationSlowTest
    implements OperatesOnJson {

    @Autowired
    GetProducts getProducts;

    def "should get products from a valid page link"() {
        given: "a valid page link"
            String pageLink = TestData.validUrl
        when: "a user get products from the page"
            getProducts.fromPage(pageLink)
        then: "the result is displayed on the console"
            assertResultsDisplayed()
        and: "the output is JSON containing 17 products info, the total and the vat"
            assertIsValidJson(outputCapture.toString())
            asJson(outputCapture.toString()) == TestData.expectedOutput
    }

    void assertResultsDisplayed() {
        assert outputCapture.toString() == null
        assert !outputCapture.toString().isEmpty()
    }

}