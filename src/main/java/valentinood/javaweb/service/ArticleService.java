package valentinood.javaweb.service;

import valentinood.javaweb.domain.Article;
import valentinood.javaweb.dto.ArticleDTO;
import valentinood.javaweb.dto.SearchArticleDTO;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    List<ArticleDTO> getArticles();
    Optional<ArticleDTO> getArticlesById(long id);
    List<ArticleDTO> getArticlesByName(String articleName);
    List<ArticleDTO> filterByParameters(SearchArticleDTO searchArticleDTO);

    void save(ArticleDTO article);
    void remove(ArticleDTO article);

    Article toArticle(ArticleDTO dto);
    ArticleDTO toArticleDTO(Article article);

    void removeWithId(long id);

    Optional<Article> getArticle(long id);

    List<ArticleDTO> getArticlesByCategoryId(long categoryId);
}
