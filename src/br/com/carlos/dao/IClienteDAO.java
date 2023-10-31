package br.com.carlos.dao;

import java.util.Collection;

import br.com.carlos.domain.Cliente;

public interface IClienteDAO {

	public boolean cadastrar(Cliente cliente);
	
	public void excluir(Long cpf);
	
	public void alterar(Cliente cliente);
	
	public Cliente consultar(Long cpf);
	
	public Collection<Cliente> buscarTodos();
	
}
