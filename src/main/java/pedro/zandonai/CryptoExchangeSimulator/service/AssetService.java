package pedro.zandonai.CryptoExchangeSimulator.service;

import pedro.zandonai.CryptoExchangeSimulator.domain.models.Asset;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.User;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.Wallet;

public interface AssetService {
    Asset findById(Long id);

    Asset buy(Asset asset, User user);

    Asset sell(Asset asset, User user);
}
