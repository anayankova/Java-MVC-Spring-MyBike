package com.mybike.service.services.implementations;

import com.mybike.data.entities.User;
import com.mybike.data.entities.enduro_enum.Brakes;
import com.mybike.data.entities.enduro_enum.Fork;
import com.mybike.data.entities.enduro_enum.Frame;
import com.mybike.data.entities.enduro_enum.Tires;
import com.mybike.data.repositories.EnduroRepository;
import com.mybike.data.repositories.UsersRepository;
import com.mybike.service.factories.EnduroFactory;
import com.mybike.service.models.EnduroCreateServiceModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void createEnduroForUser() throws Exception {
        User user = new User();
        user.setUsername("Martin");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
        EnduroCreateServiceModel enduroCreateServiceModel = new EnduroCreateServiceModel("enduroSome",
                Frame.SCOTT, Fork.FOX, Tires.TIRES26, Brakes.MAGURAMT7);
        service.create(user.getUsername(), enduroCreateServiceModel);
        assertEquals(1, user.getEnduroBikes().size());
    }

    @Test
    void getAllEnduroBikesByUsername() {
    }

    @Test
    void getAllEnduroBikes() {
    }
}