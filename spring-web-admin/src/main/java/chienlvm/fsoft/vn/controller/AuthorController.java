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
import chienlvm.fpt.vn.entity.AuthorEntity;
import chienlvm.fsoft.vn.dto.request.AuthorRequest;
import chienlvm.fsoft.vn.repositioty.AuthorRepository;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/v1/admin")
public class AuthorController {

	@Autowired
	private AuthorRepository authorRepository;

	@RequestMapping(value = "/getListAuthor", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseData getAllTypeBook() {
		ResponseData response = ResponseData.create();
		List<AuthorEntity> getListAuthor = authorRepository.findAllByOrderByAuthorIdDesc();
		getListAuthor.forEach(item -> {
			if (item.getBookEntity().size() > 0) {
				item.setExistsBook(true);
			}
		});
		response.setData("ListAuthor", getListAuthor);
		return response;
	}

	@RequestMapping(value = "/author/add", //
			method = RequestMethod.POST, //
			consumes = "application/json", produces = "application/json")
	public ResponseData addNewAthor(@RequestBody AuthorRequest authorRequest) {
		ResponseData response = ResponseData.create();
		String authorName = authorRequest.getAuthorName();
		String authorDescribe = authorRequest.getAuthorDescribe();
		Date authorDateOfBirth = authorRequest.getDateOfBirth();
		AuthorEntity author = new AuthorEntity();
		author.setAuthorDescribe(authorDescribe);
		author.setAuthorName(authorName);
		author.setDateOfBirth(authorDateOfBirth);
		try {
			authorRepository.save(author);
			response.setData("data", "Đã thêm mới tác giả");
		} catch (Exception e) {
			// TODO: handle exception
			response.setData("error", "Đã có lỗi xảy ra");
		}
		return response;
	}

	@RequestMapping(value = "/author/update", //
			method = RequestMethod.POST, //
			consumes = "application/json", produces = "application/json")
	public ResponseData updateNewAthor(@RequestBody AuthorRequest authorRequest) {
		ResponseData response = ResponseData.create();
		Long authorId = authorRequest.getAuthorId();
		String authorName = authorRequest.getAuthorName();
		String authorDescribe = authorRequest.getAuthorDescribe();
		Date authorDateOfBirth = authorRequest.getDateOfBirth();
		boolean isExistsAuthor = authorRepository.existsById(authorId);
		AuthorEntity author = new AuthorEntity();
		try {
			if (isExistsAuthor) {
				author.setAuthorId(authorId);
				author.setAuthorDescribe(authorDescribe);
				author.setAuthorName(authorName);
				author.setDelF(Long.valueOf(0));
				author.setDateOfBirth(authorDateOfBirth);
				author.setUpdtDt(new Date());
				author.setUpDtUserId(Long.valueOf(11111));
				authorRepository.save(author);
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

	@RequestMapping(value = "/author/delete", //
			method = RequestMethod.POST, //
			consumes = "application/json", produces = "application/json")
	public ResponseData removeNewAthor(@RequestBody AuthorRequest authorRequest) {
		ResponseData response = ResponseData.create();
		// step 1: find author exists book
		// if exists then throw error
		// else delete
		Long authorId = authorRequest.getAuthorId();
		try {
			Optional<AuthorEntity> lstAuthor = authorRepository.findById(authorRequest.getAuthorId());
			if (lstAuthor.isPresent()) {
				// check book
				if (lstAuthor.get().getBookEntity().size() > 0) {
					response.setData("error", "Hãy xóa hết sách trước khi xóa tác giả");
				} else {
					authorRepository.deleteById(authorId);
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
