package pedro.zandonai.CryptoExchangeSimulator.service.impl;

import org.springframework.stereotype.Service;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.Asset;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.Wallet;
import pedro.zandonai.CryptoExchangeSimulator.domain.repository.WalletRepository;
import pedro.zandonai.CryptoExchangeSimulator.service.WalletService;

@Service
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet findById(Long id) {
        return walletRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Wallet save(Wallet wallet) {
        return walletRepository.save(wallet);
    }
    @Override
    public Wallet addBalance(Wallet wallet, double balance) {
        wallet.setBalance(balance);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet addAssetToWallet(Wallet wallet, Asset asset) {
        wallet.getAssets().add(asset);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet removeAssetFromWallet(Wallet wallet, Asset asset) {
        for (int i = 0; i < wallet.getAssets().size(); i++){
            if (wallet.getAssets().get(i).getQuantity() == asset.getQuantity()){
                wallet.getAssets().remove(asset);
            } else if (wallet.getAssets().get(i).getName().equals(asset.getName())) {
                wallet.getAssets().get(i).setQuantity(asset.getQuantity());
            }
        }
        return walletRepository.save(wallet);
    }

    @Override
    public Asset getAssetFromWallet(Wallet wallet, String ticker) {
        for (int i = 0; i < wallet.getAssets().size(); i++){
            if (wallet.getAssets().get(i).equals(ticker)){
                return wallet.getAssets().get(i);
            }
        }
        return null;
    }

    @Override
    public Wallet balance(Wallet wallet) {
        double balance = 0;

        for (Asset asset : wallet.getAssets()) {
            double assetValue = asset.getQuantity() * asset.getPrice();
            balance += assetValue;
        }

        wallet.setBalance(balance);
        return walletRepository.save(wallet);
    }
}
