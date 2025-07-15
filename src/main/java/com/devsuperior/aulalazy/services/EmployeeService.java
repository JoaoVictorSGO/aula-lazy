package com.devsuperior.aulalazy.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.aulalazy.dto.EmployeeDepartmentDTO;
import com.devsuperior.aulalazy.dto.EmployeeMinDTO;
import com.devsuperior.aulalazy.entities.Employee;
import com.devsuperior.aulalazy.repositories.EmployeeRepository;

// Classe de serviço responsável pela lógica de negócio relacionada a Employee.
// Aqui é onde ficam as regras e chamadas ao repositório (camada de acesso a dados).
@Service
public class EmployeeService {

	// Injeção do repositório que acessa os dados dos funcionários
	@Autowired
	private EmployeeRepository repository;

	// Busca um funcionário pelo ID e retorna um DTO com dados mínimos (id e nome)
	@Transactional(readOnly = true)
	public EmployeeMinDTO findByIdMin(Long id) {
		Optional<Employee> result = repository.findById(id);
		return new EmployeeMinDTO(result.get());
	}

	// Busca um funcionário pelo ID e retorna um DTO com os dados + informações do departamento
	@Transactional(readOnly = true)
	public EmployeeDepartmentDTO findByIdWithDepartment(Long id) {
		Optional<Employee> result = repository.findById(id);
		return new EmployeeDepartmentDTO(result.get());
	}
	
	// Retorna uma lista de funcionários com seus departamentos (join + mapeamento para DTO)
	@Transactional(readOnly = true)
	public List<EmployeeDepartmentDTO> findEmployeesWithDepartments() {
		List<Employee> result = repository.findEmployeesWithDepartments();
		return result.stream()
				.map(x -> new EmployeeDepartmentDTO(x))
				.collect(Collectors.toList());
	}
	
	// Busca funcionários pelo nome (sem diferenciar maiúsculas e minúsculas)
	// Utiliza o método customizado definido no repositório com LIKE e ignoreCase
	@Transactional(readOnly = true)
	public List<EmployeeMinDTO> findByName(String name) {
		List<Employee> result = repository.findByNameContainingIgnoreCase(name);
		return result.stream()
				.map(x -> new EmployeeMinDTO(x))
				.collect(Collectors.toList());
	}	
}
