package test.Lekarennwm.dao;

import org.springframework.data.repository.CrudRepository;
import test.Lekarennwm.Model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String name);
}
