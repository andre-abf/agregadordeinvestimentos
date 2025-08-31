package engineer.abf.agegadordeinvestimentos.model.adapter;

import engineer.abf.agegadordeinvestimentos.controller.dto.UserDto;
import engineer.abf.agegadordeinvestimentos.entity.UserEntity;
import engineer.abf.agegadordeinvestimentos.model.UserModel;

import java.time.Instant;

public class UserAdapter {

    private UserAdapter() {

    }

    public static UserEntity adptModelToEntity(UserModel userModel) {
        final var entity = new  UserEntity();

        entity.setUserId(userModel.userid());
        entity.setUsername(userModel.username());
        entity.setEmail(userModel.email());
        entity.setPassword(userModel.password());
        entity.setCreationTimestamp(Instant.now());
        entity.setUpdateTimestamp(null);

        return entity;
    }

    public static UserModel adptEntityToModel(UserEntity userEntity) {
        return new UserModel(
                userEntity.getUserId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getPassword()
        );
    }

    public static UserDto adptModelToDto(UserModel userModel) {
        return new UserDto(
                userModel.userid(),
                userModel.username(),
                userModel.email(),
                userModel.password()
        );
    }

    public static UserModel adptDtoToModel(UserDto userDto) {
        return new UserModel(
                userDto.userid(),
                userDto.username(),
                userDto.email(),
                userDto.password()
        );
    }



}
