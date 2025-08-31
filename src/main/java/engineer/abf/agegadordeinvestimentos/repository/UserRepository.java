package engineer.abf.agegadordeinvestimentos.repository;

import engineer.abf.agegadordeinvestimentos.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository  extends JpaRepository<UserEntity, Long>,
        JpaSpecificationExecutor<UserEntity> {


}
