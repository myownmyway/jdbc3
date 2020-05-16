package com.wpw.springbootjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringbootJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJdbcApplication.class, args);
    }

}
