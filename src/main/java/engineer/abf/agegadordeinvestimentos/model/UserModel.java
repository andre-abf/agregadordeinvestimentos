package engineer.abf.agegadordeinvestimentos.model;

import engineer.abf.agegadordeinvestimentos.entity.UserEntity;

public record UserModel(
        Long userid,
        String username,
        String email,
        String password
        ) {

    public UserModel(UserEntity userEntity) {
        this(
                userEntity.getUserId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getPassword()
        );
    }
}
