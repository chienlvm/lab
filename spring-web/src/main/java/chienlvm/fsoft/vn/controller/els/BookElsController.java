package chienlvm.fsoft.vn.controller.els;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import chienlvm.fpt.vn.commom.ResponseData;
import chienlvm.fsoft.vn.dto.request.TopPapeRequest;
import chienlvm.fsoft.vn.entity.UserEntity;
import chienlvm.fsoft.vn.entity.els.AuthorEls;
import chienlvm.fsoft.vn.entity.els.BookEls;
import chienlvm.fsoft.vn.entity.els.TypeBookEls;
import chienlvm.fsoft.vn.repositories.BookFavoriteRepository;
import chienlvm.fsoft.vn.service.els.BookSearchElsService;

@RestController
@RequestMapping(path = "/api/v1/")
public class BookElsController {
	private final String RECOMMEND_AUTHOR = "recommendAuthor";
	private final String RECOMMEND_TYPEBOOK = "recommendTypeBook";
	private final String RECOMMEND_TOP = "recommendTop";

	private final int RECOMMEND_AUTHOR_SIZE = 10;
	private final int RECOMMEND_TYPEBOOK_SIZE = 10;
	private final int RECOMMEND_TOP_SIZE = 10;
	private final String URL_DETAIL_BOOK = "/detail/";
	private final String URL_TYPE_BOOK = "/type/";
	private final String URL_AUTHOR = "/author/";
	private Long userId = null;
	private List<Long> lstBookIdFavorite = null;
	
	@Value("${server.port}")
	private int port;
	@Value("${rootPath}")
	private String rootPath;
	
	
	public void setUserId() {
		try {
			UserEntity userInfor = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			this.userId = userInfor.getUserId();
		} catch (Exception e) {
			userId = null;
			// TODO: handle exception
		}
	}

	@Autowired
	public BookElsController(@Value("${rootPath}") String rootPath, @Value("${server.port}") int port) {
		this.rootPath = rootPath;
		this.port = port;
	}
	@Autowired
	private BookSearchElsService bookSearchElsService;

	@Autowired
	private BookFavoriteRepository bookFavoriteRepository;
	
	
	@RequestMapping(value = "/top", //
			method = RequestMethod.POST, //
			consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseData getRecommendTop(@RequestBody TopPapeRequest topPapeRequest) throws UnknownHostException {
		ResponseData responseData = ResponseData.create();
		List<String> categorys = topPapeRequest.getCategory();
		this.setUserId();
		int recommendAuthor = topPapeRequest.getRecommendAuthor();
		int recommendTypeBook = topPapeRequest.getRecommendTypeBook();
		int recommendTop = topPapeRequest.getRecommendTop();
		if (userId != null) {
			lstBookIdFavorite = new ArrayList<>();
			lstBookIdFavorite = bookFavoriteRepository.getListBookIdByUserId(userId);
		}
		categorys.forEach(catetory -> {
			// get newerbook
			if (RECOMMEND_AUTHOR.equals(catetory)) {
				List<AuthorEls> lstAuthor = bookSearchElsService
						.searchAgrreagetionAuthor(recommendAuthor > 0 ? recommendAuthor : RECOMMEND_AUTHOR_SIZE);
				lstAuthor.forEach(item -> {
					item.setLinkPC(URL_AUTHOR + item.getAuthorId());
				});
				responseData.setData("recommendAuthor", lstAuthor);
			}
			if (RECOMMEND_TOP.equals(catetory)) {
				List<BookEls> lstBook = bookSearchElsService
						.getNewerBook(recommendTop > 0 ? recommendTop : RECOMMEND_TOP_SIZE);
				lstBook.forEach(item -> {
					item.setLinkPC(URL_DETAIL_BOOK + item.getBookId());
					item.setBookImg(this.rootPath + item.getBookImg());
					item.setBookThumbImg(this.rootPath + item.getBookThumbImg());
					if (userId != null) {
						lstBookIdFavorite.forEach(bookId -> {
							if (bookId.equals(item.getBookId())) {
								item.setFavorite(true);
							}
						});
					} else {
						item.setFavorite(false);
					}
				});
				responseData.setData("recommendTop", lstBook);
			}
			if (RECOMMEND_TYPEBOOK.equals(catetory)) {
				List<TypeBookEls> lstTypeBook = bookSearchElsService.searchAgrreagetionTypeBook(
						recommendTypeBook > 0 ? recommendTypeBook : RECOMMEND_TYPEBOOK_SIZE);
				lstTypeBook.forEach(item -> {
					item.setLinkPC(URL_TYPE_BOOK + item.getTypeBookId());
				});
				responseData.setData("recommendTypeBook", lstTypeBook);
			}
		});
		return responseData;
	}

	@GetMapping("/search")
	@ResponseBody
	public ResponseData fetchByNameOrDesc(@RequestParam(required = false) String query) throws UnknownHostException {
		ResponseData responseData = ResponseData.create();
		List<BookEls> resultSearch = new ArrayList<>();
		if ("".equals(query)) {
			resultSearch = bookSearchElsService.getNewerBook(50);
		} else {
			resultSearch = bookSearchElsService.searchBookWithHighlight(query);
		}
		resultSearch.forEach(item -> {
			item.setLinkPC(URL_DETAIL_BOOK + item.getBookId());
			item.setBookImg(this.rootPath + item.getBookImg());
			item.setBookThumbImg(this.rootPath + item.getBookThumbImg());
		});
		responseData.setData("data", resultSearch);
		return responseData;
	}

}
