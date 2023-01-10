package chienlvm.fsoft.vn.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import chienlvm.fpt.vn.commom.ResponseData;
import chienlvm.fpt.vn.entity.BookType;
import chienlvm.fsoft.vn.dto.request.TypeBookRequest;
import chienlvm.fsoft.vn.repositioty.TypeBookRepository;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/v1/admin")
public class TypeBookController {

	@Autowired
	private TypeBookRepository typeBookRepository;

	@RequestMapping(value = "/getListTypeBook", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseData getAllTypeBook() {
		ResponseData response = ResponseData.create();
		List<BookType> bookInfo = typeBookRepository.findAllByOrderByTypeBookIdDesc();
		bookInfo.forEach(item -> {
			if (item.getBookEntity().size() > 0) {
				item.setExistsBook(true);
			}
		});
		response.setData("TypeBook", bookInfo);
		return response;
	}

	@RequestMapping(value = "/typebook/add", //
			method = RequestMethod.POST, //
			consumes = "application/json", produces = "application/json")
	public ResponseData addNewTypeBook(@RequestBody TypeBookRequest typeBookRequest) {
		ResponseData response = ResponseData.create();
		String typeBookName = typeBookRequest.getTypeBookName();
		BookType bookType = new BookType();
		bookType.setTypeBookName(typeBookName);
		try {
			typeBookRepository.save(bookType);
			response.setData("data", "Đã thêm mới loại sách");
		} catch (Exception e) {
			// TODO: handle exception
			response.setData("error", "Đã có lỗi xảy ra");
		}
		return response;
	}

	@RequestMapping(value = "/typebook/update", //
			method = RequestMethod.POST, //
			consumes = "application/json", produces = "application/json")
	public ResponseData updateNewAthor(@RequestBody TypeBookRequest typeBookRequest) {
		ResponseData response = ResponseData.create();
		Long typeBookId = typeBookRequest.getTypeBookId();
		String typeBookName = typeBookRequest.getTypeBookName();
		BookType bookType = new BookType();
		Optional<BookType> lstTypeBook = typeBookRepository.findById(typeBookId);
		try {
			if (lstTypeBook.isPresent()) {
				bookType.setTypeBookId(typeBookId);
				bookType.setTypeBookName(typeBookName);
				bookType.setDelF(Long.valueOf(0));
				bookType.setUpdtDt(new Date());
				typeBookRepository.save(bookType);
				response.setData("data", "Update thành công");
			} else {
				response.setData("error", "Tác giả không tồn tại");
			}
		} catch (Exception e) {
			// TODO: handle exception
			response.setData("error", "Đã có lỗi xảy ra");
		}
		return response;
	}

	@RequestMapping(value = "/typebook/remove", //
			method = RequestMethod.POST, //
			consumes = "application/json", produces = "application/json")
	public ResponseData removeNewAthor(@RequestBody TypeBookRequest typeBookRequest) {
		ResponseData response = ResponseData.create();
		// step 1: find author exists book
		// if exists then throw error
		// else delete
		Long typeBookId = typeBookRequest.getTypeBookId();
		try {
			Optional<BookType> lstTypeBook = typeBookRepository.findById(typeBookId);
			if (lstTypeBook.isPresent()) {
				// check book
				if (lstTypeBook.get().getBookEntity().size() > 0) {
					response.setData("error", "Hãy xóa hết sách trước khi xóa tác giả");
				} else {
					typeBookRepository.deleteById(typeBookId);
					response.setData("data", "Đã xóa");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			response.setData("error", "Đã có lỗi xảy ra");
		}
		return response;
	}
}
