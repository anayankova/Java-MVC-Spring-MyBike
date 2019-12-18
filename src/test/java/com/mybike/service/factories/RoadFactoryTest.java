package com.mybike.service.factories;

import com.mybike.data.entities.Road;
import com.mybike.data.entities.road_enum.Frame;
import com.mybike.data.entities.road_enum.Tires;
import com.mybike.service.base.ServiceTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoadFactoryTest extends ServiceTestBase {

    @Autowired
    RoadFactory factory;

    @Test
    void createRoadFactory_shouldReturnRoadWithAttributes() {
        String name = "Road";
        Frame frame = Frame.SWORKS;
        Tires tires = Tires.DETONATOR;

        Road road = factory.create(name, frame, tires);

        assertEquals(name, road.getName());
        assertEquals(frame, road.getFrame());
        assertEquals(tires, road.getTires());
    }
}
