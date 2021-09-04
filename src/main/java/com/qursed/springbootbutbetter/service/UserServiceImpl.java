package com.qursed.springbootbutbetter.service;


import com.qursed.springbootbutbetter.model.Role;
import com.qursed.springbootbutbetter.model.User;
import com.qursed.springbootbutbetter.repository.RoleRepository;
import com.qursed.springbootbutbetter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public void addUser(User user) {
        Set<Role> roleSet = new HashSet<>();
        for (Role role : user.getRoles()) {
            roleSet.add(roleRepository.findByRole(role.getRole()));
        }
        user.setRoles(roleSet);
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void editUser(User user) {
        Set<Role> roleSet = new HashSet<>();
        for (Role role : user.getRoles()) {
            roleSet.add(roleRepository.findByRole(role.getRole()));
        }
        user.setRoles(roleSet);
        userRepository.save(user);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByRole(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username);
    }
}
