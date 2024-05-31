package valentinood.javaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valentinood.javaweb.domain.LogEntry;

import java.util.List;

public interface SpringDataLogRepository extends JpaRepository<LogEntry, Long> {
    List<LogEntry> findByUsername(String username);

}
