package chienlvm.fsoft.vn.repositioty;


import org.springframework.data.jpa.repository.JpaRepository;

import chienlvm.fsoft.vn.entity.FavoriteBook;

public interface FavoriteBookDAO extends JpaRepository<FavoriteBook, Long> {
	int countByUserIdAndBookId(Long useId, Long bookId);

	long deleteByUserIdAndBookId(Long useId, Long bookId);
}
