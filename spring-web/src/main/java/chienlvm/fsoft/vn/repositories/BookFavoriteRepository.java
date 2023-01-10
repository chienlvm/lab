package chienlvm.fsoft.vn.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import chienlvm.fsoft.vn.entity.FavoriteBook;

@Repository
public interface BookFavoriteRepository extends JpaRepository<FavoriteBook, Long> {
	int countByUserIdAndBookId(Long userId, Long bookId);
	
	@Query(value = "SELECT book_id FROM lab.tb_scrap where user_id =:useId ", nativeQuery = true)
	public List<Long> getListBookIdByUserId(Long useId);

	@Modifying
	@Query(value = "DELETE FROM tb_scrap WHERE user_id = :userId and book_id=:bookId ", nativeQuery = true)
	void removeByUserIdAndBookId(@Param("userId") Long userId, @Param("bookId") Long bookId);
	

	@Modifying
	@Query(value = "INSERT INTO tb_scrap (user_id, book_id, scrap_dt, crt_dt )"
			+ "VALUES (:userId, :bookId, :scrapDt, :crtDt )", nativeQuery = true)
	public void insertFaviroteBook(@Param("userId") Long userId, @Param("bookId") Long bookId,
			@Param("scrapDt") Date scrapDt, @Param("crtDt") Date crtDt);

}
