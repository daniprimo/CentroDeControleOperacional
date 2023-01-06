package com.example.demo.repositoryJDBC;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public interface Repository {
	
	public ComboPooledDataSource ConnectionFactory();

}
