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
            presenter.show(sampleResult([]))
        then: "should print to console"
            assertConsoleWrittenTo()
    }

    def "should format valid results as JSON"() {
        when: "a valid result is shown"
            presenter.show(sampleResult([]))
        then: "results should be json"
            assertIsValidJson(consoleCapture.toString())
    }

    ProductsResult sampleResult(List list) {
        return ProductsResultFactory.getResult(list)
    }

}