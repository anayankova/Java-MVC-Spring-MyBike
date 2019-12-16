package com.mybike.web.models;

import com.mybike.data.entities.dh_enum.Brakes;
import com.mybike.data.entities.dh_enum.Fork;
import com.mybike.data.entities.dh_enum.Frame;
import com.mybike.data.entities.dh_enum.Tires;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DownhillCreateModel {
    private String name;
    private Frame frame;
    private Fork fork;
    private Tires tires;
    private Brakes brakes;
}
