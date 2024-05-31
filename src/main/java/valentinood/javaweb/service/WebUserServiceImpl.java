package valentinood.javaweb.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import valentinood.javaweb.domain.WebUser;
import valentinood.javaweb.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class WebUserServiceImpl implements WebUserService {
    private UserRepository userRepository;

    @Override
    public Optional<WebUser> getUserByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public Optional<WebUser> getUserById(Long id) {
        return userRepository.findById((long) id);
    }
}
