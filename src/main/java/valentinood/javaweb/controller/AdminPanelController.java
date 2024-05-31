package valentinood.javaweb.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import valentinood.javaweb.domain.Category;
import valentinood.javaweb.domain.Transaction;
import valentinood.javaweb.dto.ArticleDTO;
import valentinood.javaweb.service.ArticleService;
import valentinood.javaweb.service.CategoryService;
import valentinood.javaweb.service.LogService;
import valentinood.javaweb.service.TransactionService;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminPanelController {

    private ArticleService articleService;
    private CategoryService categoryService;
    private TransactionService transactionService;
    private LogService logService;

    @GetMapping
    public String home() {
        return "redirect:/admin/home";
    }

    @GetMapping("{view}")
    public String access(@PathVariable("view") String view, Model model) {
        model.addAttribute("view", view);
        return "admin/base";
    }

    @GetMapping("/fragment/home")
    public String fragHome(Model model) {
        model.addAttribute("msg", "Server time is " + ZonedDateTime.now());
        return "admin/home :: home";
    }

    @GetMapping("/fragment/articles")
    public String fragArticles(Model model) {
        model.addAttribute("articles", articleService.getArticles());
        model.addAttribute("article", new ArticleDTO());

        return "admin/articles :: articles";
    }

    @PostMapping(value = "/fragment/articles", produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveArticle(@ModelAttribute ArticleDTO article, Model model, HttpServletRequest request) {
        // Convert to base64
        try {
            StringBuilder builder = new StringBuilder("data:");
            builder.append(article.getFile().getContentType());
            builder.append(";base64,");
            String base64 = Base64.getEncoder().encodeToString(article.getFile().getBytes());
            builder.append(base64);

            article.setBase64Image(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        articleService.save(article);

        logService.log(request, "User created article " + article.getName());

        model.addAttribute("articles", articleService.getArticles());
        model.addAttribute("article", new ArticleDTO());
        return "admin/articles :: items";
    }

    @GetMapping("/fragment/categories")
    public String fragCategories(Model model) {
        model.addAttribute("category", new Category());
        return "admin/categories :: categories";
    }

    @PostMapping("/fragment/categories")
    public String saveCategory(@ModelAttribute Category category, Model model) {
        categoryService.save(category);
        model.addAttribute("category", new Category());
        return "admin/categories :: items";
    }

    @GetMapping("/fragment/logs")
    public String fragLogs(Model model) {
        model.addAttribute("logs", logService.getLogs().reversed());
        return "admin/log :: logs";
    }

    @GetMapping("/fragment/transactions")
    public String fragTransactions(Model model) {
        model.addAttribute("transactions", transactionService.getTransactions().reversed());
        return "admin/transactions :: transactions";
    }

    @PostMapping("/fragment/transactions")
    public String fragSearchTransactions(@ModelAttribute("username") String username, Model model) {
        List<Transaction> list = transactionService.getTransactions().reversed();

        if (username != null) {
            list = list.stream().filter(a -> a.getUser().getUsername().toLowerCase().contains(username.toLowerCase())).toList();
        }

        model.addAttribute("transactions", list);
        return "admin/transactions :: items";
    }

    @GetMapping("/fragment/transactions/{id}")
    public String fragSearchTransactions(@PathVariable int id, Model model) {
        Optional<Transaction> opt = transactionService.getTransactionById(id);

        if (opt.isEmpty()) {
            return "admin/transactions :: no-info";
        }

        model.addAttribute("transaction", opt.get());
        return "admin/transactions :: info";
    }
}
