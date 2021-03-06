package com.spittr.config.mysql;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

/**
 * spittr主库数据库连接配置类
 * 
 * @author chufei
 * @date 2017年6月12日
 */
@Configuration
@MapperScan(basePackages = "com.spittr.mapper.master", sqlSessionFactoryRef = "spittrMasterSqlSessionFactory")
public class SpittrMasterDataSourceConfiguration {

	@Bean(name = "spittrMasterDataSource")
	@Qualifier("spittrMasterDataSource")
	@Primary
	@ConfigurationProperties(prefix = "hikari.mysql.spittr.master")
	public DataSource dataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Bean(name = "spittrMasterTransactionManager")
	@Primary
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean(name = "spittrMasterSqlSessionFactory")
	@Primary
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		return sessionFactory.getObject();
	}
	
}
