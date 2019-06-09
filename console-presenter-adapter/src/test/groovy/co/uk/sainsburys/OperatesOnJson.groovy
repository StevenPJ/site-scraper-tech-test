package co.uk.sainsburys

import groovy.json.JsonSlurper
import groovy.transform.CompileStatic

@CompileStatic
trait OperatesOnJson {

    void assertIsValidJson(String output) {
        assert new JsonSlurper().parseText(output) instanceof Map
    }

    Map asJson(String jsonString) {
        return (Map) new JsonSlurper().parseText(jsonString)
    }

    void assertFieldExists(String output, String field) {
        assert asJson(output)[field]
    }
}
