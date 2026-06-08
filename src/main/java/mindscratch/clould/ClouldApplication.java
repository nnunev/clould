package mindscratch.clould;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ClouldApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClouldApplication.class, args);
    }
}
