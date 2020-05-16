package hex.cryptocurrencyexchange.adapter;

import hex.cryptocurrencyexchange.domain.CurrenciesUseCase;
import hex.cryptocurrencyexchange.domain.ExchangeOrder;
import hex.cryptocurrencyexchange.domain.ExchangeUseCase;
import hex.cryptocurrencyexchange.domain.GetCurrenciesOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

@RestController
@RequestMapping("/currencies")
public class Controller {
    private final CurrenciesUseCase currenciesUseCase;
    private final ExchangeUseCase exchangeUseCase;

    public Controller(CurrenciesUseCase currenciesUseCase, ExchangeUseCase exchangeUseCase) {
        this.currenciesUseCase = currenciesUseCase;
        this.exchangeUseCase = exchangeUseCase;
    }

    @GetMapping("/{currency}")
    public ResponseEntity<CurrenciesResponse> currencies(@PathVariable String currency, @RequestParam(value = "filter", defaultValue = "") Set<String> filter) {
        return ResponseEntity.of(currenciesUseCase.currencies(new GetCurrenciesOrder(currency, filter))
                .map(currencies -> currencies.stream().collect(toMap(rate -> rate.code, rate -> rate.price)))
                .map(rates -> new CurrenciesResponse(currency, rates)));
    }

    @PostMapping("/exchange")
    public ResponseEntity<Map<String, Object>> exchange(@RequestBody ExchangeOrder order) {
        return ResponseEntity.of(exchangeUseCase.exchange(order).map(rates -> {
            Map<String, Object> response = rates.stream().collect(toMap(x -> x.currencyCode, ExchangeRateResponse::new));
            response.put("from", order.from);
            return response;
        }));
    }
}
