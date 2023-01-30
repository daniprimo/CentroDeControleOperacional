package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GaragemStatica {

	@Autowired
	private Connection connection;
	
	public GaragemStatica(Connection connection) {
		super();
		this.connection = connection;
	}



	public  void populandoBanco() {
		
		String sql = "INSERT INTO cco.tb_coletivo(prefixo, placa, modelo, doc, cor, status, garagem_id)\r\n"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		for (int i = 0; i < 20; i++) {
			
			Random r = new Random();
			String placa = "BDF-"+ String.valueOf(r.nextInt(5000));
			String prefixo = "21." + String.valueOf(r.nextInt(5000));
			String modelo = "0071" + String.valueOf(r.nextInt(5000));
			
			
			
			
			try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				statement.setString(1, prefixo);
				statement.setString(2, placa);
				statement.setString(3, "Mercedez Benz");
				statement.setString(4, modelo);
				statement.setString(5, "cinza");
				statement.setString(6, "Operando");
				statement.setString(7, "2");
				
				statement.execute();
				
				}catch (SQLException e) {
					System.out.println("Erro ao adicionar coletivo");
					e.printStackTrace();
				}
				
		}
	}
		
		
}
	
	


	


