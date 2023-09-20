package pedro.zandonai.CryptoExchangeSimulator.service.impl;

import org.springframework.stereotype.Service;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.User;
import pedro.zandonai.CryptoExchangeSimulator.domain.repository.UserRepository;
import pedro.zandonai.CryptoExchangeSimulator.service.UserService;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User createUser(User userToCreate) {
        if (userRepository.existsById(userToCreate.getId())){
            throw new IllegalArgumentException("This Account Number already exists!");
        }
        return userRepository.save(userToCreate);
    }
}
