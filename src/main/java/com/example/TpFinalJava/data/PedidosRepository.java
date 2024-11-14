package com.example.TpFinalJava.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TpFinalJava.model.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos, Integer> {

	@Query(value="exec [dbo].[findPedidobyUsername] :idUsuario", nativeQuery = true)
	List<Pedidos> findPedidobyUsername(@Param("idUsuario") int idUsuario);
}
