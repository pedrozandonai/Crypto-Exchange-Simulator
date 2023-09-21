package pedro.zandonai.CryptoExchangeSimulator.service;

import pedro.zandonai.CryptoExchangeSimulator.domain.models.Asset;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.User;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.Wallet;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.coinmarketcapapi.CryptoInfo;

public interface AssetService {
    Asset findById(Long id);

    Asset buy(String symbol, int quantity, User user);

    Asset sell(String symbol, int quantity, User user);

    CryptoInfo getCryptoInfo(String symbol);
}
