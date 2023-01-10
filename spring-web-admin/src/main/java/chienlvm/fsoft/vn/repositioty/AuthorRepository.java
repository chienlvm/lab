package chienlvm.fsoft.vn.repositioty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import chienlvm.fpt.vn.entity.AuthorEntity;


public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

	List<AuthorEntity> findAllByOrderByAuthorIdDesc();

}
