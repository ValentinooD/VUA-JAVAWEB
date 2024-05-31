package valentinood.javaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valentinood.javaweb.domain.TransactionItem;

public interface SpringDataTransactionItemRepository extends JpaRepository<TransactionItem, Long> {
}
