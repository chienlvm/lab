package chienlvm.fsoft.vn.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import chienlvm.fsoft.vn.entity.MailEntity;

public class CustomerRowMapper implements RowMapper<MailEntity> {
	private static final DateFormat DT_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public MailEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		rs.setFetchSize(99999999);
		MailEntity lstBook = new MailEntity();
		lstBook.setEmail(rs.getString("email"));
		lstBook.setUserId(rs.getLong("user_id"));
		lstBook.setBookId(rs.getLong("book_id"));
		lstBook.setBookAuthor(rs.getString("AUTHOR_NAME"));
		lstBook.setBookDescribe(rs.getString("BOOK_DESCRIBE"));
		lstBook.setBookName(rs.getString("BOOK_NAME"));
		lstBook.setUseName(rs.getString("USER_NAME"));
		try {
			lstBook.setScrapDt(DT_FORMAT.parse(rs.getString("scrap_dt")));
		} catch (ParseException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstBook;
		// @formatter:on
	}
}