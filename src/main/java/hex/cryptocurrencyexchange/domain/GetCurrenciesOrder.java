package hex.cryptocurrencyexchange.domain;

import java.util.Set;

public class GetCurrenciesOrder {
    private final String currencyCode;
    private final Set<String> filter;

    public GetCurrenciesOrder(String currencyCode, Set<String> filter) {
        this.currencyCode = currencyCode;
        this.filter = filter;
    }

    public boolean isOrderedBase(Rate rate) {
        return rate.code.equalsIgnoreCase(currencyCode);
    }

    public boolean isOrderedComparative(Rate rate) {
        return !isOrderedBase(rate) && (filter.isEmpty() || filter.contains(rate.code));
    }


}
