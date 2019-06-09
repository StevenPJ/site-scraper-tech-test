package co.uk.sainsburys

import co.uk.sainsburys.application.ProductsResult
import co.uk.sainsburys.application.ProductsResultFactory
import co.uk.sainsburys.driven.presenter.Presenter
import spock.lang.Specification


class ConsoleWriterSpec extends Specification
    implements OperatesOnConsole, OperatesOnJson {

    Presenter presenter;

    def setup() {
        presenter = new ConsoleWriter()
    }

    def "should print results to the console"() {
        when: "a result is shown"
            presenter.show(sampleResult([], 0, 0))
        then: "should print to console"
            assertConsoleWrittenTo()
    }

    def "should format valid results as JSON"() {
        when: "a valid result is shown"
            presenter.show(sampleResult([], 0, 0))
        then: "results should be json"
            assertIsValidJson(consoleCapture.toString())
    }

    def "should include total field"() {
        when: "a valid result is shown"
            presenter.show(sampleResult([], 0, 0))
        then: "should print a total field"
            assertFieldExists(consoleCapture.toString(), "total")
    }

    def "should include results list"() {
        when: "a valid result is shown"
            presenter.show(sampleResult([], 0, 0))
        then: "should print a results array"
            assertFieldIs(consoleCapture.toString(), 'results', [])
    }



    ProductsResult sampleResult(List list, Number gross, Number vat) {
        return ProductsResultFactory.getResult(list, gross, vat)
    }

}