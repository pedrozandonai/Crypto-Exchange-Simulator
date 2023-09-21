package pedro.zandonai.CryptoExchangeSimulator.service;

import pedro.zandonai.CryptoExchangeSimulator.domain.models.Asset;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.Wallet;

public interface WalletService {
    Wallet findById(Long id);
    Wallet save(Wallet wallet);
    Wallet addBalance(Wallet wallet, double balance);
    Wallet addAssetToWallet(Wallet wallet, Asset asset);
    Wallet removeAssetFromWallet(Wallet wallet, Asset asset);
    Asset getAssetFromWallet(Wallet wallet, String ticker);
    Wallet balance(Wallet wallet);

}
