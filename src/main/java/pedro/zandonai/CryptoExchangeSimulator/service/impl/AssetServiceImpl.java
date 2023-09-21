package pedro.zandonai.CryptoExchangeSimulator.service.impl;

import org.springframework.stereotype.Service;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.Asset;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.User;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.Wallet;
import pedro.zandonai.CryptoExchangeSimulator.domain.repository.AssetRepository;
import pedro.zandonai.CryptoExchangeSimulator.service.AssetService;
import pedro.zandonai.CryptoExchangeSimulator.service.WalletService;

@Service
public class AssetServiceImpl implements AssetService {
    private final AssetRepository assetRepository;
    private final WalletService walletService;

    public AssetServiceImpl(AssetRepository assetRepository, WalletService walletService) {
        this.assetRepository = assetRepository;
        this.walletService = walletService;
    }

    @Override
    public Asset findById(Long id) {
        return assetRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Asset buy(Asset asset, User user) {
        if (asset.getQuantity() * asset.getPrice() <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        double totalCost = asset.getPrice() * asset.getQuantity();
        Wallet wallet = user.getUserWallet();

        if (wallet.getBalance() < totalCost) {
            throw new IllegalArgumentException("Saldo insuficiente para comprar este ativo.");
        }

        wallet.setBalance(wallet.getBalance() - totalCost);

        walletService.addAssetToWallet(wallet, asset);

        return asset;
    }

    @Override
    public Asset sell(Asset asset, User user) {
        if (asset.getQuantity() <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        if (asset.getQuantity() < asset.getQuantity()) {
            throw new IllegalArgumentException("Não há quantidade suficiente deste ativo para vender.");
        }

        double totalEarnings = asset.getPrice() * asset.getQuantity();
        Wallet wallet = user.getUserWallet();

        wallet.setBalance(wallet.getBalance() + totalEarnings);

        walletService.removeAssetFromWallet(wallet, asset);

        return asset;
    }
}
