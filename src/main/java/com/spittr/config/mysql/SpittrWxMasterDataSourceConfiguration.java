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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

/**
 * spittr_wx主库数据库连接配置类
 * 
 * @author chufei
 * @date 2017年9月25日
 */
@Configuration
@MapperScan(basePackages = "com.spittr.mapper.wx.master", sqlSessionFactoryRef = "spittrWxMasterSqlSessionFactory")
public class SpittrWxMasterDataSourceConfiguration {

	@Bean(name = "spittrWxMasterDataSource")
	@Qualifier("spittrWxMasterDataSource")
	@ConfigurationProperties(prefix = "hikari.mysql.spittr.wx.master")
	public DataSource dataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Bean(name = "spittrWxMasterTransactionManager")
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean(name = "spittrWxMasterSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		return sessionFactory.getObject();
	}
}
