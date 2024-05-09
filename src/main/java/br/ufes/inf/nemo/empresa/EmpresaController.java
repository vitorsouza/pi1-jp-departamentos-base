package br.ufes.inf.nemo.empresa;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.ufes.inf.nemo.empresa.domain.Departamento;
import br.ufes.inf.nemo.empresa.domain.Empresa;
import br.ufes.inf.nemo.empresa.domain.Funcionario;

/**
 * Controla a produção de json para o cliente.
 * 
 * Implementa as 4 operações HTTP básicas.
 * 
 * @author jpalmeida
 *
 */
@RestController
public class EmpresaController {

	public static Empresa empresa = new Empresa();
	private static long nextId = 0;

	@PostMapping("/departamentos")
	public Departamento postDepartamento(@RequestBody Departamento d) {
		d.setId(nextId);
		empresa.putDepartamento(nextId, d);
		nextId++;
		return d;
	}

	@GetMapping("/departamentos")
	public Collection<Departamento> getDepartamentos() {
		return empresa.getDepartamentos();
	}

	@GetMapping("/departamentos/{id}")
	public Departamento getDepartamento(@PathVariable(name = "id") long id) {
		if (empresa.getDepartamento(id) == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento não encontrado!");
		// veja opções para tratamento de erro em:
		// https://www.baeldung.com/spring-response-status-exception
		else
			return empresa.getDepartamento(id);
	}

	@PutMapping("/departamentos/{id}")
	public Departamento putDepartamento(@PathVariable(name = "id") long id, @RequestBody Departamento d) {
		if (empresa.getDepartamento(id) == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento não encontrado!");
		d.setId(id);
		empresa.putDepartamento(id, d);
		return d;
	}

	@DeleteMapping("/departamentos/{id}")
	public void deleteDepartamento(@PathVariable(name = "id") long id) {
		empresa.remove(id);
	}

	@PostMapping("/departamentos/{depId}/funcionarios")
	public Funcionario postFuncionario(
			@PathVariable(name = "depId") long depId,
			@RequestBody Funcionario f) {
		f.setId(nextId);
		// FIXME depID pode estar inválido
		empresa.getDepartamento(depId).putFuncionario(nextId, f);
		nextId++;
		return f;
	}

	@GetMapping("/departamentos/{depId}/funcionarios/{funcID}")
	public Funcionario getFuncionario(
			@PathVariable(name = "depId") long depId,
			@PathVariable(name = "funcID") long funcID) {
		if (empresa.getDepartamento(depId) == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento não encontrado!");
		else if (empresa.getDepartamento(depId).getFuncionario(funcID) == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado!");
		else
			return empresa.getDepartamento(depId).getFuncionario(funcID);
	}

	@GetMapping("/departamentos/{depId}/funcionarios")
	public Collection<Funcionario> getFuncionarios(
			@PathVariable(name = "depId") long depId) {
		if (empresa.getDepartamento(depId) == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento não encontrado!");
		else
			return empresa.getDepartamento(depId).getFuncionarios();
	}

	@PutMapping("/departamentos/{depId}/funcionarios/{funcID}")
	public Funcionario putFuncionario(
			@PathVariable(name = "depId") long depId,
			@PathVariable(name = "funcID") long funcID, @RequestBody Funcionario f) {
		if (empresa.getDepartamento(depId) == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento não encontrado!");
		else if (empresa.getDepartamento(depId).getFuncionario(funcID) == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado!");
		else {
			f.setId(funcID);
			empresa.getDepartamento(depId).putFuncionario(funcID, f);
			return f;
		}
	}

	@DeleteMapping("/departamentos/{depId}/funcionarios/{funcID}")
	public void deleteFuncionario(
			@PathVariable(name = "depId") long depId,
			@PathVariable(name = "funcID") long funcID) {
		if (empresa.getDepartamento(depId) == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento não encontrado!");
		else if (empresa.getDepartamento(depId).getFuncionario(funcID) == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado!");
		else
			empresa.getDepartamento(depId).remove(funcID);
	}

}
