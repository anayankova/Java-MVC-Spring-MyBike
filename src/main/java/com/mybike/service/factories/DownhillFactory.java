package com.mybike.service.factories;

import com.mybike.data.entities.Downhill;
import com.mybike.data.entities.dh_enum.Brakes;
import com.mybike.data.entities.dh_enum.Fork;
import com.mybike.data.entities.dh_enum.Frame;
import com.mybike.data.entities.dh_enum.Tires;

public interface DownhillFactory {
    Downhill create(String name, Frame frame, Fork fork, Tires tires, Brakes brakes);
}
