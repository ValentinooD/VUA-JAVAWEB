package valentinood.javaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import valentinood.javaweb.domain.Transaction;

import java.util.List;

public interface SpringDataTransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {
    List<Transaction> findAllByUserId(Long userId);
    List<Transaction> findAllByUser_Username(String username);
}
