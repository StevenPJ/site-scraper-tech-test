package co.uk.sainsburys

import groovy.transform.CompileStatic
import org.junit.After
import org.junit.Before

@CompileStatic
trait OperatesOnConsole {

    ByteArrayOutputStream consoleCapture;
    PrintStream old;

    @Before
    def setupConsole() {
        old = System.out;
        consoleCapture = new ByteArrayOutputStream()
        System.out = new PrintStream(consoleCapture)
    }

    @After
    def cleanupConsole() {
        System.out = old
        consoleCapture.close()
    }

    void assertConsoleWrittenTo() {
        assert consoleCapture.toString().length() > 0
    }
}
