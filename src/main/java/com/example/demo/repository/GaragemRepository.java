package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Garagem;
import com.google.common.base.Optional;

@Repository
public interface GaragemRepository extends JpaRepository<Garagem, Long> {
	
			Optional<Garagem> findByNome(String nome);
												
}
