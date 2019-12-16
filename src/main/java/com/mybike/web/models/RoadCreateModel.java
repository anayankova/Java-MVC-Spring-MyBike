package com.mybike.web.models;

import com.mybike.data.entities.road_enum.Frame;
import com.mybike.data.entities.road_enum.Tires;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoadCreateModel {
    private String name;
    private Frame frame;
    private Tires tires;
}
