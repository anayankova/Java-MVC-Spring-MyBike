package com.mybike.service.services.implementations;

import com.mybike.data.entities.Crosscountry;
import com.mybike.data.entities.User;
import com.mybike.data.repositories.CrosscountryRepository;
import com.mybike.data.repositories.UsersRepository;
import com.mybike.error.Constants;
import com.mybike.service.factories.CrosscountryFactory;
import com.mybike.service.models.CrosscountryServiceModel;
import com.mybike.service.services.CrosscountryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CrosscountryServiceImpl implements CrosscountryService {

    private final CrosscountryFactory crosscountryFactory;
    private final CrosscountryRepository crosscountryRepository;
    private final UsersRepository usersRepository;
    private final ModelMapper mapper;

    @Override
    public CrosscountryServiceModel findCrosscountryById(Long id) throws Exception {
        return this.crosscountryRepository.findById(id)
                .map(e -> this.mapper.map(e, CrosscountryServiceModel.class))
                .orElseThrow(() -> new Exception(Constants.XCID_NOT_FOUND));
    }

    @Override
    public Crosscountry createCrosscountry(String username, CrosscountryServiceModel model) throws Exception {
        if(model.getName().isEmpty() || model.getName() == null) {
            throw new Exception(Constants.XC_INVALID);
        }
        User user = usersRepository.findByUsernameContains(username);
        Crosscountry crosscountry = crosscountryFactory.create(model.getName(), model.getFrame(),
                model.getFork(), model.getTires(), model.getBrakes());
        crosscountry.setOwner(user);
        crosscountryRepository.save(crosscountry);
        return crosscountry;
    }

    @Override
    public void deleteCrosscountry(Long id) throws Exception {
        Crosscountry crosscountry = this.crosscountryRepository.findById(id)
                .orElseThrow(() -> new Exception(Constants.XCID_NOT_FOUND));
        this.crosscountryRepository.delete(crosscountry);

    }

    @Override
    public CrosscountryServiceModel editCrosscountry(Long id, CrosscountryServiceModel model) throws Exception {
        Crosscountry crosscountry = this.crosscountryRepository.findById(id)
                .orElseThrow(() -> new Exception(Constants.XCID_NOT_FOUND));
        crosscountry.setName(model.getName());
        crosscountry.setFrame(model.getFrame());
        crosscountry.setFork(model.getFork());
        crosscountry.setTires(model.getTires());
        crosscountry.setBrakes(model.getBrakes());
        return this.mapper.map(this.crosscountryRepository.save(crosscountry), CrosscountryServiceModel.class);
    }

    @Override
    public Set<Crosscountry> getAllCrosscountryBikesByUsername(String username) {
        return this.crosscountryRepository.findAll().stream()
                .filter(e -> e.getOwner().getUsername().equals(username))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
