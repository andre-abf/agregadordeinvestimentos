package engineer.abf.agegadordeinvestimentos.service;

import engineer.abf.agegadordeinvestimentos.controller.dto.UserDto;
import engineer.abf.agegadordeinvestimentos.entity.UserEntity;
import engineer.abf.agegadordeinvestimentos.model.UserFilter;
import engineer.abf.agegadordeinvestimentos.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserModel createUser(UserModel userModel);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteById(Long userId);
    List<UserDto> listarUser(int page, int size);
    Page<UserEntity> find(UserFilter userFilter, Pageable pageable);
}
