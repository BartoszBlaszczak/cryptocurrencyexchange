package hex.cryptocurrencyexchange.adapter;

import hex.cryptocurrencyexchange.domain.CurrencyRates;
import hex.cryptocurrencyexchange.domain.port.CryptoCurrencyExchange;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "KriptomatCryptoCurrencyExchange", url = "https://app.kriptomat.io/public-api/prices/", primary = false)
public interface KriptomatCryptoCurrencyExchange extends CryptoCurrencyExchange {

    @RequestMapping(method = RequestMethod.GET)
    CurrencyRates getRates();
}