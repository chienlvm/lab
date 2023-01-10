package chienlvm.fsoft.vn.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.TopHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import chienlvm.fsoft.vn.entity.AuthorEls;
import chienlvm.fsoft.vn.entity.BookEls;
import chienlvm.fsoft.vn.entity.TypeBookEls;
import chienlvm.fsoft.vn.repository.BookRepository;

/**
 * @author ChienLVM
 */
@SuppressWarnings("deprecation")
@Service
public class ProductSearchService {

	private static final String PRODUCT_INDEX = "book";

	private ElasticsearchOperations elasticsearchOperations;

	@Autowired
	private RestHighLevelClient client;

	@Autowired
	public ProductSearchService(final ElasticsearchOperations elasticsearchOperations,
			final BookRepository bookRepository) {
		super();
		this.elasticsearchOperations = elasticsearchOperations;
	}

	public List<BookEls> findBybookName(final String query) {
		// 1. Create query on multiple fields enabling fuzzy search
		QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(query, "bookName", "bookDescribe").fuzziness(0.5);

		Query searchQuery = new NativeSearchQueryBuilder().withFilter(queryBuilder).build();

		// 2. Execute search
		SearchHits<BookEls> productHits = elasticsearchOperations.search(searchQuery, BookEls.class,
				IndexCoordinates.of(PRODUCT_INDEX));

		// 3. Map searchHits to product list
		List<BookEls> productMatches = new ArrayList<BookEls>();
		productHits.forEach(srchHit -> {
			productMatches.add(srchHit.getContent());
		});
		return productMatches;
	}

	@SuppressWarnings({ "unchecked" })
	public List<TypeBookEls> searchAgrreagetionTypeBook() {
		TermsAggregationBuilder aggregation = AggregationBuilders.terms("typebook_terms").field("typeBook.typeBookId")
				.size(10).subAggregation(AggregationBuilders.topHits("typebook_tophits").fetchSource("typeBook", "").size(1));

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
	@SuppressWarnings({ "unchecked" })
	public List<AuthorEls> searchAgrreagetionAuthor() {
		TermsAggregationBuilder aggregation = AggregationBuilders.terms("author_terms").field("author.authorId")
				.size(10).subAggregation(AggregationBuilders.topHits("author_tophits").fetchSource("author", "").size(1));

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
