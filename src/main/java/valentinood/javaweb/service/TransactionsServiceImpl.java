package valentinood.javaweb.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import valentinood.javaweb.domain.*;
import valentinood.javaweb.repository.SpringDataTransactionItemRepository;
import valentinood.javaweb.repository.SpringDataTransactionRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionsServiceImpl implements TransactionService {
    private ArticleService articleService;
    private SpringDataTransactionRepository transactionRepository;
    private SpringDataTransactionItemRepository itemRepository;


    @Override
    public Transaction commit(WebUser user, Cart cart, String type) {
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setType(type);
        transaction.setItems(new ArrayList<>());
        transaction.setPrice(cart.getTotalPrice());
        transaction.setPurchaseDate(LocalDateTime.now());
        transactionRepository.save(transaction);

        for (CartItem item : cart.getItems()) {
            if (item.getQuantity() <= 0) continue;

            TransactionItem ti = new TransactionItem();
            ti.setTransaction(transaction);
            ti.setArticle(articleService.toArticle(item.getArticle()));
            ti.setQuantity(item.getQuantity());
            transaction.getItems().add(ti);
            itemRepository.save(ti);
        }

        cart.getItems().clear();

        return transaction;
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> getTransactionById(long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public List<Transaction> getTransactionsForUsername(String username) {
        return transactionRepository.findAllByUser_Username(username);
    }

    @Override
    public List<Transaction> getTransactionsFiltered(String username, LocalDateTime after) {
        return List.of();
    }
}
