package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }
    @Transactional
    @Override
    public void saveUser(User user) {
        userDAO.save(user);
    }

    @Override
    public User getUserByID(Long id) {
        return userDAO.getById(id);
    }

    @Override
    public User getUserByName(String name) { return userDAO.findByUsername(name); }

    @Transactional
    @Override
    public void deleteUserByID(Long id) {
        userDAO.deleteById(id);
    }
    @Transactional
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username);
        System.out.println(user.getRoles());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
