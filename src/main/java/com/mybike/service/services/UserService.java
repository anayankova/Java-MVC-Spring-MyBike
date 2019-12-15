package com.mybike.service.services;

import com.mybike.service.models.LoginUserServiceModel;
import com.mybike.service.models.RegisterUserServiceModel;
import com.mybike.service.models.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void register(RegisterUserServiceModel model) throws Exception;

    LoginUserServiceModel login(RegisterUserServiceModel model) throws Exception;

    UserServiceModel findUserByUserName(String username);

}
