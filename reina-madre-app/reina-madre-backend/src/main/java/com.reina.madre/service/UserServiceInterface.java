package com.reina.madre.service;

import com.reina.madre.model.Users;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceInterface {
    //public void save(Users users);

    public Users findByUserName(String username);

}
