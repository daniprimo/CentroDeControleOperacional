package com.example.demo.configuracao;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class ConfiguracaoDeConexaoJDBC {

	@Bean
	public ComboPooledDataSource ConnectionFactory() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/cco?createDatabaseIfNotExist=True");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("pedropaulo123");
		comboPooledDataSource.setMaxPoolSize(15);
		return comboPooledDataSource;
	}

	@Bean
	public Connection conectandoComBancoDeDados() {
		Connection connection;
		try {
			return connection = ConnectionFactory().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
