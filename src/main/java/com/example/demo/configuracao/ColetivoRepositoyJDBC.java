package com.example.demo.configuracao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.example.demo.repositoryJDBC.Repository;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ColetivoRepositoyJDBC implements Repository{
	
	private DataSource dataSource;
	
	

	public ColetivoRepositoyJDBC() {
		DataSource dataSource =  ConnectionFactory();
		this.dataSource = dataSource;
	}



	@Override
	public ComboPooledDataSource ConnectionFactory() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/cco?createDatabaseIfNotExist=True");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("pedropaulo123");
		comboPooledDataSource.setMaxPoolSize(15);
		return comboPooledDataSource;
	}

	
	public Connection conectandoComBancoDeDados() {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			System.out.println("Problema ao conectar ao banco");
			e.printStackTrace();
		}
		return null;
	}
	

}
