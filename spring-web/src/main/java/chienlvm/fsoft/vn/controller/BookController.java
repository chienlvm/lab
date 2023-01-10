package chienlvm.fsoft.vn.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import chienlvm.fpt.vn.commom.ResponseData;
import chienlvm.fpt.vn.entity.BookEntity;
import chienlvm.fsoft.vn.dto.request.UserFavoriteBook;
import chienlvm.fsoft.vn.entity.FavoriteBook;
import chienlvm.fsoft.vn.entity.UserEntity;
import chienlvm.fsoft.vn.repositories.BookFavoriteRepository;
import chienlvm.fsoft.vn.repositories.BookRepository;
import chienlvm.fsoft.vn.service.BookService;

/**
 * Book controller
 * 
 * @author chien
 *
 */

@RestController
@RequestMapping(path = "/api/v1/")
public class BookController {
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookFavoriteRepository bookFavoriteRepository;

	
	@Autowired
	private BookService bookService;
	
	@Value("${server.port}")
	private int port;
	@Value("${rootPath}")
	private String rootPath;

	private final String URL_DETAIL_BOOK = "/detail/";

	@Autowired
	public BookController(@Value("${rootPath}") String rootPath, @Value("${server.port}") int port) {
		this.rootPath = rootPath;
		this.port = port;
	}

	@RequestMapping(value = "/getListFavorite", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseData getListFavorite() throws UnknownHostException {
		List<BookEntity> lstBook = new ArrayList<>();
		String hostAddress = InetAddress.getLocalHost().getHostAddress();
		String domain = "http://" + hostAddress + ":" + port + "/images/";
		ResponseData responseData = ResponseData.create();
		UserEntity userInfor = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Long> listBookId = bookService.getListBookIdByUserId(userInfor.getUserId());

		lstBook = bookRepository.findAllById(listBookId);
		lstBook.forEach(item -> {
			item.setLinkPC(URL_DETAIL_BOOK + item.getBookId());
			item.setBookImg(domain + item.getBookImg());
			item.setBookThumbImg(domain + item.getBookThumbImg());
		});
		responseData.setData("data", lstBook);
		return responseData;
	}

	@RequestMapping(value = "/favorite/add", //
			method = RequestMethod.POST, //
			consumes = "application/json", produces = "application/json")
	public ResponseData addFavorite(@RequestBody UserFavoriteBook userFavoriteBook) {
		ResponseData responseData = ResponseData.create();
		String bookId = userFavoriteBook.getBookId();
		if ("".equals(bookId)) {
			responseData.setData("error", "Xin lỗi <br /> Chúng tôi rất đông đúc");
			return responseData;
		}
		try {
			UserEntity userInfor = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (userInfor != null) {
				Long userId = userInfor.getUserId();
				int countBookFavorite = bookFavoriteRepository.countByUserIdAndBookId(Long.valueOf(userId),
						Long.valueOf(bookId));
				if (countBookFavorite > 0) {
					bookService.removeByUserIdAndBookId(Long.valueOf(userId), Long.valueOf(bookId));
				}
				FavoriteBook bookFavorite = new FavoriteBook();
				bookFavorite.setUserId(Long.valueOf(userId));
				bookFavorite.setBookId(Long.valueOf(bookId));
				bookService.insertFaviroteBook(bookFavorite.getUserId(), bookFavorite.getBookId(),
						new Date(), new Date());
				responseData.setData("data", "Đã thích");
			} else {
				responseData.setData("error", "Lỗi xác thực");
			}
		} catch (Exception e) {
			responseData.setData("error", "Lỗi xác thực");
		}
		return responseData;
	}

	@RequestMapping(value = "/favorite/remove", //
			method = RequestMethod.POST, //
			consumes = "application/json", produces = "application/json")
	public ResponseData removeFavorite(@RequestBody UserFavoriteBook userFavoriteBook) {
		ResponseData responseData = ResponseData.create();
		String bookId = userFavoriteBook.getBookId();
		if ("".equals(bookId)) {
			responseData.setData("error", "Xin lỗi <br /> Chúng tôi rất đông đúc");
			return responseData;
		}
		try {
			UserEntity userInfor = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (userInfor != null) {
				Long userId = userInfor.getUserId();
				bookService.removeByUserIdAndBookId(Long.valueOf(userId), Long.valueOf(bookId));
			} else {
				responseData.setData("error", "Lỗi xác thực");
			}
		} catch (Exception e) {
			responseData.setData("error", "Xin lỗi <br /> Chúng tôi rất đông đúc");
		}
		responseData.setData("data", "Đã xóa");
		return responseData;
	}
}
