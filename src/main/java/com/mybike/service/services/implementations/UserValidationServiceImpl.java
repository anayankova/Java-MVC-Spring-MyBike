package com.mybike.service.services.implementations;

import com.mybike.data.repositories.UsersRepository;
import com.mybike.service.models.RegisterUserServiceModel;
import com.mybike.service.services.UserValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserValidationServiceImpl implements UserValidationService {

    private final UsersRepository usersRepository;

    @Override
    public boolean isValid(RegisterUserServiceModel user) {
        return this.isUsernameFree(user.getUsername()) &&
                this.arePasswordsValid(user.getPassword(), user.getConfirmPassword()) &&
                this.isEmailValid(user.getEmail()) &&
                this.isUsernameNotNull(user.getUsername()) &&
                this.isPasswordNotNull(user.getPassword()) &&
                this.isEmailNotNull(user.getEmail()) &&
                this.isUsernameNotEmpty(user.getUsername()) &&
                this.isPasswordNotEmpty(user.getPassword()) &&
                this.isEmailNotEmpty(user.getEmail());
    }

    private boolean isUsernameFree(String username) {
        return !usersRepository.existsByUsername(username);
    }

    private boolean arePasswordsValid(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean isEmailValid(String email) {
        return true;
    }

    private boolean isUsernameNotNull(String username) {
        return username != null;
    }

    private boolean isPasswordNotNull(String password) {
        return password != null;
    }

    private boolean isEmailNotNull(String email) {
        return email != null;
    }

    private boolean isUsernameNotEmpty(String username) {
        return !username.isEmpty();
    }

    private boolean isPasswordNotEmpty(String password) {
        return !password.isEmpty();
    }

    private boolean isEmailNotEmpty(String email) {
        return !email.isEmpty();
    }
}
