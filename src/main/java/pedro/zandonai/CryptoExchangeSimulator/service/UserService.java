package pedro.zandonai.CryptoExchangeSimulator.service;

import pedro.zandonai.CryptoExchangeSimulator.domain.models.User;

public interface UserService {
    User findById(Long id);

    User createUser(User userToCreate);
}
