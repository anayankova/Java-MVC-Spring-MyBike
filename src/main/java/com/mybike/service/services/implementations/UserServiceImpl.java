package com.mybike.service.services.implementations;

import com.mybike.data.entities.User;
import com.mybike.data.repositories.UsersRepository;
import com.mybike.error.Constants;
import com.mybike.error.UserNotFoundException;
import com.mybike.service.models.LoginUserServiceModel;
import com.mybike.service.models.RegisterUserServiceModel;
import com.mybike.service.models.UserServiceModel;
import com.mybike.service.services.UserService;
import com.mybike.service.services.UserValidationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserValidationService userValidationService;
    private final UsersRepository usersRepository;
    private final ModelMapper mapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void register(RegisterUserServiceModel model) throws Exception {
        if (!userValidationService.isValid(model)) {
            throw new Exception(Constants.USER_INVALID);
        }
        User user = mapper.map(model, User.class);
        user.setPassword(this.bCryptPasswordEncoder.encode(model.getPassword()));
        usersRepository.save(user);
    }

    @Override
    public LoginUserServiceModel login(RegisterUserServiceModel model) {
        String passwordHash = this.bCryptPasswordEncoder.encode(model.getPassword());
        return usersRepository
                .findByUsernameAndPassword(model.getUsername(), passwordHash)
                .map(user -> new LoginUserServiceModel(model.getUsername(), passwordHash))
                .orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND));
    }

    @Override
    public UserServiceModel findUserByUserName(String username) {
        return this.usersRepository.findByUsername(username)
                .map(user -> this.mapper.map(user, UserServiceModel.class))
                .orElseThrow(() -> new UserNotFoundException(Constants.USERNAME_NOT_FOUND));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = usersRepository.findByUsernameContains(s);

        Set<GrantedAuthority> authorities = new HashSet<>(user.getAuthorities());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
