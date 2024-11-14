package com.example.TpFinalJava.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TpFinalJava.model.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer>{
	
	
	
	@Query(value="exec [dbo].[ExisteUsuario] :Username, :Password", nativeQuery = true)
	Usuarios ExisteUsuario(@Param("Username") String username,@Param("Password") String password);
	
	@Query(value="exec [dbo].[userValidation] :Username", nativeQuery = true)
	Optional<Usuarios> userValidation(@Param("Username") String username);

}
