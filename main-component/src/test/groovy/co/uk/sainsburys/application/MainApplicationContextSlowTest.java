package co.uk.sainsburys.application;

import co.uk.sainsburys.StartUpApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartUpApplication.class)
@TestPropertySource("/application-test.properties")
public class MainApplicationContextSlowTest {

    @Autowired
    ApplicationContext context;

    @Test
    public void should_load_context() {
        assertThat(context).isNotNull();
    }
}
