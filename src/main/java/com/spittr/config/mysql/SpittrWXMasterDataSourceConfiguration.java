package com.spittr.config.mysql;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * spittr_wx主库数据库连接配置类
 * 
 * @author chufei
 * @date 2017年9月25日
 */
@Configuration
@MapperScan(basePackages = "com.spittr.mapper.spittr_wx.master", sqlSessionFactoryRef = "spittrWXMasterSqlSessionFactory")
public class SpittrWXMasterDataSourceConfiguration {

	@Bean(name = "spittrWXMasterDataSource")
	@Qualifier("spittrWXMasterDataSource")
	@ConfigurationProperties(prefix = "c3p0.mysql.spittr_wx.master")
	public DataSource dataSource() {
		return DataSourceBuilder.create().type(ComboPooledDataSource.class).build();
	}

	@Bean(name = "spittrWXMasterTransactionManager")
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean(name = "spittrWXMasterSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		return sessionFactory.getObject();
	}
}
