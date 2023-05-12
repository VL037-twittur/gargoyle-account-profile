package vincentlow.twittur.account.profile.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableDiscoveryClient
@ComponentScan(basePackages = {"vincentlow.twittur.account.profile.*"})
@EntityScan(basePackages = {"vincentlow.twittur.account.profile.*"})
@EnableJpaRepositories(basePackages = {"vincentlow.twittur.account.profile.*"})
public class GargoyleAccountProfileApplication {

  public static void main(String[] args) {

    SpringApplication.run(GargoyleAccountProfileApplication.class);
  }
}
