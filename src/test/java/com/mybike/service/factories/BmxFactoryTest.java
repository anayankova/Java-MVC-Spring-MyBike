package com.mybike.service.factories;

import com.mybike.data.entities.Bmx;
import com.mybike.data.entities.bmx_enum.Frame;
import com.mybike.data.entities.bmx_enum.Tires;
import com.mybike.service.base.ServiceTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BmxFactoryTest extends ServiceTestBase {

    @Autowired
    BmxFactory factory;

    @Test
    void createBmxFactory_shouldReturnBmxWithAttributes() {
        String name = "BMX";
        Frame frame = Frame.KINK;
        Tires tires = Tires.VANS;

        Bmx bmx = factory.create(name, frame, tires);

        assertEquals(name, bmx.getName());
        assertEquals(frame, bmx.getFrame());
        assertEquals(tires, bmx.getTires());
    }
}
