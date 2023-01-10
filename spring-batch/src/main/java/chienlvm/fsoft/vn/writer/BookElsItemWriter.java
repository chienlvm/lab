package chienlvm.fsoft.vn.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chienlvm.fsoft.vn.entity.BookEls;
import chienlvm.fsoft.vn.repository.BookElsRepository;
/**
 * 
 * @author chien
 *
 */

@Component
public class BookElsItemWriter implements ItemWriter<BookEls> {
	private static final Logger log = LoggerFactory.getLogger(BookElsItemWriter.class);
	private BookElsRepository bBookElsRepository;

	@Autowired
	public BookElsItemWriter(BookElsRepository bBookElsRepository) {
		this.bBookElsRepository = bBookElsRepository;
	}
	@Override
	public void write(List<? extends BookEls> items) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < items.size(); i++) {
			log.info("INSERT DATA: " + items.get(i));
		}
		this.bBookElsRepository.deleteAll();
		this.bBookElsRepository.saveAll(items);
	}
}
