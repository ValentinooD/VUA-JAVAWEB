package valentinood.javaweb.service;

import valentinood.javaweb.domain.WebUser;

import java.util.Optional;

public interface WebUserService {
    Optional<WebUser> getUserByUsername(String name);
    Optional<WebUser> getUserById(Long id);
}
