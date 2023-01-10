package chienlvm.fsoft.vn.repositories.els;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import chienlvm.fsoft.vn.entity.els.BookEls;


/**
 * @author Chien
 *
 */
@Repository
public interface BookElsRepository extends ElasticsearchRepository<BookEls, String> {
    List<BookEls> findByBookName(String name);
//    
//    List<BookEls> findByNameContaining(String name);
// 
//    List<BookEls> findByManufacturerAndCategory(String manufacturer,String category);

    List<BookEls> findAllByBookIdOrderByBookIdDesc();
}
