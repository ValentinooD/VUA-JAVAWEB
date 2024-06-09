package valentinood.javaweb.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import valentinood.javaweb.domain.Cart;
import valentinood.javaweb.domain.CartItem;
import valentinood.javaweb.dto.ArticleDTO;
import valentinood.javaweb.service.ArticleService;

import java.util.Optional;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private final Cart cart;
    private ArticleService articleService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("cart", cart);
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable long id, HttpServletRequest request, Model model) {
        String referer = request.getHeader("Referer");
        Optional<ArticleDTO> article = articleService.getArticlesById(id);

        // will fail silently
        if (article.isPresent()) {
            CartItem item = new CartItem(
                    article.get(),
                    1
            );
            cart.getItems().add(item);
        }

        return "redirect:" + referer;
    }

    @PostMapping("/amount/{id}")
    public ResponseEntity<Integer> setAmount(@PathVariable long id, @RequestParam("amount") int amount) {
        if (amount <= 0) {
            cart.getItems().removeIf(i -> i.getArticle().getId() == id);
            return new ResponseEntity<>(amount, HttpStatus.OK);
        }

        cart.getItems().stream()
                .filter(item -> item.getArticle().getId() == id)
                .forEach(item -> item.setQuantity(amount));

        return new ResponseEntity<>(amount, HttpStatus.OK);
    }
}
