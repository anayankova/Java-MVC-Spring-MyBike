package com.mybike.service.services;

import com.mybike.data.entities.Enduro;
import com.mybike.service.models.EnduroServiceModel;

import java.util.Set;

public interface EnduroService {

    EnduroServiceModel findEnduroById(Long id) throws Exception;

    Enduro createEnduro(String username, EnduroServiceModel model) throws Exception;

    void deleteEnduro(Long id) throws Exception;

    EnduroServiceModel editEnduro(Long id, EnduroServiceModel model) throws Exception;

    Set<Enduro> getAllEnduroBikesByUsername(String username);

    //List<EnduroServiceModel> getAllEnduroBikes();
}
