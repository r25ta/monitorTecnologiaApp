package br.com.pamcary.ir.monitortecnologia.dao.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;

@Configuration
public class OracleDataSourceConfig {
	private static Logger LOGGER = LoggerFactory.getLogger(OracleDataSourceConfig.class);

	/*
	 * @Bean
	 * 
	 * @Primary
	 * 
	 * @ConfigurationProperties(prefix="spring.oracle.datasource") public
	 * DataSourceProperties oracleDataSourceProperties() { return new
	 * DataSourceProperties(); }
	 * 
	 * @Bean
	 * 
	 * @Primary
	 * 
	 * @ConfigurationProperties(prefix="spring.oracle.datasource.configuration")
	 * public HikariDataSource oracleDataSource() { return
	 * oracleDataSourceProperties().initializeDataSourceBuilder()
	 * .type(HikariDataSource.class).build(); }
	 */

	@Value("${spring.datasource.jndi-name}")
	private String oracleJndiConfig;

	@Bean
	public DataSource oracleJndiDataSource() {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean(); // create JNDI data source
		bean.setJndiName(oracleJndiConfig); // jndiDataSource is name of JNDI data source
		bean.setProxyInterface(DataSource.class);
		bean.setLookupOnStartup(false);

		try {
			bean.afterPropertiesSet();
		} catch (IllegalArgumentException e) {
			LOGGER.error("Erro de conexão com banco de dados! " + e);
			e.printStackTrace();
		} catch (NamingException e) {
			LOGGER.error("Erro de conexão com banco de dados! " + e);
			e.printStackTrace();
		}
		return (DataSource) bean.getObject();
	}
}
