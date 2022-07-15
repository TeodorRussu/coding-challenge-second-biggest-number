package coding.secondbiggernumber;

import coding.secondbiggernumber.core.SecondBiggestNumberFinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
@Slf4j
public class Application implements CommandLineRunner {

    @Autowired
    SecondBiggestNumberFinder secondBiggestNumberFinder;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(50000000);
        executor.setThreadNamePrefix("Number-Finder-");
        executor.initialize();
        return executor;
    }

    @Override
    public void run(String... args) throws Exception {
    }
}