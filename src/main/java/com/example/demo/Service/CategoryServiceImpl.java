package test.Lekarennwm.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.Lekarennwm.Exeption.ItemNotFoundException;
import test.Lekarennwm.Model.Category;
import test.Lekarennwm.dao.CategoryRepository;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public List<Category> getAllCategories() {
        return (List<Category>)
                this.categoryRepository.findAll();
    }

    @Override
    @Transactional
    public List<Category> getCategoriesByName(String name) throws ItemNotFoundException {
        List<Category> categories = this.categoryRepository.findByName(name);
        if (categories == null) {
            throw new ItemNotFoundException("Category with name=" + name+ " is not found!");
        }
        return categories;
    }

    @Override
    public Category getCategoryById(Long id) throws ItemNotFoundException {
        Category category = this.categoryRepository.findById(id).orElse(null);
        if(category == null){
            throw new ItemNotFoundException("Category with id=" + id+ " is not found!");
        }
        return category;
    }

    @Override
    public Category saveCategory(Category category) {
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category editCategory(Category category) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) throws ItemNotFoundException {
        categoryRepository.deleteById(id);
    }
}
