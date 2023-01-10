package chienlvm.fsoft.vn.repositioty;


import org.springframework.data.jpa.repository.JpaRepository;

import chienlvm.fsoft.vn.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByUserName(String name);
}
