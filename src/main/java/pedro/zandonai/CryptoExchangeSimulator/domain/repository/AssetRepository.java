package pedro.zandonai.CryptoExchangeSimulator.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
