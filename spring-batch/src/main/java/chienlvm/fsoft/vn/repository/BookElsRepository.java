package chienlvm.fsoft.vn.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import chienlvm.fsoft.vn.entity.BookEls;

/**
 * @author chienlvm
 *
 */
@Repository
public interface BookElsRepository extends ElasticsearchRepository<BookEls, String> {
}
