package hex.cryptocurrencyexchange.domain;

import hex.cryptocurrencyexchange.domain.port.CryptoCurrencyExchange;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class CurrenciesUseCase {
    private final CryptoCurrencyExchange cryptoCurrencyExchange;

    public CurrenciesUseCase(CryptoCurrencyExchange cryptoCurrencyExchange) {
        this.cryptoCurrencyExchange = cryptoCurrencyExchange;
    }

    public Optional<Set<Rate>> currencies(GetCurrenciesOrder order) {
        Collection<Rate> rates = cryptoCurrencyExchange.getRates().data.values();
        Optional<Rate> orderedBaseCurrency = rates.stream().filter(order::isOrderedBase).findFirst();
        return orderedBaseCurrency
                .map(base -> rates.stream()
                        .filter(order::isOrderedComparative)
                        .map(rate -> rate.comparedTo(base))
                        .collect(toSet()));
    }
}
