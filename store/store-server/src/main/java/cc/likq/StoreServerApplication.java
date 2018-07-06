package cc.likq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author likq
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan(basePackages = {"cc.likq", "com.isxxc"})
public class StoreServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreServerApplication.class, args);
    }
}
