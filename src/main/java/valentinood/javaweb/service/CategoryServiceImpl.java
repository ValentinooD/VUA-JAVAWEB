package valentinood.javaweb.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import valentinood.javaweb.domain.Category;
import valentinood.javaweb.repository.SpringDataCategoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private SpringDataCategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void remove(Category category) {
        categoryRepository.delete(category);
    }
}
