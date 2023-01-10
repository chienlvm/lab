package chienlvm.fsoft.vn.repositories.redis;


import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

import chienlvm.fsoft.vn.entity.redis.BookRds;

@EnableRedisRepositories
public interface BookRdsRepository  extends CrudRepository<BookRds, Integer> {
	
}
