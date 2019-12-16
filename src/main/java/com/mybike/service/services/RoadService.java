package com.mybike.service.services;

import com.mybike.data.entities.Road;
import com.mybike.service.models.RoadServiceModel;

import java.util.Set;

public interface RoadService {

    RoadServiceModel findRoadById(Long id) throws Exception;

    Road createRoad(String username, RoadServiceModel model) throws Exception;

    void deleteRoad(Long id) throws Exception;

    RoadServiceModel editRoad(Long id, RoadServiceModel model) throws Exception;

    Set<Road> getAllRoadBikesByUsername(String username);
}
