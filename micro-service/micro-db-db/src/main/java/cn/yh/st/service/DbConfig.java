package cn.yh.st.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DbConfig {

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.user")
	public DataSourceProperties userDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource userDataSource() {
		return userDataSourceProperties().initializeDataSourceBuilder()
				.type(HikariDataSource.class).build();
	}

	@Bean
	public JdbcTemplate userJdbcTemplate(@Qualifier("userDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.order")
	public DataSourceProperties orderDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager ut = new DataSourceTransactionManager(userDataSource());
		DataSourceTransactionManager ot = new DataSourceTransactionManager(orderDataSource());
		ChainedTransactionManager t = new ChainedTransactionManager(ut,ot);
		return t;
	}

	@Bean
	@Primary
	public DataSource orderDataSource() {
		return orderDataSourceProperties().initializeDataSourceBuilder()
				.type(HikariDataSource.class).build();
	}

	@Bean
	public JdbcTemplate orderJdbcTemplate(@Qualifier("orderDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
