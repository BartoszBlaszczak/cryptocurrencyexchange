package hex.cryptocurrencyexchange.domain.port;

import hex.cryptocurrencyexchange.domain.CurrencyRates;

public interface CryptoCurrencyExchange {
    CurrencyRates getRates();
}