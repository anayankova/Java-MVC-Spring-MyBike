package com.mybike.web.models;

import com.mybike.data.entities.enduro_enum.Brakes;
import com.mybike.data.entities.enduro_enum.Fork;
import com.mybike.data.entities.enduro_enum.Frame;
import com.mybike.data.entities.enduro_enum.Tires;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnduroCreateModel {
    private String name;
    private Frame frame;
    private Fork fork;
    private Tires tires;
    private Brakes brakes;
}
