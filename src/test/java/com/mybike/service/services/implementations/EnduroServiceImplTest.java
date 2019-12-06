package com.mybike.service.services.implementations;

import com.mybike.data.entities.User;
import com.mybike.data.repositories.EnduroRepository;
import com.mybike.data.repositories.UsersRepository;
import com.mybike.service.factories.EnduroFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

class EnduroServiceImplTest {

    EnduroFactory enduroFactory;
    UsersRepository usersRepository;
    EnduroRepository enduroRepository;

    EnduroServiceImpl service;

    @BeforeEach
    void setupTest() {
        enduroFactory = Mockito.mock(EnduroFactory.class);
        usersRepository = Mockito.mock(UsersRepository.class);
        enduroRepository = Mockito.mock(EnduroRepository.class);
        ModelMapper mapper = new ModelMapper();
        service = new EnduroServiceImpl(enduroFactory, usersRepository, enduroRepository, mapper);


    }

    @Test
    void createEnduroForUser_whenUserDoesNotExist_shouldThrowException() {
    }

    @Test
    void createEnduroForUser() {
        User user = new User();
        user.setUsername("Martin");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
    }

    @Test
    void getAllEnduroBikesByUsername() {
    }

    @Test
    void getAllEnduroBikes() {
    }
}