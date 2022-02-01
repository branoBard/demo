package test.Lekarennwm.Service;

import test.Lekarennwm.Exeption.ItemNotFoundException;
import test.Lekarennwm.Model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    List<Category> getCategoriesByName(String name) throws ItemNotFoundException;

    Category getCategoryById(Long id) throws ItemNotFoundException;

    Category saveCategory(test.Lekarennwm.Model.Category category);

    Category editCategory(Category category);

    void deleteCategory(Long id) throws ItemNotFoundException;
}
