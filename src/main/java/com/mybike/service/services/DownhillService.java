package com.mybike.service.services;

import com.mybike.data.entities.Downhill;
import com.mybike.service.models.DownhillServiceModel;

import java.util.Set;

public interface DownhillService {

    DownhillServiceModel findDownhillById(Long id) throws Exception;

    Downhill createDownhill(String username, DownhillServiceModel model) throws Exception;

    void deleteDownhill(Long id) throws Exception;

    DownhillServiceModel editDownhill(Long id, DownhillServiceModel model) throws Exception;

    Set<Downhill> getAllDownhillBikesByUsername(String username);
}
