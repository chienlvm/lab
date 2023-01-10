package chienlvm.fsoft.vn.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import chienlvm.fsoft.vn.entity.Author;
import chienlvm.fsoft.vn.entity.BookEls;
import chienlvm.fsoft.vn.entity.TypeBook;

public class BookElsRowMapper implements RowMapper<BookEls> {

	@Override
	public BookEls mapRow(ResultSet rs, int rowNum) throws SQLException {
		BookEls lstBook = new BookEls();
		Author author = new Author();
		author.setAuthorId(rs.getLong("AUTHOR_ID"));
		author.setAuthorName(rs.getString("AUTHOR_NAME"));
		TypeBook typeBook = new TypeBook();
		typeBook.setTypeBookId(rs.getLong("TYPE_BOOK_ID"));
		typeBook.setTypeBookName(rs.getString("TYPE_BOOK_NAME"));
		
		lstBook.setBookId(rs.getLong("BOOK_ID"));
		lstBook.setAuthor(author);
		lstBook.setDelF(rs.getString("DEL_F"));
		lstBook.setBookDescribe(rs.getString("BOOK_DESCRIBE"));
		lstBook.setBookName(rs.getString("BOOK_NAME"));
		lstBook.setBookThumbImg(rs.getString("BOOK_THUMB_IMG"));
		lstBook.setBookImg(rs.getString("BOOK_IMG"));
		lstBook.setTypeBook(typeBook);
		lstBook.setPublicYear(rs.getDate("PUBLISH_YEAR"));
		lstBook.setCreateDt(rs.getDate("CRT_DT"));
		
//		private Long bookId;
//		private String bookName;
//		private String bookDescribe;
//		private String delF;
//		private String bookImg;
//		private String bookThumbImg;
//		private Author author;
//		private TypeBook typeBook;
		
//		try {
//			lstBook.setScrapDt(DT_FORMAT.parse(rs.getString("scrap_dt")));
//		} catch (ParseException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return lstBook;
		// @formatter:on
	}
}