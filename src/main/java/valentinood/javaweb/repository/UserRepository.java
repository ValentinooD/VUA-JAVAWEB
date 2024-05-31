package valentinood.javaweb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import valentinood.javaweb.domain.WebUser;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<WebUser, Long> {
    Optional<WebUser> findByUsername(String username);
    Optional<WebUser> findById(long id);
}
