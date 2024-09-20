package com.reina.madre.service;

import com.reina.madre.model.Users;
import com.reina.madre.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    UserRepository repo;

    /*@Autowired
    PasswordEncoder crypt;*/

    /*@Autowired
    RoleRepository roleRepo;*/

    /*public void save(Users user) {
        user.setPassword(crypt.encode(user.getPassword()));
        Role role = roleRepo.findByRole("USER");
        user.setAuthorities(new HashSet<Role>(Collections.singleton(role)));
        repo.save(user);
    }*/

    @Override
    public Users findByUserName(String username) {
        return repo.findByusername(username);
    }

}
