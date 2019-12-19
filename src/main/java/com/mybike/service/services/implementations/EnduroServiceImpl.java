package com.mybike.service.services.implementations;

import com.mybike.data.entities.Enduro;
import com.mybike.data.entities.User;
import com.mybike.data.repositories.EnduroRepository;
import com.mybike.data.repositories.UsersRepository;
import com.mybike.error.Constants;
import com.mybike.service.factories.EnduroFactory;
import com.mybike.service.models.EnduroServiceModel;
import com.mybike.service.services.EnduroService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EnduroServiceImpl implements EnduroService {

    private final EnduroFactory enduroFactory;
    private final EnduroRepository enduroRepository;
    private final UsersRepository usersRepository;
    private final ModelMapper mapper;

    @Override
    public EnduroServiceModel findEnduroById(Long id) throws Exception {
        return this.enduroRepository.findById(id)
                .map(e -> this.mapper.map(e, EnduroServiceModel.class))
                .orElseThrow(() -> new Exception(Constants.ENDUROID_NOT_FOUND));
    }

    @Override
    public Enduro createEnduro(String username, EnduroServiceModel model) throws Exception {
        if(model.getName().isEmpty() || model.getName() == null) {
            throw new Exception(Constants.ENDURONAME_INVALID);
        }
        User user = usersRepository.findByUsernameContains(username);
        Enduro enduro = enduroFactory.create(model.getName(), model.getFrame(),
                model.getFork(), model.getTires(), model.getBrakes());
        enduro.setOwner(user);
        enduroRepository.save(enduro);
        return enduro;
    }

    @Override
    public void deleteEnduro(Long id) throws Exception {
        Enduro enduro = this.enduroRepository.findById(id)
                .orElseThrow(() -> new Exception(Constants.ENDUROID_NOT_FOUND));
        this.enduroRepository.delete(enduro);
    }

    @Override
    public EnduroServiceModel editEnduro(Long id, EnduroServiceModel model) throws Exception {
        Enduro enduro = this.enduroRepository.findById(id)
                .orElseThrow(() -> new Exception(Constants.ENDUROID_NOT_FOUND));
        enduro.setName(model.getName());
        enduro.setFrame(model.getFrame());
        enduro.setFork(model.getFork());
        enduro.setTires(model.getTires());
        enduro.setBrakes(model.getBrakes());
        return this.mapper.map(this.enduroRepository.save(enduro), EnduroServiceModel.class);
    }

    @Override
    public Set<Enduro> getAllEnduroBikesByUsername(String username) {
        return this.enduroRepository.findAll().stream()
                .filter(e -> e.getOwner().getUsername().equals(username))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    //@Override
    //public List<EnduroServiceModel> getAllEnduroBikes() {
    //    return this.enduroRepository.findAll()
    //            .stream()
    //            .map(e -> this.mapper.map(e, EnduroServiceModel.class))
    //            .collect(Collectors.toList());
    //}
}
