package valentinood.javaweb.service;

import valentinood.javaweb.domain.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();

    void save(Category category);
    void remove(Category category);
}
