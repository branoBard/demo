package test.Lekarennwm.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.Lekarennwm.Exeption.ItemNotFoundException;
import test.Lekarennwm.Model.User;
import test.Lekarennwm.dao.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return (List<User>) this.userRepository.findAll();
    }

    @Override
    @Transactional
    public List<User> getUsersByName(String name) throws ItemNotFoundException {
        // List<User> users = null;
        // if (users == null) {
        // throw new ItemNotFoundException("User with name=" + name+ " is not found!");
        // }
        return null;
    }

    @Override
    @Transactional
    public User getUserById(Long id) throws ItemNotFoundException {
        User user = this.userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ItemNotFoundException("User with name=" + id + " is not found!");
        }
        return user;
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public User editUser(User user) {
        return null;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) throws ItemNotFoundException {
        userRepository.deleteById(id);

    }
}
