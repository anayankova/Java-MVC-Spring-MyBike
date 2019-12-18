package com.mybike.service.factories;

import com.mybike.data.entities.Downhill;
import com.mybike.data.entities.dh_enum.Brakes;
import com.mybike.data.entities.dh_enum.Fork;
import com.mybike.data.entities.dh_enum.Frame;
import com.mybike.data.entities.dh_enum.Tires;
import com.mybike.service.base.ServiceTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DownhillFactoryTest extends ServiceTestBase {

    @Autowired
    DownhillFactory factory;

    @Test
    void createDownhillFactory_shouldReturnDownhillWithAttributes() {
        String name = "Downhill";
        Frame frame = Frame.SANTACRUZ;
        Fork fork = Fork.FOX;
        Tires tires = Tires.KAISER;
        Brakes brakes = Brakes.MAGURAMT7;

        Downhill downhill = factory.create(name, frame, fork, tires, brakes);

        assertEquals(name, downhill.getName());
        assertEquals(frame, downhill.getFrame());
        assertEquals(fork, downhill.getFork());
        assertEquals(tires, downhill.getTires());
        assertEquals(brakes, downhill.getBrakes());
    }

}
