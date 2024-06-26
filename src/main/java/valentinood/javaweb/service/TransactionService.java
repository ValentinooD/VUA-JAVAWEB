package valentinood.javaweb.service;

import valentinood.javaweb.domain.Cart;
import valentinood.javaweb.domain.Transaction;
import valentinood.javaweb.domain.WebUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionService {

    Transaction commit(WebUser user, Cart cart, String type);

    List<Transaction> getTransactions();
    Optional<Transaction> getTransactionById(long id);
    List<Transaction> getTransactionsForUsername(String username);
    List<Transaction> getTransactionsFiltered(String username, LocalDateTime after);
}
