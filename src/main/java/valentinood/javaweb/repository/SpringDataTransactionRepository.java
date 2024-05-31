package valentinood.javaweb.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import valentinood.javaweb.domain.Transaction;

import java.util.List;

public interface SpringDataTransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByUserId(Long userId);
}
