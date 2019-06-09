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
            presenter.show(SampleData.makeResult())
        then: "results should be json"
            assertIsValidJson(consoleCapture.toString())
    }

    def "should include total field"() {
        when: "a valid result is shown"
            presenter.show(SampleData.makeResult())
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
            assertFieldIs(consoleCapture.toString(), 'results', [SampleData.SAMPLE_PRODUCT_JSON_PROPERTIES])
    }

    def "should omit kcal_per_100g if null"() {
        when: "a valid result is shown"
            presenter.show(SampleData.makeResult(product: SampleData.make(calories: null)))
            Map result = asJson(consoleCapture.toString()).results.first()
        then: "should not print a kcal_per_100g field"
            !result.kcal_per_100g
    }

    def "should include kcal_per_100g if present"() {
        when: "a valid result is shown"
            presenter.show(SampleData.makeResult(product: SampleData.make(calories: 10)))
            Map result = asJson(consoleCapture.toString()).results.first()
        then: "should print a kcal_per_100g field"
            result.kcal_per_100g
    }

    def "should not throw exception when printing null object"() {
        when:
            presenter.show(null)
        then:
            noExceptionThrown()
    }

    def "should print money to max 2 decimal places"() {
        when: "a valid result is shown"
            presenter.show(SampleData.makeResult(product: SampleData.make(price: 3.337), gross: 1.111, vat: 2.222))
            Map result = asJson(consoleCapture.toString())
        then: "should round total gross"
            result.total.gross == 1.11
        and: "should round total vat"
            result.total.vat == 2.22
        and: "should round product unit price"
            result.results.first().unit_price == 3.34
    }

    def "should print error as json with error message"() {
        when:
            presenter.showErrorMessage("There was an error")
        then:
            assertFieldIs(consoleCapture.toString(), "error", "There was an error")

    }

    def "should include title and description"() {
        when: "a valid result is shown"
            presenter.show(SampleData.makeResult())
            Map result = asJson(consoleCapture.toString()).results.first()
        then: "should include title"
            result.title
        and: "should include description"
            result.description
    }
}