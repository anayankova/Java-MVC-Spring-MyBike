package com.mybike.service.services.implementations;

import com.mybike.data.entities.Enduro;
import com.mybike.data.entities.User;
import com.mybike.data.repositories.EnduroRepository;
import com.mybike.data.repositories.UsersRepository;
import com.mybike.service.factories.EnduroFactory;
import com.mybike.service.models.EnduroCreateServiceModel;
import com.mybike.service.models.EnduroServiceModel;
import com.mybike.service.services.EnduroService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EnduroServiceImpl implements EnduroService {

    private final EnduroFactory enduroFactory;
    private final EnduroRepository enduroRepository;
    private final UsersRepository usersRepository;
    private final ModelMapper mapper;

    public EnduroServiceImpl(EnduroFactory enduroFactory,
                             EnduroRepository enduroRepository,
                             UsersRepository usersRepository,
                             ModelMapper mapper) {
        this.enduroFactory = enduroFactory;
        this.enduroRepository = enduroRepository;
        this.usersRepository = usersRepository;
        this.mapper = mapper;
    }

    @Override
    public Enduro create(String username, EnduroCreateServiceModel model) {
        User user = usersRepository.findByUsernameContains(username);
        Enduro enduro = enduroFactory.create(model.getName(), model.getFrame(),
                model.getFork(), model.getTires(), model.getBrakes());
        enduro.setOwner(user);
        enduroRepository.save(enduro);
        return enduro;
    }

    @Override
    public Set<Enduro> getAllEnduroBikesByUsername(String username) {
        return this.enduroRepository.findAll().stream()
                .filter(e -> e.getOwner().getUsername().equals(username))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public List<EnduroServiceModel> getAllEnduroBikes() {
        return this.enduroRepository.findAll()
                .stream()
                .map(e -> this.mapper.map(e, EnduroServiceModel.class))
                .collect(Collectors.toList());
    }
}
