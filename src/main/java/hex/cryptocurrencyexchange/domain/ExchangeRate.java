package hex.cryptocurrencyexchange.domain;

import java.math.BigDecimal;

public class ExchangeRate {
    public final String currencyCode;
    public final BigDecimal rate;
    public final BigDecimal amount;
    public final BigDecimal result;
    public final BigDecimal fee;

    public ExchangeRate(String currencyCode, BigDecimal rate, BigDecimal amount, BigDecimal result, BigDecimal fee) {
        this.currencyCode = currencyCode;
        this.rate = rate;
        this.amount = amount;
        this.result = result;
        this.fee = fee;
    }
}
