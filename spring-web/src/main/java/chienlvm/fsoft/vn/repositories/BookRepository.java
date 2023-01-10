package chienlvm.fsoft.vn.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chienlvm.fpt.vn.entity.BookEntity;

/**
 * Book controller
 * @author chien
 *
 */
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long>{

}