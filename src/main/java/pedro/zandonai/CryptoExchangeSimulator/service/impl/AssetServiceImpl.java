package pedro.zandonai.CryptoExchangeSimulator.service.impl;

import org.springframework.stereotype.Service;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.Asset;
import pedro.zandonai.CryptoExchangeSimulator.domain.repository.AssetRepository;
import pedro.zandonai.CryptoExchangeSimulator.service.AssetService;

@Service
public class AssetServiceImpl implements AssetService {
    private final AssetRepository assetRepository;

    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public Asset findById(Long id) {
        return assetRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
