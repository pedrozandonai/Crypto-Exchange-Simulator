package pedro.zandonai.CryptoExchangeSimulator.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.Asset;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.User;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.Wallet;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.coinmarketcapapi.*;
import pedro.zandonai.CryptoExchangeSimulator.domain.repository.AssetRepository;
import pedro.zandonai.CryptoExchangeSimulator.service.AssetService;
import pedro.zandonai.CryptoExchangeSimulator.service.WalletService;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {
    private final AssetRepository assetRepository;
    private final WalletService walletService;
    private final RestTemplate restTemplate;

    @Value("${api.external.key}")
    private String apiKey;

    public AssetServiceImpl(AssetRepository assetRepository, WalletService walletService, RestTemplate restTemplate) {
        this.assetRepository = assetRepository;
        this.walletService = walletService;
        this.restTemplate = restTemplate;
    }

    @Override
    public Asset findById(Long id) {
        return assetRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Asset buy(String symbol, int quantity, User user) {
        CryptoInfo cryptoInfo = getCryptoInfo(symbol);

        if (cryptoInfo == null) {
            throw new IllegalArgumentException("Ativo não encontrado.");
        }

        double totalPrice = cryptoInfo.getPrice() * quantity;

        Wallet wallet = user.getUserWallet();

        if (wallet.getBalance() < totalPrice) {
            throw new IllegalArgumentException("Saldo insuficiente para comprar este ativo.");
        }

        // Crie um novo ativo e atualize a carteira do usuário
        Asset asset = new Asset();
        asset.setSymbol(symbol);
        asset.setQuantity(quantity);
        asset.setPrice(cryptoInfo.getPrice());
        asset.setName(cryptoInfo.getName());

        wallet.setBalance(wallet.getBalance() - totalPrice);

        walletService.addAssetToWallet(wallet, asset);

        return assetRepository.save(asset);
    }

    @Override
    public Asset sell(String symbol, int quantity, User user) {
        // Use os dados da API para obter informações sobre o ativo
        CryptoInfo cryptoInfo = getCryptoInfo(symbol);

        if (cryptoInfo == null) {
            throw new IllegalArgumentException("Ativo não encontrado.");
        }

        Wallet wallet = user.getUserWallet();

        // Verifique se o usuário possui o ativo em sua carteira
        Asset userAsset = walletService.getAssetBySymbol(wallet, symbol);

        if (userAsset == null || userAsset.getQuantity() < quantity) {
            throw new IllegalArgumentException("Você não possui quantidade suficiente deste ativo para vender.");
        }

        double totalEarnings = cryptoInfo.getPrice() * quantity;

        // Atualize o saldo da carteira e a quantidade do ativo na carteira
        wallet.setBalance(wallet.getBalance() + totalEarnings);
        userAsset.setQuantity(userAsset.getQuantity() - quantity);

        // Se a quantidade do ativo na carteira for zero, remova-o
        if (userAsset.getQuantity() == 0) {
            walletService.removeAssetFromWallet(wallet, userAsset);
        } else {
            walletService.updateAssetInWallet(wallet, userAsset);
        }

        return assetRepository.save(userAsset);
    }

    public CryptoInfo getCryptoInfo(String symbol) {
        String url = "https://pro-api.coinmarketcap.com/v2/cryptocurrency/quotes/latest?symbol=" + symbol.toUpperCase();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<CryptoData> response = restTemplate.exchange(url, HttpMethod.GET, entity, CryptoData.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                CryptoData cryptoData = response.getBody();
                if (cryptoData != null && cryptoData.getData() != null) {
                    Data data = cryptoData.getData();
                    CryptoInfo cryptoInfo = new CryptoInfo();
                    cryptoInfo.setId(data.getId());

                    // Verifica se os valores são nulos antes de atribuí-los
                    if (data.getName() != null) {
                        cryptoInfo.setName(data.getName());
                    }
                    if (data.getSymbol() != null) {
                        cryptoInfo.setSymbol(data.getSymbol());
                    }

                    if (!data.getQuotes().isEmpty()) {
                        Quote quote = data.getQuotes().get(0);
                        QuoteData quoteData = quote.getQuote();
                        cryptoInfo.setPrice(quoteData.getPrice());
                    }

                    return cryptoInfo;
                }
            }
        } catch (RestClientException e) {
            e.printStackTrace();
        }

        return null;
    }
}
