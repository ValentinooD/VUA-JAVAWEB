package valentinood.javaweb.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import valentinood.javaweb.domain.Cart;
import valentinood.javaweb.service.ArticleService;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {

    private final Cart cart;
    private ArticleService articleService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("cart", cart);
        model.addAttribute("articles", articleService.getArticles());

        return "home";
    }

    @GetMapping("user")
    public String user(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            return home(model);
        }

        return "user";
    }

    @GetMapping("error")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String error(HttpServletRequest request) {
        return switch (Integer.parseInt(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString())) {
            case 404 -> "error/404";
            default -> "error/error";
        };
    }
}
