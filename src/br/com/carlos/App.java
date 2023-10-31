package br.com.carlos;


import javax.swing.JOptionPane;

import br.com.carlos.dao.ClienteMapDAO;
import br.com.carlos.dao.IClienteDAO;
import br.com.carlos.domain.Cliente;

public class App {
	
	private static IClienteDAO iClienteDAO = new ClienteMapDAO();;

	public static void main(String[] args) {
		
		String opcao = JOptionPane.showInputDialog(null, "Digite 1 para cadastro, 2 para Consultar,"
				+ " 3 para exclusão, 4 para alteração ou para sair"
				, "Cadastro", JOptionPane.INFORMATION_MESSAGE);
		
		while(!opcaoValida(opcao)) {
			if ("".equals(opcao)) {
				sair();
			}
			opcao = JOptionPane.showInputDialog(null, "Digite 1 para cadastro, 2 para Consultar,"
					+ " 3 para exclusão, 4 para alteração ou para sair"
					, "Cadastro", JOptionPane.INFORMATION_MESSAGE);
		}
		
		while(opcaoValida(opcao)) {
			if(isSair(opcao)) {
				sair();
			}
			else if (isOpcaoCadastro(opcao)) {
				String dados = JOptionPane.showInputDialog(null,"Digite seus dados conforme o exemplo a seguir :"
						+ " Nome,CPF,Telefone,Endereço,Numero,Cidade,Estado",
						"Cadastro", JOptionPane.INFORMATION_MESSAGE);
				cadastrar(dados);
				main(null);
			}
			else if (isOpcaoConsulta(opcao)) {
				String cpf = JOptionPane.showInputDialog(null,"Informe o CPF de quem deseja consultar",
						"Consulta", JOptionPane.INFORMATION_MESSAGE);
				if(cpf == null) {
					main(null);
				}
				JOptionPane.showConfirmDialog(null, consultar(Long.parseLong(cpf)), "Consultar", JOptionPane.INFORMATION_MESSAGE);
				main(null);
			}
			else if (isOpcaoExcluir(opcao)) {
				String cpf = JOptionPane.showInputDialog(null,"Informe o CPF no qual deseja excluir","Exclusão",JOptionPane.INFORMATION_MESSAGE);
				exclusao(cpf);
				main(null);
			}
			else if (isOpcaoAlterar(opcao)) {
				alterar();
				main(null);
			}
		}
		
	}
	
	public static boolean alterar() {
		String dados = JOptionPane.showInputDialog(null,"Digite seus dados conforme o exemplo a seguir:"
				+ " Nome,CPF,Telefone,Endereço,Numero,Cidade,Estado",
				"Cadastro", JOptionPane.INFORMATION_MESSAGE);
		String[] dadosNovos = dados.split(",");
		Cliente clienteAntigo = iClienteDAO.consultar(Long.parseLong(dadosNovos[1].trim()));
		if(clienteAntigo != null) {
			iClienteDAO.alterar(new Cliente(dadosNovos[0], dadosNovos[1], dadosNovos[2], dadosNovos[3], dadosNovos[4], dadosNovos[5], dadosNovos[6]));
			JOptionPane.showConfirmDialog(null, "Conta alterada com sucesso");
			return true;
		}
		JOptionPane.showConfirmDialog(null, "Conta não encontrada");
		return false;
	}
	
	public static boolean exclusao(String cpf) {
		Long cpfExcluir = Long.valueOf(cpf.trim());
		if(iClienteDAO.consultar(cpfExcluir) != null) {
			iClienteDAO.excluir(cpfExcluir);
			JOptionPane.showConfirmDialog(null, "Conta com o cpf "+cpfExcluir + " excluido");
			return true;
		}
		JOptionPane.showConfirmDialog(null, "Não encontrada");
		return false;
	}
	
	public static boolean cadastrar (String dados) {
		String[] dadosS = dados.split(",");
		if(dadosS[1].isEmpty()) {
			return false;
		}
		iClienteDAO.cadastrar(new Cliente(dadosS[0], dadosS[1], dadosS[2], dadosS[3], dadosS[4], dadosS[5], dadosS[6]));
		return true;
	}
	
	public static String consultar (Long cpf) {
		Cliente clienteValido = iClienteDAO.consultar(cpf);
		if(clienteValido != null) {
			return clienteValido.toString();
		}
		return "Cliente não encontrado";
	}
	
	public static void sair() {
		System.exit(0);
	}
	
	public static boolean opcaoValida (String opcao) {
		if (Byte.parseByte(opcao) > 0 && Byte.parseByte(opcao) < 6) {
			return true;
		}
		return false;
	}
	
	public static boolean isOpcaoCadastro(String opcao) {
		if (opcao.equals("1")) {
			return true;
		}
		return false;
	}
	
	public static boolean isOpcaoConsulta(String opcao) {
		if (opcao.equals("2")) {
			return true;
		}
		return false;
	}
	
	public static boolean isOpcaoExcluir(String opcao) {
		if(opcao.equals("3")) {
			return true;
		}
		return false;
	}
	
	public static boolean isOpcaoAlterar(String opcao) {
		if(opcao.equals("4")) {
			return true;
		}
		return false;
	}
	
	public static boolean isSair(String opcao) {
		if(opcao.equals("5")) {
			return true;
		}
		return false;
	}
	
}
