package valentinood.javaweb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import valentinood.javaweb.domain.Role;
import valentinood.javaweb.domain.WebUser;
import valentinood.javaweb.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<WebUser> optUser = userRepository.findByUsername(username);

        if(optUser.isEmpty()) {
            throw new UsernameNotFoundException("User does not exist: " + username);
        }

        WebUser user = optUser.get();
        String[] roles = user.getRoles().stream().map(Role::getName).toArray(String[]::new);

        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(roles)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
