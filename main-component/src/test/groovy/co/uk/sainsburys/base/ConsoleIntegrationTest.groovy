package co.uk.sainsburys.base

import co.uk.sainsburys.StartUpApplication
import org.junit.Rule
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.rule.OutputCapture
import org.springframework.test.context.TestPropertySource
import spock.lang.Ignore
import spock.lang.Specification

@Ignore
@SpringBootTest(classes = StartUpApplication.class)
@TestPropertySource("/application-test.properties")
class ConsoleIntegrationTest extends Specification {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();
}
