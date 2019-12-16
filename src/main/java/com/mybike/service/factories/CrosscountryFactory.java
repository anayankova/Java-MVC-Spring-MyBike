package com.mybike.service.factories;

import com.mybike.data.entities.Crosscountry;
import com.mybike.data.entities.xc_enum.Brakes;
import com.mybike.data.entities.xc_enum.Fork;
import com.mybike.data.entities.xc_enum.Frame;
import com.mybike.data.entities.xc_enum.Tires;

public interface CrosscountryFactory {
    Crosscountry create(String name, Frame frame, Fork fork, Tires tires, Brakes brakes);
}
