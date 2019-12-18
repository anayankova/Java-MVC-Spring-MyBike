package com.mybike.service.services;

import com.mybike.data.entities.Bmx;
import com.mybike.data.entities.User;
import com.mybike.data.entities.bmx_enum.Frame;
import com.mybike.data.entities.bmx_enum.Tires;
import com.mybike.data.repositories.BmxRepository;
import com.mybike.data.repositories.UsersRepository;
import com.mybike.service.base.ServiceTestBase;
import com.mybike.service.models.BmxServiceModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BmxServiceTest extends ServiceTestBase {

    @MockBean
    UsersRepository usersRepository;

    @MockBean
    BmxRepository bmxRepository;

    @Autowired
    BmxService bmxService;

    @Test
    void createBmxForUser_whenBmxNameIsNull_shouldThrowException() {
        User user = new User();
        user.setUsername("Martin");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
        String bmxName = null;
        BmxServiceModel model = new BmxServiceModel(bmxName, Frame.KINK, Tires.VANS);

        assertThrows(Exception.class, () -> bmxService.createBmx(user.getUsername(), model));
    }

    @Test
    void createBmxForUser_whenBmxNameIsEmpty_shouldThrowException() {
        User user = new User();
        user.setUsername("Martin");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
        String bmxName = "";
        BmxServiceModel model = new BmxServiceModel(bmxName, Frame.KINK, Tires.VANS);

        assertThrows(Exception.class, () -> bmxService.createBmx(user.getUsername(), model));
    }

    @Test
    void creatBmxForUser_whenBmxHasName_shouldCreateBmxForUser() throws Exception {
        User user = new User();
        user.setUsername("Martin");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
        String bmxName = "BmxRace";
        BmxServiceModel model = new BmxServiceModel(bmxName, Frame.KINK, Tires.VANS);
        Bmx bmx = bmxService.createBmx(user.getUsername(), model);

        assertEquals(bmxName, bmx.getName());
        assertEquals(user.getUsername(), bmx.getOwner().getUsername());
    }

    @Test
    void deleteBmx_whenBmxExists_shouldDeleteBmx() throws Exception {
        Bmx bmx = new Bmx();
        Long bmxId = 1L;
        bmx.setId(bmxId);
        Mockito.when(bmxRepository.findById(bmxId)).thenReturn(Optional.of(bmx));
        this.bmxService.deleteBmx(bmx.getId());

        assertEquals(0, bmxRepository.count());
    }

    @Test
    void getAllBmxBikesByUsername_whenUserExists_shouldReturnBmxBikes() throws Exception {
        User user = new User();
        user.setUsername("Victor");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
        String bmxName = "BmxRace";
        BmxServiceModel model = new BmxServiceModel(bmxName, Frame.KINK, Tires.VANS);
        Bmx bmx = bmxService.createBmx(user.getUsername(), model);
        Mockito.when(bmxRepository.findAll()).thenReturn(List.of(bmx));
        Set<Bmx> bmxBikes = bmxService.getAllBmxBikesByUsername(user.getUsername());

        assertEquals(1, bmxBikes.size());
    }
}
