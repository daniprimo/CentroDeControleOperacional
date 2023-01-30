package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.coletivoDtos.RelatorioColetivo;
import com.example.demo.entities.Coletivo;

@Repository
public interface RelatorioRepository extends JpaRepository<Coletivo, Long> {

		@Query(value = "select c.id, c.prefixo, c.status,g.nome,"
				+ " g.cidade from cco.tb_coletivo as c inner join cco.tb_garagem as g on g.id = c.garagem_id", nativeQuery = true)
		List<RelatorioColetivo> findRelatorioColetivoRefGaragem ();

		@Query(value = "select c.id, c.prefixo, c.status,g.nome,"
				+ " g.cidade from cco.tb_coletivo as c inner join cco.tb_garagem as g on g.id = c.garagem_id"
				+ " WHERE g.nome = 'Garagem filial carapicuiba' ", nativeQuery = true)
		List<RelatorioColetivo> findRelatorioColetivoGaragemFilial ();
		
		@Query(value = "select c.id, c.prefixo, c.status,g.nome,"
				+ " g.cidade from cco.tb_coletivo as c inner join cco.tb_garagem as g on g.id = c.garagem_id"
				+ " WHERE g.nome = 'Garagem matriz' ", nativeQuery = true)
		List<RelatorioColetivo> findRelatorioColetivoGaragemMatriz ();
		
		@Query(value = "select c.id, c.prefixo, c.status,g.nome,"
				+ " g.cidade from cco.tb_coletivo as c inner join cco.tb_garagem as g on g.id = c.garagem_id"
				+ " WHERE c.status = 'Operando' ", nativeQuery = true)
		List<RelatorioColetivo> findRelatorioColetivoOperando ();
		
		@Query(value = "select c.id, c.prefixo, c.status,g.nome,"
				+ " g.cidade from cco.tb_coletivo as c inner join cco.tb_garagem as g on g.id = c.garagem_id"
				+ " WHERE c.status = 'manutencao'  ", nativeQuery = true)
		List<RelatorioColetivo> findRelatorioColetivoManutencao ();
		
		@Query(value = "select c.id, c.prefixo, c.status,g.nome,"
				+ " g.cidade from cco.tb_coletivo as c inner join cco.tb_garagem as g on g.id = c.garagem_id"
				+ " WHERE c.status = 'parado' ", nativeQuery = true)
		List<RelatorioColetivo> findRelatorioColetivoParado ();
		
		
	
}
