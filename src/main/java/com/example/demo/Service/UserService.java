package test.Lekarennwm.Service;

import test.Lekarennwm.Exeption.ItemNotFoundException;
import test.Lekarennwm.Model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    List<User> getUsersByName(String name) throws ItemNotFoundException;

    User getUserById(Long id) throws ItemNotFoundException;

    User saveUser(test.Lekarennwm.Model.User user);

    User editUser(User user);

    void deleteUser(Long id) throws ItemNotFoundException;
}
