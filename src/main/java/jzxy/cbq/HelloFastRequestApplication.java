package jzxy.cbq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class HelloFastRequestApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloFastRequestApplication.class, args);

        log.info("HelloFastRequestApplication run success ");
    }

}
