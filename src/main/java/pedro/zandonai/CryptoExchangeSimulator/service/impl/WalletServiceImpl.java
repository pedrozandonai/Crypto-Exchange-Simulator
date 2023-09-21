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
    public Wallet balance(Wallet wallet) {
        double balance = 0;

        for (Asset asset : wallet.getAssets()) {
            double assetValue = asset.getQuantity() * asset.getPrice();
            balance += assetValue;
        }

        wallet.setBalance(balance);
        return walletRepository.save(wallet);
    }

    @Override
    public Asset getAssetBySymbol(Wallet wallet, String symbol) {
        for (int i = 0; i < wallet.getAssets().size(); i++){
            if (wallet.getAssets().get(i).getSymbol().equals(symbol)){
                return wallet.getAssets().get(i);
            }
        }
        return null;
    }

    @Override
    public Wallet updateAssetInWallet(Wallet wallet, Asset userAsset) {
        if (userAsset.getQuantity() <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        Asset existingAsset = getAssetBySymbol(wallet, userAsset.getSymbol());

        if (existingAsset == null) {
            throw new IllegalArgumentException("O ativo não está na carteira do usuário.");
        }

        if (!existingAsset.getId().equals(userAsset.getId())) {
            throw new IllegalArgumentException("ID do ativo fornecido não corresponde ao ID do ativo na carteira.");
        }

        // Atualize o ativo existente com as informações fornecidas
        existingAsset.setQuantity(userAsset.getQuantity());
        existingAsset.setPrice(userAsset.getPrice());
        addAssetToWallet(wallet, userAsset);


        return walletRepository.save(wallet);
    }
}
