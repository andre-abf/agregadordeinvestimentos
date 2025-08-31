package engineer.abf.agegadordeinvestimentos.service;

import engineer.abf.agegadordeinvestimentos.controller.dto.UserDto;
import engineer.abf.agegadordeinvestimentos.entity.UserEntity;
import engineer.abf.agegadordeinvestimentos.model.UserFilter;
import engineer.abf.agegadordeinvestimentos.model.UserModel;
import engineer.abf.agegadordeinvestimentos.model.adapter.UserAdapter;
import engineer.abf.agegadordeinvestimentos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private  final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel createUser (UserModel userModel) {

        var userEntity = UserAdapter.adptModelToEntity(userModel);
        var userEntitySaved = userRepository.save(userEntity);
        return UserAdapter.adptEntityToModel(userEntitySaved);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        var resp = userRepository.findById(id);
        var userEntity = resp.get();
        userEntity.setUsername(userDto.username());
        userEntity.setEmail(userDto.email());
        userRepository.save(userEntity);
        var model = UserAdapter.adptEntityToModel(userEntity);
        return UserAdapter.adptModelToDto(model);
    }

    @Override
    public List<UserDto> listarUser (int page, int size){
        //var pageable = PageRequest.of(page, size);
        //return userRepository.findAll(pageable).stream().toList();

        var pageable = PageRequest.of(page, size);

        // Converte a lista de UserEntity para UserDto
        return userRepository.findAll(pageable)
                .stream()
                .map(UserAdapter::adptEntityToModel)  // Converte para UserModel
                .map(UserAdapter::adptModelToDto)    // Converte para UserDto
                .toList();


    }

    @Override
    public Page<UserEntity> find(UserFilter userFilter, Pageable pageable) {
        final var specification = userFilter.getSpecification();
        //final var page = userRepository.findAll(specification, pageable);
        return  userRepository.findAll(specification, pageable);
    }

    @Override
    public void deleteById(Long userId) {
        var userExists = userRepository.existsById(userId);

        if (userExists) {
            userRepository.deleteById(userId);
        }
    }
}
