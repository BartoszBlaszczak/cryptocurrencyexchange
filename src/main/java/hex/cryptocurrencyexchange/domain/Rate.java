package hex.cryptocurrencyexchange.domain;

import java.math.BigDecimal;
import java.math.MathContext;

public class Rate {

    public final String code;
    public final BigDecimal price;

    public Rate(String code, BigDecimal price) {
        this.code = code.toUpperCase();
        this.price = price;
    }

    public Rate comparedTo(Rate other) {
        return new Rate(code, price.divide(other.price, MathContext.DECIMAL32));
    }
}
