package com.cloud.currencyconversionservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeServiceProxy proxy;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(
        @PathVariable String from,
        @PathVariable String to,
        @PathVariable BigDecimal quantity
        ) {

        Map<String, String> map = new HashMap<>();
        map.put("from", from);
        map.put("to", to);
        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
            "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
            CurrencyConversionBean.class,
            map
        );

        CurrencyConversionBean bean = responseEntity.getBody();

        return new CurrencyConversionBean(bean.getId(), from, to, bean.getConversionMultiple(), quantity, quantity.multiply(bean.getConversionMultiple()), bean.getPort());
    }

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(
        @PathVariable String from,
        @PathVariable String to,
        @PathVariable BigDecimal quantity
    ) {
        CurrencyConversionBean bean = proxy.retrieveExchangeValue(from, to);

        logger.info("{}", bean);

        return new CurrencyConversionBean(bean.getId(), from, to, bean.getConversionMultiple(), quantity, quantity.multiply(bean.getConversionMultiple()), bean.getPort());
    }
}
