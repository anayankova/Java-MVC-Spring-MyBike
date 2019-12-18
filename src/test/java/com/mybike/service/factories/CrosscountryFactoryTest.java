package com.mybike.service.factories;

import com.mybike.data.entities.Crosscountry;
import com.mybike.data.entities.xc_enum.Brakes;
import com.mybike.data.entities.xc_enum.Fork;
import com.mybike.data.entities.xc_enum.Frame;
import com.mybike.data.entities.xc_enum.Tires;
import com.mybike.service.base.ServiceTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CrosscountryFactoryTest extends ServiceTestBase {

    @Autowired
    CrosscountryFactory factory;

    @Test
    void createCrosscountryFactory_shouldReturnCrosscountryWithAttributes() {
        String name = "CrossCountry";
        Frame frame = Frame.SOBATO;
        Fork fork = Fork.FOX;
        Tires tires = Tires.TIRES27;
        Brakes brakes = Brakes.MAGURAMT7;

        Crosscountry crosscountry = factory.create(name, frame, fork, tires, brakes);

        assertEquals(name, crosscountry.getName());
        assertEquals(frame, crosscountry.getFrame());
        assertEquals(fork, crosscountry.getFork());
        assertEquals(tires, crosscountry.getTires());
        assertEquals(brakes, crosscountry.getBrakes());
    }
}
