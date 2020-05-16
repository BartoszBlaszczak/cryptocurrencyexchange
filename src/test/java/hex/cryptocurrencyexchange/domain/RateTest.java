package hex.cryptocurrencyexchange.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class RateTest {

    @Test
    void shouldProvideComparedRate() {
        Rate firstRate = new Rate("btc", new BigDecimal(20));
        Rate secondRate = new Rate("eth", new BigDecimal("4.56"));

        // when
        Rate compared = firstRate.comparedTo(secondRate);

        // then
        assertThat(compared.code).isEqualTo(firstRate.code);
        assertThat(compared.price).isEqualTo(new BigDecimal("4.385965"));
    }
}