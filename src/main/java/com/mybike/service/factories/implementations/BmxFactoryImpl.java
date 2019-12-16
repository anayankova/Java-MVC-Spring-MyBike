package com.mybike.service.factories.implementations;

import com.mybike.config.annotations.Factory;
import com.mybike.data.entities.Bmx;
import com.mybike.data.entities.bmx_enum.Frame;
import com.mybike.data.entities.bmx_enum.Tires;
import com.mybike.service.factories.BmxFactory;

@Factory
public class BmxFactoryImpl implements BmxFactory {
    @Override
    public Bmx create(String name, Frame frame, Tires tires) {
        Bmx bmx = new Bmx();
        bmx.setName(name);
        bmx.setFrame(frame);
        bmx.setTires(tires);
        return bmx;
    }
}
