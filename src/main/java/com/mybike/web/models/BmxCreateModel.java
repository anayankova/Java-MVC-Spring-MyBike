package com.mybike.web.models;

import com.mybike.data.entities.bmx_enum.Frame;
import com.mybike.data.entities.bmx_enum.Tires;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BmxCreateModel {
    private String name;
    private Frame frame;
    private Tires tires;
}
