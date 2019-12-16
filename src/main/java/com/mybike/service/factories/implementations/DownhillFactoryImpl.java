package com.mybike.service.factories.implementations;

import com.mybike.config.annotations.Factory;
import com.mybike.data.entities.Downhill;
import com.mybike.data.entities.dh_enum.Brakes;
import com.mybike.data.entities.dh_enum.Fork;
import com.mybike.data.entities.dh_enum.Frame;
import com.mybike.data.entities.dh_enum.Tires;
import com.mybike.service.factories.DownhillFactory;

@Factory
public class DownhillFactoryImpl implements DownhillFactory {
    @Override
    public Downhill create(String name, Frame frame, Fork fork, Tires tires, Brakes brakes) {
        Downhill downhill = new Downhill();
        downhill.setName(name);
        downhill.setFrame(frame);
        downhill.setFork(fork);
        downhill.setTires(tires);
        downhill.setBrakes(brakes);
        return downhill;
    }
}
