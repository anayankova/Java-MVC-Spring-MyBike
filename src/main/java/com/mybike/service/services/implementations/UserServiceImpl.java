package com.mybike.service.services.implementations;

import com.mybike.data.entities.User;
import com.mybike.data.repositories.UsersRepository;
import com.mybike.error.Constants;
import com.mybike.error.UserNotFoundException;
import com.mybike.service.models.LoginUserServiceModel;
import com.mybike.service.models.RegisterUserServiceModel;
import com.mybike.service.models.UserServiceModel;
import com.mybike.service.services.HashService;
import com.mybike.service.services.UserService;
import com.mybike.service.services.UserValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserValidationService userValidationService;
    private final UsersRepository usersRepository;
    private final ModelMapper mapper;
    private final HashService hashService;

    public UserServiceImpl(UserValidationService userValidationService,
                           UsersRepository usersRepository,
                           ModelMapper mapper,
                           HashService hashService) {
        this.userValidationService = userValidationService;
        this.usersRepository = usersRepository;
        this.mapper = mapper;
        this.hashService = hashService;
    }

    @Override
    public void register(RegisterUserServiceModel model) throws Exception {
        if (!userValidationService.isValid(model)) {
            // todo: empty parameters
            throw new Exception("Invalid user");
        }
        User user = mapper.map(model, User.class);
        user.setPassword(hashService.hash(user.getPassword()));
        usersRepository.save(user);
    }

    @Override
    public LoginUserServiceModel login(RegisterUserServiceModel model) {
        String passwordHash = hashService.hash(model.getPassword());
        return usersRepository
                .findByUsernameAndPassword(model.getUsername(), passwordHash)
                .map(user -> new LoginUserServiceModel(model.getUsername(), passwordHash))
                .orElseThrow(() -> new UserNotFoundException("Invalid user"));
    }

    @Override
    public UserServiceModel findUserByUserName(String username) {
        return this.usersRepository.findByUsername(username)
                .map(user -> this.mapper.map(user, UserServiceModel.class))
                .orElseThrow(() -> new UserNotFoundException(Constants.USERNAME_NOT_FOUND));
    }

}
