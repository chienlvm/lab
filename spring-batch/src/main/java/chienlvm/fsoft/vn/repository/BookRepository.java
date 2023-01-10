package chienlvm.fsoft.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chienlvm.fpt.vn.entity.BookEntity;


/**
 * @author chienlvm
 *
 */
@Repository
public interface BookRepository extends JpaRepository<BookEntity, String> {
//    
//    List<BookEls> findByNameContaining(String name);
// 
//    List<BookEls> findByManufacturerAndCategory(String manufacturer,String category);
}
