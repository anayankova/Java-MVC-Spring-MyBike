package com.mybike.service.services.implementations;

import com.mybike.data.entities.Bmx;
import com.mybike.data.entities.User;
import com.mybike.data.repositories.BmxRepository;
import com.mybike.data.repositories.UsersRepository;
import com.mybike.error.Constants;
import com.mybike.service.factories.BmxFactory;
import com.mybike.service.models.BmxServiceModel;
import com.mybike.service.services.BmxService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BmxServiceImpl implements BmxService {

    private final BmxFactory bmxFactory;
    private final BmxRepository bmxRepository;
    private final UsersRepository usersRepository;
    private final ModelMapper mapper;

    @Override
    public BmxServiceModel findBmxById(Long id) throws Exception {
        return this.bmxRepository.findById(id)
                .map(e -> this.mapper.map(e, BmxServiceModel.class))
                .orElseThrow(() -> new Exception(Constants.BMXID_NOT_FOUND));
    }

    @Override
    public Bmx createBmx(String username, BmxServiceModel model) throws Exception {
        if(model.getName().isEmpty() || model.getName() == null) {
            throw new Exception(Constants.BMXNAME_INVALID);
        }
        User user = usersRepository.findByUsernameContains(username);
        Bmx bmx = bmxFactory.create(model.getName(), model.getFrame(), model.getTires());
        bmx.setOwner(user);
        bmxRepository.save(bmx);
        return bmx;
    }

    @Override
    public void deleteBmx(Long id) throws Exception {
        Bmx bmx = this.bmxRepository.findById(id)
                .orElseThrow(() -> new Exception(Constants.BMXID_NOT_FOUND));
        this.bmxRepository.delete(bmx);

    }

    @Override
    public BmxServiceModel editBmx(Long id, BmxServiceModel model) throws Exception {
        Bmx bmx = this.bmxRepository.findById(id)
                .orElseThrow(() -> new Exception(Constants.BMXID_NOT_FOUND));
        bmx.setName(model.getName());
        bmx.setFrame(model.getFrame());
        bmx.setTires(model.getTires());
        return this.mapper.map(this.bmxRepository.save(bmx), BmxServiceModel.class);
    }

    @Override
    public Set<Bmx> getAllBmxBikesByUsername(String username) {
        return this.bmxRepository.findAll().stream()
                .filter(e -> e.getOwner().getUsername().equals(username))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
