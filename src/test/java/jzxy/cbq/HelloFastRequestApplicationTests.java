package jzxy.cbq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootTest
class HelloFastRequestApplicationTests {
    @Autowired
    ApplicationContext context;

    @Test
    void contextLoads() {
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
    }

}
