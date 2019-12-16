package com.mybike.service.factories;

import com.mybike.data.entities.Bmx;
import com.mybike.data.entities.bmx_enum.Frame;
import com.mybike.data.entities.bmx_enum.Tires;

public interface BmxFactory {
    Bmx create(String name, Frame frame, Tires tires);
}
