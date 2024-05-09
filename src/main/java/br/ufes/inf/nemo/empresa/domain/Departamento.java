package br.ufes.inf.nemo.empresa.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Departamento {
	private String nome;
	private long id;

	private Map<Long,Funcionario> funcionarios = new HashMap<>();

	public Collection<Funcionario> getFuncionarios() {
		return funcionarios.values();
	}
	
	public void putFuncionario(Long key, Funcionario value)
	{
		funcionarios.put(key, value);
	}
	
	public Funcionario getFuncionario(Long key)
	{
		return funcionarios.get(key);
	}
	
	public void remove(Long key)
	{
		funcionarios.remove(key);
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
