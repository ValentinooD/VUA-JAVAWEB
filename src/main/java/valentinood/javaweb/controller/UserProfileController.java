package valentinood.javaweb.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import valentinood.javaweb.domain.Transaction;
import valentinood.javaweb.service.TransactionService;

import java.util.Optional;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserProfileController {
    private TransactionService transactionService;

    @GetMapping("")
    public String user(Model model, HttpServletRequest request) {
        model.addAttribute("transactions",
                transactionService.getTransactionsForUsername(request.getUserPrincipal().getName()).reversed());

        return "user";
    }

    @GetMapping("/fragment/transactions/{id}")
    public String fragSearchTransactions(@PathVariable int id, Model model, HttpServletRequest request) {
        Optional<Transaction> opt = transactionService.getTransactionById(id);

        if (opt.isEmpty()) {
            return "admin/transactions :: no-info";
        }

        if (!opt.get().getUser().getUsername().equals(request.getUserPrincipal().getName())) {
            return "admin/transactions :: no-info";
        }

        model.addAttribute("transaction", opt.get());
        return "admin/transactions :: info";
    }
}
