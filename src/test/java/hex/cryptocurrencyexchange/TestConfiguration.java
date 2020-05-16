package hex.cryptocurrencyexchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import hex.cryptocurrencyexchange.domain.CurrencyRates;
import hex.cryptocurrencyexchange.domain.port.CryptoCurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class TestConfiguration {
    @Autowired private ObjectMapper objectMapper;

    @Bean
    @Primary
    CryptoCurrencyExchange cryptoCurrencyExchange() throws IOException {
        CurrencyRates currencyRates = objectMapper.readValue(new ClassPathResource("kriptomat_response.json").getFile(), CurrencyRates.class);
        return () -> currencyRates;
    }
}
