package chienlvm.fsoft.vn.controller;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import chienlvm.fpt.vn.commom.ResponseData;
import chienlvm.fpt.vn.entity.BookEntity;
import chienlvm.fsoft.vn.dto.request.BookDtoRequest;
import chienlvm.fsoft.vn.entity.UserEntity;
import chienlvm.fsoft.vn.repositioty.BookRepository;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/v1/admin")
public class BookController {
	private static String BOOK_IMAGE_NAME = "bookImg";
	private static String BOOK_IMAGE_THUMB = "bookImgThumb";
	@Value("${token.header_string}")
	private String HEADER_STRING;
	@Value("${server.port}")
	private int port;
	@Value("${rootPath}")
	private String rootPath;
	@Autowired
	public BookController(@Value("${rootPath}") String rootPath, @Value("${token.header_string}") String headerString,
			@Value("${server.port}") int port) {
		this.rootPath = rootPath;
		this.HEADER_STRING = headerString;
		this.port = port;
	}

	@Autowired
	private BookRepository bookRepository;

	@RequestMapping(value = "/listBook", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseData getBook(HttpServletRequest request) throws UnknownHostException {
		ResponseData response = ResponseData.create();
		List<BookEntity> bookInfo = bookRepository.findAllByOrderByPublishYearDesc().stream().filter(i -> i.getDelF() != 1).collect(Collectors.toList());
		// convert public image
		// convert time
		// path public
		String publicPath = "http://localhost:" + port + "/images/";
		bookInfo.forEach(book -> {
			book.setBookImg(publicPath + book.getBookImg());
			book.setBookThumbImg(publicPath + book.getBookThumbImg());
		});
		response.setData("book", bookInfo);
		return response;
	}

	@RequestMapping(value = "/createBook", //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	public ResponseData createBook(@Valid @ModelAttribute BookDtoRequest bookRequest, HttpServletRequest request) {
		ResponseData responseData = ResponseData.create();
		UserEntity userInfor = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		BookEntity book = new BookEntity();
		// set root folder upload file
		String uploadRootPath = rootPath;
		Path rootLocation = Paths.get(uploadRootPath);
		File uploadRootDir = new File(uploadRootPath);
		// create folder if not exists
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		MultipartRequest multipartRequest = (MultipartRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		MultipartFile mfile = null;
		String fileNameBookImg = "default.png";
		String fileNameBookImgThumb = "default.png";
		if (fileMap.size() > 0) {
			for (Iterator<MultipartFile> iter = fileMap.values().iterator(); iter.hasNext();) {
				mfile = (MultipartFile) iter.next();
				String nameInput = mfile.getName();
				String originalFilename = mfile.getOriginalFilename();
				String extension = FilenameUtils.getExtension(originalFilename);
				String fileName = UUID.randomUUID().toString().replace("-", "") + "." + extension;
				if (BOOK_IMAGE_NAME.equals(nameInput)) {
					fileNameBookImg = fileName;
				} else if (BOOK_IMAGE_THUMB.equals(nameInput)) {
					fileNameBookImgThumb = fileName;
				}
				try {
					Files.copy(mfile.getInputStream(), rootLocation.resolve(fileName),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		book.setBookImg(fileNameBookImg);
		book.setBookThumbImg(fileNameBookImgThumb);
		book.setBookName(bookRequest.getBookName());
		book.setAuthorId(bookRequest.getAuthorId());
		book.setBookDescribe(bookRequest.getBookDescribe());
		book.setDelF(bookRequest.getDelF());
		book.setTypeBookId(bookRequest.getTypeBookId());
		book.setPublishYear(bookRequest.getPublishYear());
		book.setUpDtUserId(userInfor.getUserId());

		try {
			bookRepository.save(book);
			responseData.setData("data", "Tạo mới sách thành công");
		} catch (Exception e) {
			responseData.setData("error", "Đã có lỗi");
			return responseData;
		}
		// return ResponseEntity.ok("OK");
		return responseData;
	}

	/**
	 * Update book
	 * 
	 * @param bookRequest
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateBook", //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	public ResponseData updateBook(@Valid @ModelAttribute BookDtoRequest bookRequest, HttpServletRequest request) {
		ResponseData responseData = ResponseData.create();
		UserEntity userInfor = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// get book info
		Optional<BookEntity> bookInfo = bookRepository.findById(bookRequest.getBookId());
		if (!bookInfo.isPresent()) {
			responseData.setData("error", "Sách này đã bị xóa");
			return responseData;
		}
		BookEntity book = new BookEntity();
		// set root folder upload file
		String uploadRootPath = rootPath;
		Path rootLocation = Paths.get(uploadRootPath);
		File uploadRootDir = new File(uploadRootPath);
		// create folder if not exists
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		MultipartRequest multipartRequest = (MultipartRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		MultipartFile mfile = null;
		String fileNameBookImg = "default.png";
		String fileNameBookImgThumb = "default.png";
		if (fileMap.size() > 0) {
			for (Iterator<MultipartFile> iter = fileMap.values().iterator(); iter.hasNext();) {
				mfile = (MultipartFile) iter.next();
				String fileName = null;
				String nameInput = mfile.getName();
				String originalFilename = mfile.getOriginalFilename();
				String extension = FilenameUtils.getExtension(originalFilename);
				if (BOOK_IMAGE_NAME.equals(nameInput)) {
					if (originalFilename.isEmpty() && extension.isEmpty()) {
						fileName = bookInfo.map(e -> e.getBookImg()).orElse("default.png");
						fileNameBookImg = fileName;
					} else {
						fileName = UUID.randomUUID().toString().replace("-", "") + "." + extension;
						fileNameBookImg = fileName;
					}
				} else if (BOOK_IMAGE_THUMB.equals(nameInput)) {
					if (originalFilename.isEmpty() && extension.isEmpty()) {
						fileName = bookInfo.map(e -> e.getBookThumbImg()).orElse("default.png");
						fileNameBookImgThumb = fileName;
					} else {
						fileName = UUID.randomUUID().toString().replace("-", "") + "." + extension;
						fileNameBookImgThumb = fileName;
					}
				}
				try {
					if (!originalFilename.isEmpty() && !extension.isEmpty()) {
						Files.copy(mfile.getInputStream(), rootLocation.resolve(fileName),
								StandardCopyOption.REPLACE_EXISTING);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		book.setBookId(bookRequest.getBookId());
		book.setBookImg(fileNameBookImg);
		book.setBookThumbImg(fileNameBookImgThumb);
		book.setBookName(bookRequest.getBookName());
		book.setAuthorId(bookRequest.getAuthorId());
		book.setBookDescribe(bookRequest.getBookDescribe());
		book.setDelF(bookRequest.getDelF());
		book.setTypeBookId(bookRequest.getTypeBookId());
		book.setPublishYear(bookRequest.getPublishYear());
		book.setUpdtDt(new Date());
		book.setUpDtUserId(Long.valueOf(9999));
		book.setUpDtUserId(userInfor.getUserId());
		try {
			bookRepository.save(book);
		} catch (Exception e) {
			responseData.setData("error", "Sách này đã bị xóa");
			return responseData;
		}
		// return ResponseEntity.ok("OK");
		responseData.setData("data", "Sách đã được cập nhập");
		return responseData;
	}

	@RequestMapping(value = "/deleteBook", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseData deleteBook(@RequestBody BookDtoRequest bookRequest) {
		ResponseData responseData = ResponseData.create();
		Optional<BookEntity> bookInfo = bookRepository.findById(bookRequest.getBookId());
		if (!bookInfo.isPresent()) {
			responseData.setData("error", "Book được delete trước đo !!");
		}
		try {
			BookEntity book = bookInfo.get();
			book.setDelF(Long.valueOf(1));
			book.setDelDt(new Date());
			bookRepository.save(book);
		} catch (Exception e) {
			responseData.setData("error", "Sách này đã bị xóa");
			return responseData;
		}
		responseData.setData("data", "Book:"  + bookInfo.map(e -> e.getBookName()).orElse("") + " đã được xóa!!");
		return responseData;
	}
}
