package hex.cryptocurrencyexchange.adapter;

import java.math.BigDecimal;
import java.util.Map;

public class CurrenciesResponse {
    public final String source;
    public final Map<String, BigDecimal> rates;

    public CurrenciesResponse(String source, Map<String, BigDecimal> rates) {
        this.source = source;
        this.rates = rates;
    }
}
