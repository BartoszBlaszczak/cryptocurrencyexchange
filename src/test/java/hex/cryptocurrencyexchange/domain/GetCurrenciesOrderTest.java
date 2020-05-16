package hex.cryptocurrencyexchange.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class GetCurrenciesOrderTest {

    @Test
    void shouldBeOrderedBase() {
        Rate rate = new Rate("btc", BigDecimal.ONE);
        GetCurrenciesOrder order = new GetCurrenciesOrder(rate.code, Set.of());

        // when
        boolean isOrderedBase = order.isOrderedBase(rate);

        // then
        assertThat(isOrderedBase).isTrue();
    }

    @Test
    void shouldNotBeOrderedBase() {
        Rate rate = new Rate("btc", BigDecimal.ONE);
        GetCurrenciesOrder order = new GetCurrenciesOrder("eth", Set.of());

        // when
        boolean isOrderedBase = order.isOrderedBase(rate);

        // then
        assertThat(isOrderedBase).isFalse();
    }

    @Test
    void shouldBeOrderedComparative() {
        Rate rate = new Rate("btc", BigDecimal.ONE);
        GetCurrenciesOrder order = new GetCurrenciesOrder("baseCurrencyCode", Set.of());

        // when
        boolean isOrderedBase = order.isOrderedComparative(rate);

        // then
        assertThat(isOrderedBase).isTrue();
    }

    @Test
    void shouldBeOrderedComparativeAfterFiltration() {
        Rate rate = new Rate("btc", BigDecimal.ONE);
        GetCurrenciesOrder order = new GetCurrenciesOrder("baseCurrencyCode", Set.of(rate.code));

        // when
        boolean isOrderedBase = order.isOrderedComparative(rate);

        // then
        assertThat(isOrderedBase).isTrue();
    }

    @Test
    void shouldNotBeOrderedComparative() {
        Rate rate = new Rate("btc", BigDecimal.ONE);
        GetCurrenciesOrder order = new GetCurrenciesOrder("baseCurrencyCode", Set.of("eth"));

        // when
        boolean isOrderedBase = order.isOrderedComparative(rate);

        // then
        assertThat(isOrderedBase).isFalse();
    }

    @Test
    void isOrderedComparative() {
    }
}