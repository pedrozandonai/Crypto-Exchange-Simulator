package pedro.zandonai.CryptoExchangeSimulator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.Asset;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.User;
import pedro.zandonai.CryptoExchangeSimulator.domain.repository.AssetRepository;
import pedro.zandonai.CryptoExchangeSimulator.service.WalletService;
import pedro.zandonai.CryptoExchangeSimulator.service.impl.AssetServiceImpl;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.coinmarketcapapi.CryptoInfo;

import static javax.management.Query.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AssetServiceImplTest {

    @Mock
    private AssetRepository assetRepository;

    @Mock
    private WalletService walletService;

    @Mock
    private RestTemplate restTemplate;

    private AssetServiceImpl assetService;

    private CryptoInfo cryptoInfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        assetService = new AssetServiceImpl(assetRepository, walletService, restTemplate);
    }

    @Test
    void testBuyAsset() {
        // Crie um usuário fictício
        User user = new User();
        user.setId(1L);

        // Mock da resposta da chamada da API externa
        String symbol = "BTC";
        String apiUrl = "https://pro-api.coinmarketcap.com/v2/cryptocurrency/quotes/latest?symbol=" + symbol.toUpperCase();
        double price = 50000.0;

        // Crie um objeto CryptoInfo simulado para a resposta da API
        AssetServiceImpl.CryptoInfo cryptoInfo = new AssetServiceImpl.CryptoInfo();
        cryptoInfo.setSymbol(symbol);
        cryptoInfo.setPrice(price);

        // Defina o comportamento esperado do mock do restTemplate
        when(restTemplate.exchange(apiUrl, HttpMethod.GET, any(), eq(AssetServiceImpl.CryptoData.class)))
                .thenReturn(new ResponseEntity<>(new AssetServiceImpl.CryptoData(cryptoInfo), HttpStatus.OK));

        // Defina o saldo da carteira do usuário
        user.getUserWallet().setBalance(100000.0);

        // Faça a compra de ativo
        Asset boughtAsset = assetService.buy(symbol, 2, user);

        // Verifique se o ativo foi comprado com sucesso
        assertEquals(symbol, boughtAsset.getSymbol());
        assertEquals(2, boughtAsset.getQuantity());
        assertEquals(price, boughtAsset.getPrice());

        // Verifique se o saldo da carteira foi atualizado corretamente
        assertEquals(100000.0 - (2 * price), user.getUserWallet().getBalance());
    }

    // Adicione mais testes para outros métodos, como testSellAsset, conforme necessário
}

