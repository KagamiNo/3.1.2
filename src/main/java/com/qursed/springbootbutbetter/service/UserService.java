package com.qursed.springbootbutbetter.service;



import com.qursed.springbootbutbetter.model.Role;
import com.qursed.springbootbutbetter.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    void addUser(User user);

    void deleteUser(Long id);

    void editUser(User user);

    List<Role> getAllRoles();

    Role getRoleByName(String name);
}
