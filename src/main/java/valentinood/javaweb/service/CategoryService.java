package valentinood.javaweb.service;

import valentinood.javaweb.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getCategories();

    void save(Category category);
    void remove(Category category);

    Optional<Category> getCategory(long id);

    void removeWithId(long id);
}
