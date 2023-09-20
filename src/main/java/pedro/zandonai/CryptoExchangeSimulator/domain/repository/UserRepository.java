package pedro.zandonai.CryptoExchangeSimulator.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsById(Long id);
}
