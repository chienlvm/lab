package chienlvm.fsoft.vn.repositioty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chienlvm.fpt.vn.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

	List<BookEntity> findAllByOrderByPublishYearDesc();

}
