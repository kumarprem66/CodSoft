package com.codsoft.currencyConvertor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/currency")
@CrossOrigin(origins = "*")
public class CurrencyConvertorController {

    @Value("${exchange-rate-api-url}")
    private String exchangeRateApiUrl;
    @GetMapping("/convert")
    public ResponseEntity<String> convertCurrency(@RequestParam String baseCurrency,
                                                  @RequestParam String targetCurrency,
                                                  @RequestParam double amount){
        String apiUrl = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/"+baseCurrency+"/"+targetCurrency+".json";

        RestTemplate restTemplate = new RestTemplate();
        String exchangeRates = restTemplate.getForObject(apiUrl, String.class);


        if(exchangeRates == null){
            return new ResponseEntity<>("Error fetching exchange rates or invalid currencies.",HttpStatus.BAD_REQUEST);
        }



        try {
            // Create ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Convert the string to a JsonNode
            JsonNode jsonNode = objectMapper.readTree(exchangeRates);

            // Access individual fields

            double value = jsonNode.get(targetCurrency).asDouble();


            double convertedAmount = amount * value;

            return new ResponseEntity<>(String.format("%s %.2f = %s %.2f", baseCurrency, amount, targetCurrency, convertedAmount),HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(exchangeRates,HttpStatus.OK);
    }


}
