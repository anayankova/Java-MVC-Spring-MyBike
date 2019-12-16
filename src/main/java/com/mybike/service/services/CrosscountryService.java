package com.mybike.service.services;

import com.mybike.data.entities.Crosscountry;
import com.mybike.service.models.CrosscountryServiceModel;

import java.util.Set;

public interface CrosscountryService {

    CrosscountryServiceModel findCrosscountryById(Long id) throws Exception;

    Crosscountry createCrosscountry(String username, CrosscountryServiceModel model) throws Exception;

    void deleteCrosscountry(Long id) throws Exception;

    CrosscountryServiceModel editCrosscountry(Long id, CrosscountryServiceModel model) throws Exception;

    Set<Crosscountry> getAllCrosscountryBikesByUsername(String username);
}
