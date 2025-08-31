package engineer.abf.agegadordeinvestimentos.model;

import engineer.abf.agegadordeinvestimentos.entity.UserEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFilter {

    private Long userId;
    private String username;
    private String email;
    private String password;

    public Specification<UserEntity> getSpecification(){
        final var entity = getEntity();
        return ((root, query, criteriaBuilder) -> {
            var predicates = criteriaBuilder.conjunction();

            if (entity.getUserId() != null) {
                predicates = criteriaBuilder.and(predicates,
                        criteriaBuilder.equal(root.get("userId"),
                                entity.getUserId()));
            }
            if (entity.getUsername() != null) {
                predicates = criteriaBuilder.and(predicates,
                        criteriaBuilder.equal(root.get("username"),
                                entity.getUsername()));
            }
            if (entity.getEmail() != null) {
                predicates = criteriaBuilder.and(predicates,
                        criteriaBuilder.equal(root.get("email"),
                        entity.getEmail()));
            }
            if (entity.getPassword() != null) {
                predicates = criteriaBuilder.and(predicates,
                        criteriaBuilder.equal(root.get("password"),
                                entity.getPassword()));
            }

            return predicates;
        });
    }

    public UserEntity getEntity() {
        return UserEntity
                .builder()
                .userId(userId == null ? null :
                        userId)
                .username(username)
                .email(email)
                .password(password)
                .build();
    }
}
