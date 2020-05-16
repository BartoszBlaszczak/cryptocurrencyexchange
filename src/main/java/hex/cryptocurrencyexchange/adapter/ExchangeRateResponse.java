package hex.cryptocurrencyexchange.adapter;

import hex.cryptocurrencyexchange.domain.ExchangeRate;

import java.math.BigDecimal;

public class ExchangeRateResponse {
    public final BigDecimal rate;
    public final BigDecimal amount;
    public final BigDecimal result;
    public final BigDecimal fee;

    public ExchangeRateResponse(ExchangeRate exchangeRate) {
        this.rate = exchangeRate.rate;
        this.amount = exchangeRate.amount;
        this.result = exchangeRate.result;
        this.fee = exchangeRate.fee;
    }
}
