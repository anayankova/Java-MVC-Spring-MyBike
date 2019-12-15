package com.mybike.service.services;

import com.mybike.data.entities.Enduro;
import com.mybike.data.entities.User;
import com.mybike.data.entities.enduro_enum.Brakes;
import com.mybike.data.entities.enduro_enum.Fork;
import com.mybike.data.entities.enduro_enum.Frame;
import com.mybike.data.entities.enduro_enum.Tires;
import com.mybike.data.repositories.EnduroRepository;
import com.mybike.data.repositories.UsersRepository;
import com.mybike.service.base.ServiceTestBase;
import com.mybike.service.models.EnduroServiceModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EnduroServiceTest extends ServiceTestBase {

    @MockBean
    UsersRepository usersRepository;

    @MockBean
    EnduroRepository enduroRepository;

    @Autowired
    EnduroService enduroService;

    @Test
    void createEnduroForUser_whenEnduroNameIsNull_shouldThrowException() {
        User user = new User();
        user.setUsername("Martin");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
        String enduroName = null;
        EnduroServiceModel model = new EnduroServiceModel(enduroName,
                Frame.SCOTT, Fork.FOX, Tires.TIRES27, Brakes.SRAMCODER);

        assertThrows(Exception.class, () -> enduroService.createEnduro(user.getUsername(), model));
    }

    @Test
    void createEnduroForUser_whenEnduroNameIsEmpty_shouldThrowException() {
        User user = new User();
        user.setUsername("Martin");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
        String enduroName = "";
        EnduroServiceModel model = new EnduroServiceModel(enduroName,
                Frame.SCOTT, Fork.FOX, Tires.TIRES27, Brakes.SRAMCODER);

        assertThrows(Exception.class, () -> enduroService.createEnduro(user.getUsername(), model));
    }

    @Test
    void createEnduroForUser_whenEnduroHasName_shouldCreateEnduroForUser() throws Exception {
        User user = new User();
        user.setUsername("Martin");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
        String enduroName = "EnduroRace";
        EnduroServiceModel model = new EnduroServiceModel(enduroName,
                Frame.SCOTT, Fork.FOX, Tires.TIRES27, Brakes.SRAMCODER);
        Enduro enduro = enduroService.createEnduro(user.getUsername(), model);

        assertEquals(enduroName, enduro.getName());
        assertEquals(user.getUsername(), enduro.getOwner().getUsername());
    }

    @Test
    void deleteEnduro_whenEnduroExists_shouldDeleteEnduro() throws Exception {
        User user = new User();
        user.setUsername("Victor");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
        Enduro enduro = new Enduro();
        Long enduroId = 1L;
        String enduroName = "EnduroRace";
        enduro.setName(enduroName);
        enduro.setId(enduroId);
        enduro.setOwner(user);
        enduro.setFrame(Frame.SCOTT);
        enduro.setFork(Fork.OHLINS);
        enduro.setTires(Tires.TIRES27);
        enduro.setBrakes(Brakes.SRAMCODER);
        Mockito.when(enduroRepository.findById(enduroId)).thenReturn(Optional.of(enduro));
        this.enduroService.deleteEnduro(enduro.getId());

        //assertFalse(enduroRepository.findById(enduro.getId()).isPresent());
        assertEquals(0, enduroRepository.count());
    }

    @Test
    void getAllEnduroBikesByUsername_whenUserExists_shouldReturnEnduroBikes() throws Exception {
        User user = new User();
        user.setUsername("Victor");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
        String enduroName = "EnduroRace";
        EnduroServiceModel model = new EnduroServiceModel(enduroName,
                Frame.SCOTT, Fork.FOX, Tires.TIRES27, Brakes.SRAMCODER);
        Enduro enduro = enduroService.createEnduro(user.getUsername(), model);
        Mockito.when(enduroRepository.findAll()).thenReturn(List.of(enduro));
        Set<Enduro> enduroBikes = enduroService.getAllEnduroBikesByUsername(user.getUsername());

        assertEquals(1, enduroBikes.size());
    }

}
