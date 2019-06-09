package co.uk.sainsburys


import co.uk.sainsburys.data.SampleData
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
            presenter.show(SampleData.emptyResult())
        then: "should print to console"
            assertConsoleWrittenTo()
    }

    def "should format valid results as JSON"() {
        when: "a valid result is shown"
            presenter.show(SampleData.emptyResult())
        then: "results should be json"
            assertIsValidJson(consoleCapture.toString())
    }

    def "should include total field"() {
        when: "a valid result is shown"
            presenter.show(SampleData.emptyResult())
        then: "should print a total field"
            assertFieldExists(consoleCapture.toString(), "total")
    }

    def "should include results list"() {
        when: "a valid result is shown"
            presenter.show(SampleData.emptyResult())
        then: "should print a results array"
            assertFieldIs(consoleCapture.toString(), 'results', [])
    }

    def "should print ProductResult as valid JSON"() {
        when: "a valid result is shown"
            presenter.show(SampleData.makeResult())
        then: "should print result as JSON"
            assertFieldIs(consoleCapture.toString(), 'results', [SampleData.SAMPLE_PRODUCT_PROPERTIES])
    }


}