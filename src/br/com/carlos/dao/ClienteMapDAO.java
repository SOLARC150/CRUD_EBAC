package br.com.carlos.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.carlos.domain.Cliente;

public class ClienteMapDAO implements IClienteDAO{
	
	private Map<Long, Cliente> map;
	
	public ClienteMapDAO() {
		this.map = new HashMap<>();
	}

	@Override
	public boolean cadastrar(Cliente cliente) {
		if(this.map.containsKey(cliente.getCpf())) {
			return false;
		}
		this.map.put(cliente.getCpf(), cliente);
		return true;
	}

	@Override
	public void excluir(Long cpf) {
		Cliente clienteCadastrado = this.map.get(cpf);
		
		if(clienteCadastrado != null) {
			this.map.remove(clienteCadastrado.getCpf(), clienteCadastrado);
		}
	}

	@Override
	public void alterar(Cliente cliente) {
		Cliente clienteAntigo = this.map.get(cliente.getCpf());
		
		if(clienteAntigo != null) {
			clienteAntigo.setNome(cliente.getNome());
			clienteAntigo.setCpf(cliente.getCpf());
			clienteAntigo.setTel(cliente.getTel());
			clienteAntigo.setEnd(cliente.getEnd());
			clienteAntigo.setNumero(cliente.getNumero());
			clienteAntigo.setCidade(cliente.getCidade());
			clienteAntigo.setEstado(cliente.getEstado());
		}
		
	}

	@Override
	public Cliente consultar(Long cpf) {
		return this.map.get(cpf);
	}

	@Override
	public Collection<Cliente> buscarTodos() {
		return this.map.values();
	}

}
