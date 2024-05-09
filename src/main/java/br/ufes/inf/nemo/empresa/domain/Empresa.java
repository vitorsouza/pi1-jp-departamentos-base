package br.ufes.inf.nemo.empresa.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Empresa {
	private Map<Long,Departamento> departamentos = new HashMap<>();

	public Collection<Departamento> getDepartamentos() {
		return departamentos.values();
	}
	
	public void putDepartamento(Long key, Departamento value)
	{
		departamentos.put(key, value);
	}
	
	public Departamento getDepartamento(Long key)
	{
		return departamentos.get(key);
	}
	
	public void remove(Long key)
	{
		departamentos.remove(key);
	}
	

}
