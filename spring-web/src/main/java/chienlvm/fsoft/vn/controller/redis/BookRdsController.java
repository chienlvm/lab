package chienlvm.fsoft.vn.controller.redis;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chienlvm.fpt.vn.commom.BeanUtil;
import chienlvm.fpt.vn.commom.ResponseData;
import chienlvm.fpt.vn.entity.BookEntity;
import chienlvm.fsoft.vn.entity.redis.AuthorRds;
import chienlvm.fsoft.vn.entity.redis.BookRds;
import chienlvm.fsoft.vn.entity.redis.TypeBookRds;
import chienlvm.fsoft.vn.repositories.BookRepository;
import chienlvm.fsoft.vn.repositories.redis.BookRdsRepository;

/**
 * Book controller redis
 * 
 * @author chien
 *
 */
@RestController
@RequestMapping(path = "/api/v1/detailbook")
public class BookRdsController {
	@Autowired
	private BookRdsRepository bookRdsRepository;
	@Autowired
	private BookRepository bookRepository;
	@Value("${server.port}")
	private int port;
	@Value("${rootPath}")
	private String rootPath;

	@Autowired
	public BookRdsController(@Value("${rootPath}") String rootPath, @Value("${server.port}") int port) {
		this.rootPath = rootPath;
		this.port = port;
	}

	@GetMapping("{id}")
	public ResponseData test(@PathVariable Integer id) throws UnknownHostException {
		String hostAddress = InetAddress.getLocalHost().getHostAddress();
		String domain = "http://" + hostAddress + ":" + port + "/images/";
		ResponseData responseData = ResponseData.create();
		// check bookId has exists in redis
		Optional<BookRds> retrievedBookRds = bookRdsRepository.findById(id);

		if (retrievedBookRds.isPresent()) {
			BookRds bookrds = BeanUtil.createAndCopy(retrievedBookRds.get(), BookRds.class);
			TypeBookRds typeBook = BeanUtil.createAndCopy(retrievedBookRds.get().getTypeBook(), TypeBookRds.class);
			AuthorRds authorRds = BeanUtil.createAndCopy(retrievedBookRds.get().getAuthorRds(), AuthorRds.class);
			bookrds.setAuthorRds(authorRds);
			bookrds.setTypeBook(typeBook);
			bookrds.setBookImg(domain + bookrds.getBookImg());
			bookrds.setBookThumbImg(domain + bookrds.getBookThumbImg());
			bookrds.setRedisMessage("Get From Redis");
			responseData.setData("detailbook", bookrds);
		} else {
			Optional<BookEntity> retrievedBookFromDB = bookRepository.findById(Long.valueOf(id));
			if (retrievedBookFromDB.isPresent()) {
				BookRds bookrds = BeanUtil.createAndCopy(retrievedBookFromDB.get(), BookRds.class);
				TypeBookRds typeBook = BeanUtil.createAndCopy(retrievedBookFromDB.get().getBookType(),
						TypeBookRds.class);
				AuthorRds authorRds = BeanUtil.createAndCopy(retrievedBookFromDB.get().getBookAuthorEntity(),
						AuthorRds.class);
				bookrds.setAuthorRds(authorRds);
				bookrds.setTypeBook(typeBook);
				bookrds.setRedisMessage("Get From DB");
				bookrds.setBookImg(bookrds.getBookImg());
				bookrds.setBookThumbImg(bookrds.getBookThumbImg());
				bookRdsRepository.save(bookrds);
				// set domain for response data
				bookrds.setBookImg(domain + bookrds.getBookImg());
				bookrds.setBookThumbImg(domain + bookrds.getBookThumbImg());
				responseData.setData("detailbook", bookrds);
			} else {
				responseData.setData("error", "Cuốn sách đã bị xóa");
			}
		}
		return responseData;
	}
}
