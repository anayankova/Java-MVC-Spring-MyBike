package com.mybike.service.services;

import com.mybike.service.models.RegisterUserServiceModel;

public interface UserValidationService {
    boolean isValid(RegisterUserServiceModel user);
}
