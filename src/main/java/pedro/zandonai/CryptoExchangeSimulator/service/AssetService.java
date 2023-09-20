package pedro.zandonai.CryptoExchangeSimulator.service;

import pedro.zandonai.CryptoExchangeSimulator.domain.models.Asset;

public interface AssetService {
    Asset findById(Long id);
}
