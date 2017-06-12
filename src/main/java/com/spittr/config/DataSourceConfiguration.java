package com.spittr.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * 数据库连接配置类
 * @author chufei
 * @date 2017年6月12日
 */
@Configuration
@PropertySource("classpath:properties/jdbc.properties")
public class DataSourceConfiguration {

	@Autowired
	private Environment env;

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(env.getProperty("jdbc.mysql.driverClassName"));
		driverManagerDataSource.setUrl(env.getProperty("jdbc.mysql.url"));
		driverManagerDataSource.setUsername(env.getProperty("jdbc.mysql.username"));
		driverManagerDataSource.setPassword(env.getProperty("jdbc.mysql.password"));
		return driverManagerDataSource;
	}

	@Bean(name = "transactionManager")
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(this.dataSource());
		return dataSourceTransactionManager;
	}

	/**
	 * 使用mybatis-spring-boot-start可不要配置SqlSessionFactory和SqlSessionTemplate
	 * 
	 * @Bean(name="sqlSessionFactory") public SqlSessionFactory
	 *                                 sqlSessionFactory() throws Exception {
	 *                                 SqlSessionFactoryBean
	 *                                 sqlSessionFactoryBean = new
	 *                                 SqlSessionFactoryBean();
	 *                                 sqlSessionFactoryBean.setDataSource(this.dataSource());
	 *                                 sqlSessionFactoryBean.setTypeAliasesPackage(env.getProperty("mybatis.type-aliases-package"));
	 * 
	 *                                 SqlSessionFactory sqlSessionFactory =
	 *                                 sqlSessionFactoryBean.getObject();
	 *                                 sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
	 *                                 sqlSessionFactory.getConfiguration().setCacheEnabled(true);
	 *                                 sqlSessionFactory.getConfiguration().setUseGeneratedKeys(true);
	 *                                 return sqlSessionFactory; }
	 * 
	 * @Bean(name="sqlSessionTemplate") public SqlSessionTemplate
	 *                                  sqlSessionTemplate() throws Exception {
	 *                                  return new
	 *                                  SqlSessionTemplate(this.sqlSessionFactory());
	 *                                  }
	 */

}
