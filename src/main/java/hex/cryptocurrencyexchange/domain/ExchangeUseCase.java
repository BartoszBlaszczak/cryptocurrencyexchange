package hex.cryptocurrencyexchange.domain;

import hex.cryptocurrencyexchange.domain.port.CryptoCurrencyExchange;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class ExchangeUseCase {
    private final CryptoCurrencyExchange cryptoCurrencyExchange;
    private final BigDecimal feeRate;

    public ExchangeUseCase(CryptoCurrencyExchange cryptoCurrencyExchange, BigDecimal feeRate) {
        this.cryptoCurrencyExchange = cryptoCurrencyExchange;
        this.feeRate = feeRate;
    }

    public Optional<List<ExchangeRate>> exchange(ExchangeOrder order) {

        Collection<Rate> rates = cryptoCurrencyExchange.getRates().data.values();
        Optional<Rate> orderedBaseCurrency = rates.stream().filter(rate->rate.code.equalsIgnoreCase(order.from)).findFirst();

        return orderedBaseCurrency.map(base ->
                rates.parallelStream()
                .filter(onlyOrdered(order))
                .map(rate -> exchangeRate(order, base, rate)).collect(toList()));
    }

    private Predicate<Rate> onlyOrdered(ExchangeOrder order) {
        return rate -> order.to.contains(rate.code);
    }

    private ExchangeRate exchangeRate(ExchangeOrder order, Rate from, Rate to) {
        Rate compared = to.comparedTo(from);
        BigDecimal result = order.amount.divide(to.comparedTo(from).price, MathContext.DECIMAL32);
        BigDecimal fee = result.multiply(feeRate);
        return new ExchangeRate(compared.code, compared.price, order.amount, result, fee);
    }
}
