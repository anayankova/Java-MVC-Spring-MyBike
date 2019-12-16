package com.mybike.service.services.implementations;

import com.mybike.data.entities.Downhill;
import com.mybike.data.entities.User;
import com.mybike.data.repositories.DownhillRepository;
import com.mybike.data.repositories.UsersRepository;
import com.mybike.error.Constants;
import com.mybike.service.factories.DownhillFactory;
import com.mybike.service.models.DownhillServiceModel;
import com.mybike.service.services.DownhillService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DownhillServiceImpl implements DownhillService {

    private final DownhillFactory downhillFactory;
    private final DownhillRepository downhillRepository;
    private final UsersRepository usersRepository;
    private final ModelMapper mapper;

    @Override
    public DownhillServiceModel findDownhillById(Long id) throws Exception {
        return this.downhillRepository.findById(id)
                .map(e -> this.mapper.map(e, DownhillServiceModel.class))
                .orElseThrow(() -> new Exception(Constants.DHID_NOT_FOUND));
    }

    @Override
    public Downhill createDownhill(String username, DownhillServiceModel model) throws Exception {
        if(model.getName().isEmpty() || model.getName() == null) {
            throw new Exception(Constants.DH_INVALID);
        }
        User user = usersRepository.findByUsernameContains(username);
        Downhill downhill = downhillFactory.create(model.getName(), model.getFrame(),
                model.getFork(), model.getTires(), model.getBrakes());
        downhill.setOwner(user);
       downhillRepository.save(downhill);
        return downhill;
    }

    @Override
    public void deleteDownhill(Long id) throws Exception {
        Downhill downhill = this.downhillRepository.findById(id)
                .orElseThrow(() -> new Exception(Constants.DHID_NOT_FOUND));
        this.downhillRepository.delete(downhill);

    }

    @Override
    public DownhillServiceModel editDownhill(Long id, DownhillServiceModel model) throws Exception {
        Downhill downhill = this.downhillRepository.findById(id)
                .orElseThrow(() -> new Exception(Constants.DHID_NOT_FOUND));
        downhill.setName(model.getName());
        downhill.setFrame(model.getFrame());
        downhill.setFork(model.getFork());
        downhill.setTires(model.getTires());
        downhill.setBrakes(model.getBrakes());
        return this.mapper.map(this.downhillRepository.save(downhill), DownhillServiceModel.class);
    }

    @Override
    public Set<Downhill> getAllDownhillBikesByUsername(String username) {
        return this.downhillRepository.findAll().stream()
                .filter(e -> e.getOwner().getUsername().equals(username))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
