package org.blog.api;

import org.blog.api.domain.Account;
import org.blog.api.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(AccountRepository accountRepository) {
//        return args -> {
//            Account account = Account.builder()
//                    .email("hypemova@gmail.com")
//                    .username("sam")
//                    .password("1111")
//                    .build();
//
//            accountRepository.save(account);
//        };
//    }

}
