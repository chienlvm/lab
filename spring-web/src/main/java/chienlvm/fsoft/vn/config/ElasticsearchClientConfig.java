package chienlvm.fsoft.vn.config;


import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author Pratik Das
 *
 */
@Configuration
@SuppressWarnings("deprecation")
@EnableElasticsearchRepositories(basePackages = "chienlvm.fpt.vn.repositories")
public class ElasticsearchClientConfig extends AbstractElasticsearchConfiguration {

	@Override
	@Bean
	public RestHighLevelClient elasticsearchClient() {

		final ClientConfiguration clientConfiguration = 
				ClientConfiguration
				.builder()
				.connectedTo("localhost:9200")
				.build();

		return RestClients
				.create(clientConfiguration)
				.rest();
		
	}
	

}
