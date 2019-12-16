package com.mybike.web.models;

import com.mybike.data.entities.xc_enum.Brakes;
import com.mybike.data.entities.xc_enum.Fork;
import com.mybike.data.entities.xc_enum.Frame;
import com.mybike.data.entities.xc_enum.Tires;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CrosscountryCreateModel {
    private String name;
    private Frame frame;
    private Fork fork;
    private Tires tires;
    private Brakes brakes;
}
