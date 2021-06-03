/* Engine de Conexão ao Banco de Dados MySQL */

package com.crud.ibrplanner.dal;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DataConfigurationMySQL {

	// Bin de Conexão com o Banco de Dados MySQL
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/eventos");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		return dataSource;
	}
	
	// Bin de Conexão com o Hibernate
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);                                 // definição do Banco de Dados utilizado 
		adapter.setShowSql(true); 										     // exibe etapas dos scripts no Banco de Dados [log - passo a passo no console]
		adapter.setGenerateDdl(true); 									     // permite a criação das tabelas de forma automática no Banco de Dados
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");   // dialeto
		adapter.setPrepareConnection(true); 							     // para que o Hibernate prepare a Conexão
		
		return adapter;
	}	
}
