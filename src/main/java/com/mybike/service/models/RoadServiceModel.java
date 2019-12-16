package com.mybike.service.models;

import com.mybike.data.entities.road_enum.Frame;
import com.mybike.data.entities.road_enum.Tires;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoadServiceModel {
    private String name;
    private Frame frame;
    private Tires tires;
}
