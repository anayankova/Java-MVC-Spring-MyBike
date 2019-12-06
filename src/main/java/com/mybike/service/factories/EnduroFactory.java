package com.mybike.service.factories;

import com.mybike.data.entities.Enduro;
import com.mybike.data.entities.enduro_enum.Brakes;
import com.mybike.data.entities.enduro_enum.Fork;
import com.mybike.data.entities.enduro_enum.Frame;
import com.mybike.data.entities.enduro_enum.Tires;

public interface EnduroFactory {
    Enduro create(String name, Frame frame, Fork fork, Tires tires, Brakes brakes);
}
