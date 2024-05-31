package valentinood.javaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valentinood.javaweb.domain.Category;

public interface SpringDataCategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
