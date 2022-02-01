package test.Lekarennwm.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.Lekarennwm.Model.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository <Category, Long> {
    List<Category> findByName(String name);
}
