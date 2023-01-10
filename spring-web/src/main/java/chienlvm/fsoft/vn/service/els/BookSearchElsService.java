package chienlvm.fsoft.vn.service.els;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.TopHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import chienlvm.fsoft.vn.entity.els.AuthorEls;
import chienlvm.fsoft.vn.entity.els.AuthorEls01;
import chienlvm.fsoft.vn.entity.els.BookEls;
import chienlvm.fsoft.vn.entity.els.TypeBook;
import chienlvm.fsoft.vn.entity.els.TypeBookEls;

/**
 * @author ChienLVM
 */
@SuppressWarnings("deprecation")
@Service
public class BookSearchElsService {

	private static final String PRODUCT_INDEX = "book";

//	private ElasticsearchOperations elasticsearchOperations;
//
//	@Autowired
//	private BookElsRepository bookElsRepository;

	@Autowired
	private RestHighLevelClient client;

//	@Autowired
//	public BookSearchElsService(ElasticsearchOperations elasticsearchOperations,
//			BookElsRepository bookElsRepository) {
//		super();
//		this.elasticsearchOperations = elasticsearchOperations;
//		this.bookElsRepository = bookElsRepository;
//	}

	/**
	 * Get all book newer
	 * 
	 * @param pageSize
	 * @return
	 */
	public List<BookEls> getNewerBook(int pageSize) {

		QueryBuilder queryBuilder = QueryBuilders.queryStringQuery("*");

		SearchSourceBuilder builder = new SearchSourceBuilder().query(queryBuilder)
				.sort(SortBuilders.fieldSort("bookId").order(SortOrder.DESC)).size(pageSize);
		SearchRequest searchRequest = new SearchRequest().source(builder).indices(PRODUCT_INDEX);
		List<BookEls> bookMatches = new ArrayList<BookEls>();
		try {
			SearchResponse response1 = client.search(searchRequest, RequestOptions.DEFAULT);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			for (SearchHit hit : response1.getHits()) {
				String sourceAsString = hit.getSourceAsString();
				BookEls bookEls = gson.fromJson(sourceAsString, BookEls.class);
				bookMatches.add(bookEls);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookMatches;
	}

	public List<BookEls> searchBookWithHighlight(final String query) {
		// 1. Create query on multiple fields enabling fuzzy search
		QueryBuilder queryBuilder = QueryBuilders
				.multiMatchQuery(query, "bookName", "bookDescribe", "author.authorName", "typeBook.typeBookName")
				.fuzziness(0.5);
		HighlightBuilder highlightBuilder = new HighlightBuilder();
		HighlightBuilder.Field hlBook = new HighlightBuilder.Field("bookName");
		HighlightBuilder.Field hlBookDescribe = new HighlightBuilder.Field("bookDescribe");
		HighlightBuilder.Field hlAuthorName = new HighlightBuilder.Field("author.authorName");
		HighlightBuilder.Field hlBookTypeBookName = new HighlightBuilder.Field("typeBook.typeBookName");

		highlightBuilder.field(hlBook);
		highlightBuilder.field(hlBookDescribe);
		highlightBuilder.field(hlAuthorName);
		highlightBuilder.field(hlBookTypeBookName);
		highlightBuilder.preTags("<span class='highlighter' style=\"color:#5488c7; font-weight: bold;\">");
		highlightBuilder.postTags("</span>");

		String hlBookNameTxt = "";
		String hlBookDescribeTxt = "";
		String hlAuthorNameTxt = "";
		String hlTypeBookNameTxt = "";

		SearchSourceBuilder builder = new SearchSourceBuilder().query(queryBuilder).highlighter(highlightBuilder);
		SearchRequest searchRequest = new SearchRequest().source(builder).indices(PRODUCT_INDEX);
		List<BookEls> bookMatches = new ArrayList<BookEls>();
		try {
			SearchResponse response1 = client.search(searchRequest, RequestOptions.DEFAULT);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			for (SearchHit hit : response1.getHits()) {
				String sourceAsString = hit.getSourceAsString();
				BookEls bookEls = gson.fromJson(sourceAsString, BookEls.class);

				HighlightField hField = hit.getHighlightFields().get("bookName");
				if (hField != null) {
					Text[] hlText = hField.getFragments();
					hlBookNameTxt = hlText[0].toString();
					bookEls.setBookName(hlBookNameTxt);
				}

				HighlightField hFieldBookDescribe = hit.getHighlightFields().get("bookDescribe");
				if (hFieldBookDescribe != null) {
					Text[] hlTextBookDescribe = hFieldBookDescribe.getFragments();
					hlBookDescribeTxt = hlTextBookDescribe[0].toString();
					bookEls.setBookDescribe(hlBookDescribeTxt);
				}

				HighlightField hFieldAuthorName = hit.getHighlightFields().get("author.authorName");
				if (hFieldAuthorName != null) {
					Text[] hlTextAuthorName = hFieldAuthorName.getFragments();
					hlAuthorNameTxt = hlTextAuthorName[0].toString();
					AuthorEls01 author = new AuthorEls01();
					author.setAuthorId(bookEls.getAuthor().getAuthorId());
					author.setAuthorName(hlAuthorNameTxt);
					bookEls.setAuthorEls01(author);
				}

				HighlightField hlFieldTypeBookName = hit.getHighlightFields().get("typeBook.typeBookName");
				if (hlFieldTypeBookName != null) {
					Text[] hlTextTypeBookName = hlFieldTypeBookName.getFragments();
					hlTypeBookNameTxt = hlTextTypeBookName[0].toString();
					TypeBook typebook = new TypeBook();
					typebook.setTypeBookId(bookEls.getTypeBook().getTypeBookId());
					typebook.setTypeBookName(hlTypeBookNameTxt);
					bookEls.setTypeBook(typebook);
				}
				bookMatches.add(bookEls);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bookMatches;
	}

	@SuppressWarnings({ "unchecked" })
	public List<TypeBookEls> searchAgrreagetionTypeBook(int pageSize) {
		TermsAggregationBuilder aggregation = AggregationBuilders.terms("typebook_terms").field("typeBook.typeBookId")
				.size(pageSize)
				.subAggregation(AggregationBuilders.topHits("typebook_tophits").fetchSource("typeBook", "").size(1));

		final SearchSourceBuilder builder = new SearchSourceBuilder().aggregation(aggregation).size(0);
		final SearchRequest searchRequest = new SearchRequest().source(builder).indices(PRODUCT_INDEX);
		List<TypeBookEls> data = new ArrayList<>();
		try {
			final SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
			Terms termRS = response.getAggregations().get("typebook_terms");
			Collection<Terms.Bucket> buckets = (Collection<Terms.Bucket>) termRS.getBuckets();
			for (Terms.Bucket bucket : buckets) {
				if (bucket.getDocCount() != 0) {
					TopHits terms = bucket.getAggregations().get("typebook_tophits");
					for (SearchHit hit : terms.getHits().getHits()) {
						Map<String, Object> keyValues = hit.getSourceAsMap();
						Map<String, TypeBookEls> typeBooks = (Map<String, TypeBookEls>) keyValues.get("typeBook");
						TypeBookEls book = new TypeBookEls();
						book.setTotal((int) bucket.getDocCount());
						for (Map.Entry<String, TypeBookEls> entry : typeBooks.entrySet()) {
							if (entry.getKey().equals("typeBookId")) {
								Long id = Long.valueOf(String.valueOf(entry.getValue()));
								book.setTypeBookId(id);
							} else {
								book.setTypeBookName(String.valueOf(entry.getValue()));
							}
						}
						data.add(book);
					}
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}

	public List<BookEls> searchBookWithNoHighlight(final String query) {
		// 1. Create query on multiple fields enabling fuzzy search
		QueryBuilder queryBuilder = QueryBuilders
				.multiMatchQuery(query, "bookName", "bookDescribe", "author.authorName", "typeBook.typeBookName")
				.fuzziness(0.5);
		SearchSourceBuilder builder = new SearchSourceBuilder().query(queryBuilder);
		SearchRequest searchRequest = new SearchRequest().source(builder).indices(PRODUCT_INDEX);
		List<BookEls> productMatches = new ArrayList<BookEls>();
		// 2. Execute search
		try {
			SearchResponse response1 = client.search(searchRequest, RequestOptions.DEFAULT);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			for (SearchHit hit : response1.getHits()) {
				String sourceAsString = hit.getSourceAsString();
				BookEls bookEls = gson.fromJson(sourceAsString, BookEls.class);
				productMatches.add(bookEls);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productMatches;
	}

	@SuppressWarnings({ "unchecked" })
	public List<AuthorEls> searchAgrreagetionAuthor(int pageSize) {
		TermsAggregationBuilder aggregation = AggregationBuilders.terms("author_terms").field("author.authorId")
				.size(pageSize)
				.subAggregation(AggregationBuilders.topHits("author_tophits").fetchSource("author", "").size(1));

		final SearchSourceBuilder builder = new SearchSourceBuilder().aggregation(aggregation).size(0);
		final SearchRequest searchRequest = new SearchRequest().source(builder).indices(PRODUCT_INDEX);
		List<AuthorEls> data = new ArrayList<>();
		try {
			final SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
			Terms termRS = response.getAggregations().get("author_terms");
			Collection<Terms.Bucket> buckets = (Collection<Terms.Bucket>) termRS.getBuckets();
			for (Terms.Bucket bucket : buckets) {
				if (bucket.getDocCount() != 0) {
					TopHits terms = bucket.getAggregations().get("author_tophits");
					for (SearchHit hit : terms.getHits().getHits()) {
						Map<String, Object> keyValues = hit.getSourceAsMap();
						Map<String, AuthorEls> typeBooks = (Map<String, AuthorEls>) keyValues.get("author");
						AuthorEls author = new AuthorEls();
						for (Map.Entry<String, AuthorEls> entry : typeBooks.entrySet()) {
							if (entry.getKey().equals("authorId")) {
								Long id = Long.valueOf(String.valueOf(entry.getValue()));
								author.setAuthorId(id);
							} else {
								author.setAuthorName(String.valueOf(entry.getValue()));
							}
						}
						author.setTotal((int) bucket.getDocCount());
						data.add(author);
					}
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}

}
