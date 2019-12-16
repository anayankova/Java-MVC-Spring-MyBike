package com.mybike.service.factories.implementations;

import com.mybike.config.annotations.Factory;
import com.mybike.data.entities.Crosscountry;
import com.mybike.data.entities.xc_enum.Brakes;
import com.mybike.data.entities.xc_enum.Fork;
import com.mybike.data.entities.xc_enum.Frame;
import com.mybike.data.entities.xc_enum.Tires;
import com.mybike.service.factories.CrosscountryFactory;

@Factory
public class CrosscountryFactoryImpl implements CrosscountryFactory {
    @Override
    public Crosscountry create(String name, Frame frame, Fork fork, Tires tires, Brakes brakes) {
        Crosscountry crosscountry = new Crosscountry();
        crosscountry.setName(name);
        crosscountry.setFrame(frame);
        crosscountry.setFork(fork);
        crosscountry.setTires(tires);
        crosscountry.setBrakes(brakes);
        return crosscountry;
    }
}
