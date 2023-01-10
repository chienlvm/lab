package chienlvm.fsoft.vn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;




@SpringBootApplication
@ComponentScan(basePackages = { "chienlvm" })
@EntityScan(basePackages = { "chienlvm" })
@EnableJpaRepositories(basePackages = { "chienlvm" })
public class SpringWebAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebAdminApplication.class, args);
	}

}
