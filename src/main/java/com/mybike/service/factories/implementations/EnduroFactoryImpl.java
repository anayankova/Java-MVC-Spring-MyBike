package com.mybike.service.factories.implementations;

import com.mybike.config.annotations.Factory;
import com.mybike.data.entities.Enduro;
import com.mybike.data.entities.enduro_enum.Brakes;
import com.mybike.data.entities.enduro_enum.Fork;
import com.mybike.data.entities.enduro_enum.Frame;
import com.mybike.data.entities.enduro_enum.Tires;
import com.mybike.service.factories.EnduroFactory;

@Factory
public class EnduroFactoryImpl implements EnduroFactory {
    @Override
    public Enduro create(String name, Frame frame, Fork fork, Tires tires, Brakes brakes) {
        Enduro enduro = new Enduro();
        enduro.setName(name);
        enduro.setFrame(frame);
        enduro.setFork(fork);
        enduro.setTires(tires);
        enduro.setBrakes(brakes);
        return enduro;
    }
}
