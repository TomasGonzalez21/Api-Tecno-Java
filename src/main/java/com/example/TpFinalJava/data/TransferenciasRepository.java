package com.example.TpFinalJava.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TpFinalJava.model.Transferencias;

public interface TransferenciasRepository extends JpaRepository<Transferencias, Integer>{	
	
	
	@Query(value="exec [dbo].[ConcretarTransferencia] :CuentaEmisor, :MontoTransferencia , :CuentaReceptor", nativeQuery = true)
	void ConcretarTransferencia(@Param("CuentaEmisor") int CuentaEmisor,@Param("MontoTransferencia") float MontoTransferencia,@Param("CuentaReceptor") int CuentaReceptor);

	@Query(value="exec [dbo].[findTransferenciasUsuario] :idUsuario", nativeQuery = true)
	List<Transferencias> findTransferenciasUsuario(@Param("idUsuario") int idUsuario);
}
