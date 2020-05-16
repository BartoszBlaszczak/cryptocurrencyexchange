package hex.cryptocurrencyexchange;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(classes = {Application.class, TestConfiguration.class}, webEnvironment = RANDOM_PORT)
class ApplicationE2ETests {
    @Autowired protected TestRestTemplate restTemplate;
    @LocalServerPort private int port;

    @Test
    void contextLoads() {}

    protected String url() {
        return "http://localhost:"+port+"/currencies/";
    }



}
