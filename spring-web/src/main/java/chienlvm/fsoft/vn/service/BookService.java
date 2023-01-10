package chienlvm.fsoft.vn.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chienlvm.fsoft.vn.repositories.BookFavoriteRepository;

@Service
public class BookService {
	@Autowired
	private static BookFavoriteRepository bookFavoriteRepository;

	@Autowired
	public BookService(BookFavoriteRepository bookFavoriteRepository) {
		BookService.bookFavoriteRepository = bookFavoriteRepository;
	}

	@Transactional
	public void removeByUserIdAndBookId(Long userId, Long bookId) {
		bookFavoriteRepository.removeByUserIdAndBookId(userId, bookId);
	}

	@Transactional
	public void insertFaviroteBook(Long userId, Long bookId, Date scrapDt, Date crtDt) {
		bookFavoriteRepository.insertFaviroteBook(userId, bookId, scrapDt, crtDt);
	}

	public List<Long> getListBookIdByUserId(Long userId) {
		return bookFavoriteRepository.getListBookIdByUserId(userId);
	}

}
