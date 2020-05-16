package hex.cryptocurrencyexchange.domain;

import java.math.BigDecimal;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class ExchangeOrder {
    public final String from;
    public final Set<String> to;
    public final BigDecimal amount;

    public ExchangeOrder(String from, Set<String> to, BigDecimal amount) {
        this.from = from;
        this.to = to.stream().map(String::toUpperCase).collect(toSet());
        this.amount = amount;
    }
}
