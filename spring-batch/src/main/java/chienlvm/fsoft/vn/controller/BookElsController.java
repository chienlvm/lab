package chienlvm.fsoft.vn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import chienlvm.fsoft.vn.entity.AuthorEls;
import chienlvm.fsoft.vn.entity.BookEls;
import chienlvm.fsoft.vn.entity.TypeBookEls;
import chienlvm.fsoft.vn.service.ProductSearchService;
import chienlvm.fsoft.vn.utils.ResponseData;

@RestController
public class BookElsController {
	@Autowired
	private ProductSearchService productSearchService;

	@GetMapping("/products")
	@ResponseBody
	public List<BookEls> fetchByNameOrDesc(@RequestParam(required = false) String query) {
		List<BookEls> products = productSearchService.findBybookName(query);
		return products;
	}
	
	@GetMapping("/getLstTypeBookMostBook")
	@ResponseBody
	public ResponseData getLstTypeBookMostBook() {
		List<TypeBookEls> lstBook = productSearchService.searchAgrreagetionTypeBook();
		ResponseData responseData = ResponseData.create();
		responseData.setData("data", lstBook);
		return responseData;
	}
	@GetMapping("/getLstAuthorMostBook")
	@ResponseBody
	public ResponseData getLstAuthorMostBook() {
		List<AuthorEls> lstBook = productSearchService.searchAgrreagetionAuthor();
		ResponseData responseData = ResponseData.create();
		responseData.setData("data", lstBook);
		return responseData;
	}
}
