package valentinood.javaweb.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import valentinood.javaweb.dto.ArticleDTO;
import valentinood.javaweb.service.ArticleService;

import java.util.Optional;

@Controller
@RequestMapping("/article")
@AllArgsConstructor
public class ArticleController {
    private ArticleService articleService;

    @GetMapping
    public String home(Model model) {
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String article(Model model, @PathVariable long id) {
        Optional<ArticleDTO> optional =  articleService.getArticlesById(id);

        if (optional.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("article", optional.get());
        return "article/article";
    }

    @GetMapping("/fragment/articles/{catId}")
    public String articles(@PathVariable("catId") long categoryId, Model model) {
        if (categoryId != 0) {
            model.addAttribute("articles", articleService.getArticlesByCategoryId(categoryId));
        } else {
            model.addAttribute("articles", articleService.getArticles());
        }

        return "fragments/item :: items";
    }
}
