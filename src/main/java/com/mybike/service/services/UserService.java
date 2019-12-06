package com.mybike.service.services;

import com.mybike.service.models.LoginUserServiceModel;
import com.mybike.service.models.RegisterUserServiceModel;
import com.mybike.service.models.UserServiceModel;

public interface UserService {

    void register(RegisterUserServiceModel model) throws Exception;

    LoginUserServiceModel login(RegisterUserServiceModel model) throws Exception;

    UserServiceModel findUserByUserName(String username);

}
