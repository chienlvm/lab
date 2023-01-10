package chienlvm.fsoft.vn.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import chienlvm.fsoft.vn.complete.JobCompletionNotificationListener;
import chienlvm.fsoft.vn.entity.BookEls;
import chienlvm.fsoft.vn.entity.BookInfo;
import chienlvm.fsoft.vn.entity.MailEntity;
import chienlvm.fsoft.vn.mapper.BookElsRowMapper;
import chienlvm.fsoft.vn.mapper.CustomerRowMapper;

@Configuration
@EnableBatchProcessing
@EnableScheduling
@EnableTransactionManagement
public class BatchElsConfiguration {
	private final Logger logger = LoggerFactory.getLogger(BatchElsConfiguration.class);
	// import book to els
	private final String SQL_ELASTIC_IMPORT_SELECT_BOOK = "SELECT \n" + "    TB.BOOK_ID,\n" + "    TB.BOOK_NAME,\n"
			+ "    TB.AUTHOR_ID,\n" + "    TB.TYPE_BOOK_ID,\n" + "	TYB.TYPE_BOOK_NAME,\n" + "    TBA.AUTHOR_NAME,\n"
			+ "    TBA.AUTHOR_DESCRIBE,\n" + "    TB.BOOK_IMG,\n" + "    TB.BOOK_THUMB_IMG,\n"
			+ "    TB.BOOK_DESCRIBE,\n" + "    TB.PUBLISH_YEAR,\n" + "    TB.DEL_F,\n" + "    TB.CRT_DT\n";
	private final String SQL_ELASTIC_IMPORT_FROM_BOOK = "FROM\n" + "    TB_BOOK TB\n" + "        LEFT JOIN\n"
			+ "    TB_BOOK_AUTHOR TBA ON TB.AUTHOR_ID = TBA.AUTHOR_ID\n" + "        LEFT JOIN\n"
			+ "    TB_TYPE_BOOK TYB ON TB.TYPE_BOOK_ID = TYB.TYPE_BOOK_ID";
	// SQL get list book
	private final String SQL_MAIL_SELECT_BOOK = "SELECT TMP.USER_ID, TMP.scrap_dt, TMP.USER_NAME, TMP.BOOK_ID, TMP.EMAIL, TMP.USER_NAME, tb_book.BOOK_NAME, tb_book.BOOK_DESCRIBE, TB_BOOK_AUTHOR.AUTHOR_NAME";
	private final String SQL_MAIL_FROM_BOOK = "" + "FROM\n" + "    (SELECT \n" + "        tb_scrap.USER_ID,\n"
			+ "            tb_scrap.BOOK_ID,\n" + "            tb_scrap.scrap_dt,\n" + "            tb_login.EMAIL,\n"
			+ "            tb_login.USER_NAME\n" + "    FROM\n" + "        tb_scrap\n"
			+ "    INNER JOIN tb_login ON tb_scrap.USER_ID = tb_login.USER_ID) AS TMP\n" + "        INNER JOIN\n"
			+ "    tb_book  ON TMP.BOOK_ID = tb_book.BOOK_ID\n" + "        INNER JOIN\n"
			+ "    TB_BOOK_AUTHOR ON tb_book.AUTHOR_ID = TB_BOOK_AUTHOR.AUTHOR_ID";
	private final String SQL_MAIL_WHERE_BOOK = "" + "WHERE\n" + "    USER_ID IN (SELECT \n" + "            USER_ID\n"
			+ "        FROM\n" + "            tb_scrap\n" + "        GROUP BY USER_ID\n"
			+ "        HAVING COUNT(USER_ID) > 10)";
	private String htmlMsgTemplate = "";
	private String htmlMsgContent = "";
	
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	@Autowired
	private DataSource dataSource;
	@Autowired
	public JavaMailSender emailSender;

	/**
	 * Get book
	 * 
	 * @return
	 */
	@Bean
	public JdbcPagingItemReader<BookEls> getBookItemReader() {
		// count book
		// fetch
		JdbcPagingItemReader<BookEls> reader = new JdbcPagingItemReader<>();
		reader.setDataSource(this.dataSource);
		reader.setFetchSize(1000);
		reader.setPageSize(1000);
		reader.setRowMapper(new BookElsRowMapper());

		MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
		Map<String, Order> sortKeys = new HashMap<>();
		sortKeys.put("BOOK_ID", Order.ASCENDING);
		queryProvider.setSelectClause(SQL_ELASTIC_IMPORT_SELECT_BOOK);
		queryProvider.setFromClause(SQL_ELASTIC_IMPORT_FROM_BOOK);
		queryProvider.setSortKeys(sortKeys);
		reader.setQueryProvider(queryProvider);

		return reader;
	}

	@Bean
	public ItemProcessor<BookEls, BookEls> processorBookEls() {
		return new ItemProcessor<BookEls, BookEls>() {

			@Override
			public BookEls process(final BookEls bookEls) throws Exception {
				return bookEls;
			}
		};
	}

	/*-------------------------------------------------------------------------------------*/
	@Bean
	public Step importBookToEls(StepBuilderFactory stepBuilderFactory, ItemWriter<BookEls> BookElsItemWriter) {
		return stepBuilderFactory.get("importBookToEls").<BookEls, BookEls>chunk(100).reader(getBookItemReader())
				.writer(BookElsItemWriter).build();
	}

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory, Step importBookToEls) {
		logger.info("-------START BATCH IMPORT BOOK-------");
		return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer()).start(importBookToEls).build();
	}
	/*-------------------------------------------------------------------------------------*/
	
	@Bean
	public JdbcPagingItemReader<MailEntity> pagingItemReader() {
		// step 1: get userId has favorite 10 book
		// Step 2: Get bookId by userId;
		// Step 3: Get detail user by userId and Book

		JdbcPagingItemReader<MailEntity> reader = new JdbcPagingItemReader<>();
		reader.setDataSource(this.dataSource);
		reader.setFetchSize(1000);
		reader.setPageSize(1000);
		reader.setRowMapper(new CustomerRowMapper());

		Map<String, Order> sortKeys = new HashMap<>();
		sortKeys.put("USER_ID", Order.DESCENDING);
		sortKeys.put("scrap_dt", Order.DESCENDING);
		MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
		queryProvider.setSelectClause(SQL_MAIL_SELECT_BOOK);
		queryProvider.setFromClause(SQL_MAIL_FROM_BOOK);
		queryProvider.setWhereClause(SQL_MAIL_WHERE_BOOK);
		queryProvider.setSortKeys(sortKeys);
		reader.setQueryProvider(queryProvider);

		return reader;
	}

	@Bean
	public ItemWriter<MailEntity> customerItemWriter() {
		return items -> {
			if (!items.isEmpty()) {
				List<MailEntity> lstMail = new ArrayList<>();
				for (MailEntity c : items) {
					MailEntity mail = new MailEntity();
					mail.setBookId(c.getBookId());
					mail.setEmail(c.getEmail());
					mail.setScrapDt(c.getScrapDt());
					mail.setUserId(c.getUserId());
					mail.setBookAuthor(c.getBookAuthor());
					mail.setBookDescribe(c.getBookDescribe());
					mail.setBookName(c.getBookName());
					mail.setUseName(c.getBookName());
					lstMail.add(mail);
				}
				lstMail.sort((o1, o2) -> o1.getScrapDt().compareTo(o2.getScrapDt()));
				Map<String, List<BookInfo>> data = this.getDataForMail(lstMail);
				// method send email
				this.sendMail(data);
			}
		};
	}

	private Map<String, List<BookInfo>> getDataForMail(List<MailEntity> lstMail) {
		return lstMail.stream()
				.collect(Collectors.groupingBy(MailEntity::getEmail, Collectors.mapping(
						mo -> new BookInfo(mo.getBookId(), mo.getBookDescribe(), mo.getBookAuthor(), mo.getBookName()),
						Collectors.toList())));
	}

	private void sendMail(Map<String, List<BookInfo>> data) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		message.setHeader("Content-Type", "text/plain; charset=UTF-8");
		boolean multipart = true;
		data.forEach((key, value) -> {
			value.stream().limit(10).forEach(book -> {
				htmlMsgContent = htmlMsgContent + "<tbody>"
						+ "<tr>"
						+ "  <td align=\"left\" style=\"padding:20px 0 17px;border-bottom:1px solid #e5e5e5\""
						+ "    valign=\"top\">"
						+ "    <table cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse\""
						+ "      width=\"100%\">"
						+ "      <tbody>"
						+ "        <tr>"
						+ "          <td style=\"margin:0;padding:0\">"
						+ "            <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\""
						+ "              style=\"border-collapse:collapse;table-layout:fixed\" width=\"100%\">"
						+ "              <tbody>"
						+ "                <tr>"
						+ "                  <td style=\"margin:0;padding:0 10px 0 0\"> <a href=\" "+book.getBookId()+ "\""
						+ "                      style=\"color:#005aff;text-decoration:none;font-size:18px;line-height:20px;font-weight:700;outline:none;font-family:Arial,sans-serif\""
						+ "                      target=\"_blank\">"
						+ "                      "+ book.getBookName()+ "</a>"
						+ "                    <p"
						+ "                      style=\"margin:5px 0 0 0;padding:3px 0 0;line-height:18px;color:#777777;font-weight:700;font-size:13.5px;font-family:Arial,sans-serif\">"
						+ "                      "+ book.getBookDescribe()+ "</p>"
						+ "                    <p"
						+ "                      style=\"margin:0 0 2px 0;padding:0;color:#999999;font-size:13.5px;font-family:Arial,sans-serif;line-height:18px\">"
						+ "                      <span style=\"width:auto\"> Tác giả: "+ book.getBookAuthor()+ " </span>"
						+ "                    </p>"
						+ "                  </td>"
						+ "                </tr>"
						+ "              </tbody>"
						+ "            </table>"
						+ "          </td>"
						+ "        </tr>"
						+ "      </tbody>"
						+ "    </table>"
						+ "  </td>"
						+ "</tr>"
						+ "</tbody>";
				
				
			});
			htmlMsgTemplate = "  <div marginheight=\"0\" marginwidth=\"0\">"
					+ "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\""
					+ "      style=\"width:100%;margin:0 auto;background-color:#e6efff;max-width:680px\" width=\"100%\">"
					+ "      <tbody>"
					+ "        <tr>"
					+ "          <td align=\"center\" valign=\"top\" width=\"100%\">"
					+ "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\""
					+ "              style=\"margin:0 auto;max-width:600px;border:5px solid #e6efff;border-top:0\" width=\"100%\">"
					+ "              <tbody>"
					+ "                <tr>"
					+ "                  <td align=\"center\" valign=\"top\" width=\"100%\">"
					+ "                    <table bgcolor=\"\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"margin:5px auto 0\" width=\"100%\">"
					+ "                      <tbody>"
					+ "                        <tr>"
					+ "                          <td align=\"left\" style=\"padding:15px 0 15px 6%\" valign=\"top\" width=\"100%\"> </td>"
					+ "                        </tr>"
					+ "                      </tbody>"
					+ "                    </table>"
					+ "                  </td>"
					+ "                </tr>"
					+ "                <tr>"
					+ "                  <td align=\"center\" bgcolor=\"#ffffff\" valign=\"top\" width=\"100%\">"
					+ "                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"88%\">"
					+ "                      <tbody>"
					+ "                        <tr>"
					+ "                          <td height=\"35\" style=\"height:35px\"> &nbsp;</td>"
					+ "                        </tr>"
					+ "                        <tr>"
					+ "                          <td align=\"center\" valign=\"top\" width=\"100%\">"
					+ "                            <table bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" style=\"padding:0\" width=\"100%\">"
					+ "                              <tbody>"
					+ "<tr>"
					+ "  <td style=\"padding-bottom:27px\">"
					+ "    <p"
					+ "      style=\"font-family:Arial,sans-serif;font-size:16px;text-align:left;margin:0 0 0 0;color:#4a4a4a;line-height:21px\">"
					+ "      <strong>10 cuốn sách bạn thích nhất:</strong>"
					+ "    </p>"
					+ "  </td>"
					+ "</tr>"
					+ "                              </tbody>"
					+ "                            </table>"
					+ "                            <table bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\""
					+ "                              style=\"border-top:1px solid #e5e5e5\" width=\"100%\">"
					+ htmlMsgContent +
					"                            </table>"
					+ "                          </td>"
					+ "                        </tr>"
					+ "                        <tr>"
					+ "                          <td height=\"20\"> &nbsp;</td>"
					+ "                        </tr>"
					+ "                      </tbody>"
					+ "                    </table>"
					+ "                  </td>"
					+ "                </tr>"
					+ "              </tbody>"
					+ "            </table>"
					+ "          </td>"
					+ "        </tr>"
					+ "      </tbody>"
					+ "    </table>"
					+ "  </div>";
			MimeMessageHelper helper = null;
			try {
				helper = new MimeMessageHelper(message, multipart, "utf-8");
				message.setContent(htmlMsgTemplate, "text/html; charset=UTF-8");
				helper.setTo(key);
				helper.setSubject("Danh sách yêu thích của bạn");
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			this.emailSender.send(message);
			htmlMsgTemplate = "";
			htmlMsgContent = "";
		});
	}

	@Bean
	public ItemProcessor<MailEntity, MailEntity> processor() {
		return new ItemProcessor<MailEntity, MailEntity>() {

			@Override
			public MailEntity process(final MailEntity FavoriteBook) throws Exception {
				return FavoriteBook;
			}
		};
	}

	@Bean
	public Job sendMail() throws Exception {
		return jobBuilderFactory.get("sendMail").incrementer(new RunIdIncrementer())
				.listener(new JobCompletionNotificationListener()).flow(step1()).end().build();
	}

	@Bean
	public Step step1() throws Exception {
		return stepBuilderFactory.get("step1").<MailEntity, MailEntity>chunk(999999).reader(pagingItemReader())
				.processor(processor()).writer(customerItemWriter()).build();
	}
}
