package com.mybike.service.services;

import com.mybike.data.entities.Bmx;
import com.mybike.service.models.BmxServiceModel;

import java.util.Set;

public interface BmxService {

    BmxServiceModel findBmxById(Long id) throws Exception;

    Bmx createBmx(String username, BmxServiceModel model) throws Exception;

    void deleteBmx(Long id) throws Exception;

    BmxServiceModel editBmx(Long id, BmxServiceModel model) throws Exception;

    Set<Bmx> getAllBmxBikesByUsername(String username);
}
