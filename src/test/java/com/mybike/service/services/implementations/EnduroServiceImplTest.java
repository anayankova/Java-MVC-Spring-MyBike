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
    EnduroRepository enduroRepository;
    UsersRepository usersRepository;

    EnduroServiceImpl service;

    @BeforeEach
    void setupTest() {
        enduroFactory = Mockito.mock(EnduroFactory.class);
        enduroRepository = Mockito.mock(EnduroRepository.class);
        usersRepository = Mockito.mock(UsersRepository.class);
        ModelMapper mapper = new ModelMapper();
        service = new EnduroServiceImpl(enduroFactory, enduroRepository, usersRepository, mapper);
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