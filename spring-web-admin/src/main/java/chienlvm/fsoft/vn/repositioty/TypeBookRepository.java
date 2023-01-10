package chienlvm.fsoft.vn.repositioty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import chienlvm.fpt.vn.entity.BookType;

public interface TypeBookRepository extends JpaRepository<BookType, Long> {

	List<BookType> findAllByOrderByTypeBookIdDesc();
}
