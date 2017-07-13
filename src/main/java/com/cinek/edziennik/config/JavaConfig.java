package com.cinek.edziennik.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.cinek.edziennik.repository.UserRepository;
import com.cinek.edziennik.repository.impl.HibernateUserRepository;

@Configuration
public class JavaConfig {
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/edziennik");
		dataSource.setUsername("root");
		dataSource.setPassword("cinek");
		return dataSource;
	}


}
