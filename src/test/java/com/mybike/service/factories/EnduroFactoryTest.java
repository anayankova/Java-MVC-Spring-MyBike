package com.mybike.service.factories;

import com.mybike.data.entities.Enduro;
import com.mybike.data.entities.enduro_enum.Brakes;
import com.mybike.data.entities.enduro_enum.Fork;
import com.mybike.data.entities.enduro_enum.Frame;
import com.mybike.data.entities.enduro_enum.Tires;
import com.mybike.service.base.ServiceTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class EnduroFactoryTest extends ServiceTestBase {

    @Autowired
    EnduroFactory factory;

    @Test
    void createEnduroFactory_shouldReturnEnduroWithAttributes() {
        String name = "Enduro";
        Frame frame = Frame.SCOTT;
        Fork fork = Fork.FOX;
        Tires tires = Tires.TIRES27;
        Brakes brakes = Brakes.MAGURAMT7;

        Enduro enduro = factory.create(name, frame, fork, tires, brakes);

        assertEquals(name, enduro.getName());
        assertEquals(frame, enduro.getFrame());
        assertEquals(fork, enduro.getFork());
        assertEquals(tires, enduro.getTires());
        assertEquals(brakes, enduro.getBrakes());
    }
}
