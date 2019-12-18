package com.mybike.service.services;

import com.mybike.data.entities.Crosscountry;
import com.mybike.data.entities.User;
import com.mybike.data.entities.xc_enum.Brakes;
import com.mybike.data.entities.xc_enum.Fork;
import com.mybike.data.entities.xc_enum.Frame;
import com.mybike.data.entities.xc_enum.Tires;
import com.mybike.data.repositories.CrosscountryRepository;
import com.mybike.data.repositories.UsersRepository;
import com.mybike.service.base.ServiceTestBase;
import com.mybike.service.models.CrosscountryServiceModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CrosscountryServiceTest extends ServiceTestBase {

    @MockBean
    UsersRepository usersRepository;

    @MockBean
    CrosscountryRepository crosscountryRepository;

    @Autowired
    CrosscountryService crosscountryService;

    @Test
    void createCrosscountryForUser_whenCrosscountryNameIsNull_shouldThrowException() {
        User user = new User();
        user.setUsername("Martin");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
        String crosscountryName = null;
        CrosscountryServiceModel model = new CrosscountryServiceModel(crosscountryName,
                Frame.SOBATO, Fork.FOX, Tires.TIRES27, Brakes.MAGURAMT7);

        assertThrows(Exception.class, () -> crosscountryService.createCrosscountry(user.getUsername(), model));
    }

    @Test
    void createCrosscountryForUser_whenCrosscountryNameIsEmpty_shouldThrowException() {
        User user = new User();
        user.setUsername("Martin");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
        String crosscountryName = "";
        CrosscountryServiceModel model = new CrosscountryServiceModel(crosscountryName,
                Frame.SOBATO, Fork.FOX, Tires.TIRES27, Brakes.MAGURAMT7);

        assertThrows(Exception.class, () -> crosscountryService.createCrosscountry(user.getUsername(), model));
    }

    @Test
    void creatCrosscountryForUser_whenCrosscountryHasName_shouldCreateCrosscountryForUser() throws Exception {
        User user = new User();
        user.setUsername("Martin");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
        String crosscountryName = "CrosscountryRace";
        CrosscountryServiceModel model = new CrosscountryServiceModel(crosscountryName,
                Frame.SOBATO, Fork.FOX, Tires.TIRES27, Brakes.MAGURAMT7);
        Crosscountry crosscountry = crosscountryService.createCrosscountry(user.getUsername(), model);

        assertEquals(crosscountryName, crosscountry.getName());
        assertEquals(user.getUsername(), crosscountry.getOwner().getUsername());
    }

    @Test
    void deleteCrosscountry_whenCrosscountryExists_shouldDeleteCrosscountry() throws Exception {
        Crosscountry crosscountry = new Crosscountry();
        Long crosscountryId = 1L;
        crosscountry.setId(crosscountryId);
        Mockito.when(crosscountryRepository.findById(crosscountryId)).thenReturn(Optional.of(crosscountry));
        this.crosscountryService.deleteCrosscountry(crosscountry.getId());

        assertEquals(0, crosscountryRepository.count());
    }

    @Test
    void getAllCrosscountryBikesByUsername_whenUserExists_shouldReturnCrosscountryBikes() throws Exception {
        User user = new User();
        user.setUsername("Victor");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
        String crosscountryName = "CrosscountryRace";
        CrosscountryServiceModel model = new CrosscountryServiceModel(crosscountryName,
                Frame.SOBATO, Fork.FOX, Tires.TIRES27, Brakes.MAGURAMT7);
        Crosscountry crosscountry = crosscountryService.createCrosscountry(user.getUsername(), model);
        Mockito.when(crosscountryRepository.findAll()).thenReturn(List.of(crosscountry));
        Set<Crosscountry> crosscountryBikes = crosscountryService.getAllCrosscountryBikesByUsername(user.getUsername());

        assertEquals(1, crosscountryBikes.size());
    }
}
