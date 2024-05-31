package valentinood.javaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valentinood.javaweb.domain.Article;

import java.util.List;

public interface SpringDataArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByName(String name);
}
