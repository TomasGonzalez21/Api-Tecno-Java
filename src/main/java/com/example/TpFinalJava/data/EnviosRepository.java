package com.example.TpFinalJava.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TpFinalJava.model.Envios;

public interface EnviosRepository extends JpaRepository<Envios, Integer> {
	
	@Query(value="exec [dbo].[findEnviobyUsername] :idUsuario", nativeQuery = true)
	List<Envios> findEnviobyUsername(@Param("idUsuario") int idUsuario);
}
