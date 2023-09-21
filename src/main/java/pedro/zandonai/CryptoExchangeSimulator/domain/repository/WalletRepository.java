package pedro.zandonai.CryptoExchangeSimulator.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
