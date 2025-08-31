package engineer.abf.agegadordeinvestimentos.controller.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import engineer.abf.agegadordeinvestimentos.entity.UserEntity;
import engineer.abf.agegadordeinvestimentos.model.UserModel;
import lombok.NonNull;


public record UserDto(
        Long userid,
        String username,
        String email,
        String password) {

    public UserDto(@NonNull UserModel userModel) {
        this(
                userModel.userid(),
                userModel.username(),
                userModel.email(),
                userModel.password()
        );
    }

    public UserDto(UserEntity userEntity) {
        this(
                userEntity.getUserId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getPassword()
        );
    }

    @Override
    public @NonNull String toString() {
        try {
            final var objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to JSON", e);
        }
    }
}
