package com.mybike.service.services;

import com.mybike.data.entities.Road;
import com.mybike.data.entities.User;
import com.mybike.data.entities.road_enum.Frame;
import com.mybike.data.entities.road_enum.Tires;
import com.mybike.data.repositories.RoadRepository;
import com.mybike.data.repositories.UsersRepository;
import com.mybike.service.base.ServiceTestBase;
import com.mybike.service.models.RoadServiceModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoadServiceTest extends ServiceTestBase {
    @MockBean
    UsersRepository usersRepository;

    @MockBean
    RoadRepository roadRepository;

    @Autowired
    RoadService roadService;

    @Test
    void createRoadForUser_whenRoadNameIsNull_shouldThrowException() {
        User user = new User();
        user.setUsername("Martin");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
        String roadName = null;
        RoadServiceModel model = new RoadServiceModel(roadName, Frame.SWORKS, Tires.DETONATOR);

        assertThrows(Exception.class, () -> roadService.createRoad(user.getUsername(), model));
    }

    @Test
    void createRoadForUser_whenRoadNameIsEmpty_shouldThrowException() {
        User user = new User();
        user.setUsername("Martin");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
        String roadName = "";
        RoadServiceModel model = new RoadServiceModel(roadName, Frame.SWORKS, Tires.DETONATOR);

        assertThrows(Exception.class, () -> roadService.createRoad(user.getUsername(), model));
    }

    @Test
    void creatRoadForUser_whenRoadHasName_shouldCreateRoadForUser() throws Exception {
        User user = new User();
        user.setUsername("Martin");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
        String roadName = "RoadRace";
        RoadServiceModel model = new RoadServiceModel(roadName, Frame.SWORKS, Tires.DETONATOR);
        Road road = roadService.createRoad(user.getUsername(), model);

        assertEquals(roadName, road.getName());
        assertEquals(user.getUsername(), road.getOwner().getUsername());
    }

    @Test
    void deleteRoad_whenRoadExists_shouldDeleteRoad() throws Exception {
        Road road = new Road();
        Long roadId = 1L;
        road.setId(roadId);
        Mockito.when(roadRepository.findById(roadId)).thenReturn(Optional.of(road));
        this.roadService.deleteRoad(road.getId());

        assertEquals(0, roadRepository.count());
    }

    @Test
    void getAllRoadBikesByUsername_whenUserExists_shouldReturnRoadBikes() throws Exception {
        User user = new User();
        user.setUsername("Victor");
        Mockito.when(usersRepository.findByUsernameContains(user.getUsername()))
                .thenReturn(user);
        String roadName = "RoadRace";
        RoadServiceModel model = new RoadServiceModel(roadName, Frame.SWORKS, Tires.DETONATOR);
        Road road = roadService.createRoad(user.getUsername(), model);
        Mockito.when(roadRepository.findAll()).thenReturn(List.of(road));
        Set<Road> roadBikes = roadService.getAllRoadBikesByUsername(user.getUsername());

        assertEquals(1, roadBikes.size());
    }

}
