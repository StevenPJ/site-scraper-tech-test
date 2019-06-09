package co.uk.sainsburys.application;

import co.uk.sainsburys.ConsoleClient;
import co.uk.sainsburys.StartUpApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartUpApplication.class)
@TestPropertySource("/application-test.properties")
public class MainApplicationContextSlowTest {

    @Autowired
    ApplicationContext context;

    @MockBean
    ConsoleClient client;

    @Test
    public void should_run_client_on_context_start() {
        assertThat(context).isNotNull();
        verify(client).run(ArgumentMatchers.<String>any());
    }
}
