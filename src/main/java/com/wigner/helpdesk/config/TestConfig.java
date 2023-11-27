package com.wigner.helpdesk.config;

import com.wigner.helpdesk.Services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String value;

    @Bean
    public boolean instanciaDB() {
        if(value.equals("create")) {
            this.dbService.instanciaDB();
        }
        return false;
    }

}
