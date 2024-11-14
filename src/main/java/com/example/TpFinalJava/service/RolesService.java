package com.example.TpFinalJava.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.TpFinalJava.data.RolesRepository;
import com.example.TpFinalJava.model.Roles;

@Service
public class RolesService {
	
	private final RolesRepository RolesRepository;
	
	public RolesService(RolesRepository RolesRepository) {
		this.RolesRepository = RolesRepository;
	}
	

	@Transactional(readOnly = true)
	public List<Roles> getRoles(){
		return RolesRepository.findAll();
	}
	
	@Transactional
	public Roles guardarRol(Roles Roles){
		
		return RolesRepository.saveAndFlush(Roles);

	}

	@Transactional
	public void eliminarRol(int rolID) {
		RolesRepository.deleteById(rolID);
	}
}
