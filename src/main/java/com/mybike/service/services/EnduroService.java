package com.mybike.service.services;

import com.mybike.data.entities.Enduro;
import com.mybike.service.models.EnduroCreateServiceModel;
import com.mybike.service.models.EnduroServiceModel;

import java.util.List;
import java.util.Set;

public interface EnduroService {
    Enduro create(String username, EnduroCreateServiceModel model);

    Set<Enduro> getAllEnduroBikesByUsername(String username);

    List<EnduroServiceModel> getAllEnduroBikes();
}
