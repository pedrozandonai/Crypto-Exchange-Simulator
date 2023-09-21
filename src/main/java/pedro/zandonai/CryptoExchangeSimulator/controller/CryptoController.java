package pedro.zandonai.CryptoExchangeSimulator.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.coinmarketcapapi.CryptoData;

@RestController
@RequestMapping("/crypto")
public class CryptoController {

    @Value("${api.external.key}")
    private String apiKey;

    @GetMapping("/{symbol}")
    public ResponseEntity<CryptoData> getCryptoData(@PathVariable String symbol) {
        String url = "https://pro-api.coinmarketcap.com/v2/cryptocurrency/quotes/latest?symbol=" + symbol.toUpperCase();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CryptoData> response = restTemplate.exchange(url, HttpMethod.GET, entity, CryptoData.class);

        CryptoData cryptoData = response.getBody();

        // Agora você tem os dados da API no objeto cryptoData

        return ResponseEntity.ok(cryptoData);
    }
}
