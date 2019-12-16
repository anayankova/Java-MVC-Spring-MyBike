package com.mybike.service.factories.implementations;

import com.mybike.config.annotations.Factory;
import com.mybike.data.entities.Road;
import com.mybike.data.entities.road_enum.Frame;
import com.mybike.data.entities.road_enum.Tires;
import com.mybike.service.factories.RoadFactory;

@Factory
public class RoadFactoryImpl implements RoadFactory {
    @Override
    public Road create(String name, Frame frame, Tires tires) {
        Road road = new Road();
        road.setName(name);
        road.setFrame(frame);
        road.setTires(tires);
        return road;
    }
}
