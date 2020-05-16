package hex.cryptocurrencyexchange;

import hex.cryptocurrencyexchange.adapter.CurrenciesResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class GetCurrenciesE2ETest extends ApplicationE2ETests {

    @Test
    void shouldGetNotFoundResponse() {
        // given
        String nonExistingCurrencyCode = "XXX";
        // when
        ResponseEntity<CurrenciesResponse> response = restTemplate.getForEntity(url()+nonExistingCurrencyCode, CurrenciesResponse.class);
        // then
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void shouldGetCurrencies() {
        // given
        String currencyCode = "BTC";
        // when
        ResponseEntity<CurrenciesResponse> response = restTemplate.getForEntity(url()+currencyCode, CurrenciesResponse.class);
        // then
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(response.getBody().source).isEqualTo(currencyCode);
        assertThat(response.getBody().rates.entrySet()).hasSize(30);
        response.getBody().rates.values().forEach(value -> assertThat(value).isNotNull());
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void shouldGetFilteredCurrencies() {
        // given
        String currencyCode = "BTC";
        String url = url()+currencyCode+"?filter=USDT&filter=ETH";
        // when
        ResponseEntity<CurrenciesResponse> response = restTemplate.getForEntity(url, CurrenciesResponse.class);
        // then
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(response.getBody().source).isEqualTo(currencyCode);
        assertThat(response.getBody().rates.entrySet()).hasSize(2);
        assertThat(response.getBody().rates.get("USDT")).isEqualTo(new BigDecimal("0.0001086810"));
        assertThat(response.getBody().rates.get("ETH")).isEqualTo(new BigDecimal("0.02097144"));
    }
}
