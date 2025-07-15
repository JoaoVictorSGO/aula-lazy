package com.devsuperior.aulalazy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.aulalazy.dto.EmployeeDepartmentDTO;
import com.devsuperior.aulalazy.dto.EmployeeMinDTO;
import com.devsuperior.aulalazy.services.EmployeeService;

// Controlador responsável por expor os endpoints relacionados aos funcionários (employees).
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

	// Injeto o serviço que lida com a lógica de negócio dos funcionários
	@Autowired
	private EmployeeService service;
	
	// Endpoint que retorna apenas os dados mínimos de um funcionário, buscando pelo ID
	@GetMapping(value = "/{id}/min")
	public ResponseEntity<EmployeeMinDTO> findByIdMin(@PathVariable Long id) {
		EmployeeMinDTO obj = service.findByIdMin(id);		
		return ResponseEntity.ok(obj);
	}

	// Endpoint que retorna os dados do funcionário junto com o departamento (DTO customizado)
	@GetMapping(value = "/{id}")
	public ResponseEntity<EmployeeDepartmentDTO> findByIdWithDepartment(@PathVariable Long id) {
		EmployeeDepartmentDTO obj = service.findByIdWithDepartment(id);		
		return ResponseEntity.ok(obj);
	}
	
	// Endpoint comentado, mas que retorna a lista de todos os funcionários com seus departamentos.
	// Descomentar se quiser listar geral (bom pra testes de performance ou verificar joins)
	//@GetMapping
	public ResponseEntity<List<EmployeeDepartmentDTO>> findEmployeesWithDepartments() {
		List<EmployeeDepartmentDTO> list = service.findEmployeesWithDepartments();		
		return ResponseEntity.ok(list);
	}
	
	// Endpoint que busca funcionários pelo nome (usando RequestParam na URL)
	// Ex: /employees?name=João
	@GetMapping
	public ResponseEntity<List<EmployeeMinDTO>> findByName(@RequestParam String name) {
		List<EmployeeMinDTO> list = service.findByName(name);		
		return ResponseEntity.ok(list);
	}
}
