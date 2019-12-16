package com.mybike.service.factories;

import com.mybike.data.entities.Road;
import com.mybike.data.entities.road_enum.Frame;
import com.mybike.data.entities.road_enum.Tires;

public interface RoadFactory {
    Road create(String name, Frame frame, Tires tires);
}
