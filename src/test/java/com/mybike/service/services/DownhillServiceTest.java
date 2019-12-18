package com.mybike.service.services;

import com.mybike.data.entities.Downhill;
import com.mybike.data.entities.User;
import com.mybike.data.entities.dh_enum.Brakes;
import com.mybike.data.entities.dh_enum.Fork;
import com.mybike.data.entities.dh_enum.Frame;
import com.mybike.data.entities.dh_enum.Tires;
import com.mybike.data.repositories.DownhillRepository;
import com.mybike.data.repositories.UsersRepository;
import com.mybike.service.base.ServiceTestBase;
import com.mybike.service.models.DownhillServiceModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DownhillServiceTest extends ServiceTestBase {

    @MockBean
    UsersRepository usersRepository;

    @MockBean
    DownhillRepository downhillRepository;

    @Autowired
    DownhillService downhillService;

    @Test
    void createDownhillForUser_whenDownhillNameIsNull_shouldThrowException() {
        User user = new User();
        user.setUsername("Martin");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
        String downhillName = null;
        DownhillServiceModel model = new DownhillServiceModel(downhillName,
                Frame.EVIL, Fork.FOX, Tires.MAXXIS, Brakes.MAGURAMT7);

        assertThrows(Exception.class, () -> downhillService.createDownhill(user.getUsername(), model));
    }

    @Test
    void createDownhillForUser_whenDownhillNameIsEmpty_shouldThrowException() {
        User user = new User();
        user.setUsername("Martin");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
        String downhillName = "";
        DownhillServiceModel model = new DownhillServiceModel(downhillName,
                Frame.EVIL, Fork.FOX, Tires.MAXXIS, Brakes.MAGURAMT7);

        assertThrows(Exception.class, () -> downhillService.createDownhill(user.getUsername(), model));
    }

    @Test
    void creatDownhillForUser_whenDownhillHasName_shouldCreateDownhillForUser() throws Exception {
        User user = new User();
        user.setUsername("Martin");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
        String downhillName = "DownhillRace";
        DownhillServiceModel model = new DownhillServiceModel(downhillName,
                Frame.EVIL, Fork.FOX, Tires.MAXXIS, Brakes.MAGURAMT7);
        Downhill downhill = downhillService.createDownhill(user.getUsername(), model);

        assertEquals(downhillName, downhill.getName());
        assertEquals(user.getUsername(), downhill.getOwner().getUsername());
    }

    @Test
    void deleteDownhill_whenDownhillExists_shouldDeleteDownhill() throws Exception {
        Downhill downhill = new Downhill();
        Long downhillId = 1L;
        downhill.setId(downhillId);
        Mockito.when(downhillRepository.findById(downhillId)).thenReturn(Optional.of(downhill));
        this.downhillService.deleteDownhill(downhill.getId());

        assertEquals(0, downhillRepository.count());
    }

    @Test
    void getAllDownhillBikesByUsername_whenUserExists_shouldReturnDownhillBikes() throws Exception {
        User user = new User();
        user.setUsername("Victor");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
        String downhillName = "DownhillRace";
        DownhillServiceModel model = new DownhillServiceModel(downhillName,
                Frame.EVIL, Fork.FOX, Tires.MAXXIS, Brakes.MAGURAMT7);
        Downhill downhill = downhillService.createDownhill(user.getUsername(), model);
        Mockito.when(downhillRepository.findAll()).thenReturn(List.of(downhill));
        Set<Downhill> downhillBikes = downhillService.getAllDownhillBikesByUsername(user.getUsername());

        assertEquals(1, downhillBikes.size());
    }
}
