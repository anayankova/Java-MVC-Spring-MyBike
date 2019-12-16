package com.mybike.service.services.implementations;

import com.mybike.data.entities.Road;
import com.mybike.data.entities.User;
import com.mybike.data.repositories.RoadRepository;
import com.mybike.data.repositories.UsersRepository;
import com.mybike.error.Constants;
import com.mybike.service.factories.RoadFactory;
import com.mybike.service.models.RoadServiceModel;
import com.mybike.service.services.RoadService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoadServiceImpl implements RoadService {

    private final RoadFactory roadFactory;
    private final RoadRepository roadRepository;
    private final UsersRepository usersRepository;
    private final ModelMapper mapper;

    @Override
    public RoadServiceModel findRoadById(Long id) throws Exception {
        return this.roadRepository.findById(id)
                .map(e -> this.mapper.map(e, RoadServiceModel.class))
                .orElseThrow(() -> new Exception(Constants.ROADID_NOT_FOUND));
    }

    @Override
    public Road createRoad(String username, RoadServiceModel model) throws Exception {
        if(model.getName().isEmpty() || model.getName() == null) {
            throw new Exception(Constants.ROAD_INVALID);
        }
        User user = usersRepository.findByUsernameContains(username);
        Road road = roadFactory.create(model.getName(), model.getFrame(), model.getTires());
        road.setOwner(user);
        roadRepository.save(road);
        return road;
    }

    @Override
    public void deleteRoad(Long id) throws Exception {
        Road road = this.roadRepository.findById(id)
                .orElseThrow(() -> new Exception(Constants.ROADID_NOT_FOUND));
        this.roadRepository.delete(road);

    }

    @Override
    public RoadServiceModel editRoad(Long id, RoadServiceModel model) throws Exception {
        Road road = this.roadRepository.findById(id)
                .orElseThrow(() -> new Exception(Constants.ROADID_NOT_FOUND));
        road.setName(model.getName());
        road.setFrame(model.getFrame());
        road.setTires(model.getTires());
        return this.mapper.map(this.roadRepository.save(road), RoadServiceModel.class);
    }

    @Override
    public Set<Road> getAllRoadBikesByUsername(String username) {
        return this.roadRepository.findAll().stream()
                .filter(e -> e.getOwner().getUsername().equals(username))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
