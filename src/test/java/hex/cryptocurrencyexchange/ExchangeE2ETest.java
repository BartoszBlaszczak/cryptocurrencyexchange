package hex.cryptocurrencyexchange;

import hex.cryptocurrencyexchange.domain.ExchangeOrder;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

class ExchangeE2ETest extends ApplicationE2ETests {

    @Test
    @SuppressWarnings({"unchecked", "ConstantConditions"})
    void shouldExchange() {
        // given
        String currencyToExchangeCode = "btc";
        ExchangeOrder exchangeOrder = new ExchangeOrder(currencyToExchangeCode, Set.of("eth", "usdt"), new BigDecimal(121));
        // when
        var response = restTemplate.postForEntity(url()+"exchange", exchangeOrder, Map.class);
        // then
        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody().get("from")).isEqualTo(currencyToExchangeCode);

        Map<String, Object> eth = (Map<String, Object>) response.getBody().get("ETH");
        assertThat(eth.get("amount").toString()).isEqualTo(exchangeOrder.amount.toString());
        assertThat(eth.get("rate").toString()).isEqualTo("0.02097144");
        assertThat(eth.get("result").toString()).isEqualTo("5769.752");
        assertThat(eth.get("fee").toString()).isEqualTo("57.69752");

        Map<String, Object> usdt = (Map<String, Object>) response.getBody().get("USDT");
        assertThat(usdt.get("amount").toString()).isEqualTo(exchangeOrder.amount.toString());
        assertThat(usdt.get("rate").toString()).isEqualTo("1.08681E-4");
        assertThat(usdt.get("result").toString()).isEqualTo("1113350");
        assertThat(usdt.get("fee").toString()).isEqualTo("11133.5");
    }

    @Test
    void shouldGetNotFound() {
        // given
        String nonExistingCurrencyCode = "xxx";
        ExchangeOrder exchangeOrder = new ExchangeOrder(nonExistingCurrencyCode, Set.of("eth", "usdt"), new BigDecimal(121));
        // when
        var response = restTemplate.postForEntity(url()+"exchange", exchangeOrder, Map.class);
        // then
        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    }
}
