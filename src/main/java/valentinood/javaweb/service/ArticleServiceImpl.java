package valentinood.javaweb.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import valentinood.javaweb.domain.Article;
import valentinood.javaweb.dto.ArticleDTO;
import valentinood.javaweb.dto.SearchArticleDTO;
import valentinood.javaweb.repository.SpringDataArticleRepository;
import valentinood.javaweb.repository.SpringDataCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private SpringDataArticleRepository articleRepository;
    private SpringDataCategoryRepository categoryRepository;

    @Override
    public List<ArticleDTO> getArticles() {
        return toArticleDTOList(articleRepository.findAll());
    }

    @Override
    public List<ArticleDTO> getArticlesByName(String articleName) {
        return toArticleDTOList(articleRepository.findByName(articleName));
    }

    @Override
    public Optional<ArticleDTO> getArticlesById(long id) {
        Optional<Article> opt =  articleRepository.findById(id);
        return opt.map(this::toArticleDTO);
    }

    @Override
    public List<ArticleDTO> filterByParameters(SearchArticleDTO searchArticleDTO) {
        return List.of(); // TODO
    }

    @Override
    public void save(ArticleDTO article) {
        articleRepository.save(toArticle(article));
    }

    @Override
    public void remove(ArticleDTO article) {
        articleRepository.delete(toArticle(article));
    }

    private List<ArticleDTO> toArticleDTOList(List<Article> articles) {
        return articles.stream().map(this::toArticleDTO).toList();
    }

    @Override
    public ArticleDTO toArticleDTO(Article article) {
        return new ArticleDTO(
                article.getId(),
                article.getName(),
                article.getDescription(),
                article.getPrice(),
                article.getCategory().getName(),
                article.getImage(),
                null // always null here, it's filled by the form when creating it
        );
    }

    @Override
    public Article toArticle(ArticleDTO dto) {
        return new Article(
                dto.getId(),
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getBase64Image(),
                categoryRepository.findByName(dto.getCategory())
        );
    }
}
