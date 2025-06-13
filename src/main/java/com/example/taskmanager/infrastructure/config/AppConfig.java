package com.example.taskmanager.infrastructure.config;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

	@Bean
	public Config domaConfig(DataSource dataSource) {
		// Doma Config の実装を返却
		return new Config() {

			//mysqlを使用する
			@Override
			public Dialect getDialect() {
				return new org.seasar.doma.jdbc.dialect.MysqlDialect();
			}

			@Override
			public javax.sql.DataSource getDataSource() {
				return dataSource;
			}
		};
	}
}
