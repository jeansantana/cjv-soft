package controllers;

import play.mvc.Controller;

/**
 * 
 * @author jeansilva
 * 
 * Esta classe Provê funções de gerência para a área administrativa do sistema.
 * Essas funções são relativas a gerência de Funcionário operadores do
 * sistema.
 */

public class ControllerAdmin extends Controller {
	//Controller para gerenciar os funcionarios admins;
	/**
	 * Função de cadastro de conta de funcionário operador do sistema
	 */
	public void criarContaUsuario() {
		render();
	}
	/**
	 * Função de gerenciamento de funcionários admins (criação, exlcusão, ver desempenho) - não está 
	 * dentro do prazo para ser desenvolvido
	 */
	//usar jQuery para fazer uma tela de gerenciamento com funções de excluir e adicionar (esta chama a página acima)
	public void gerenciamentoAdmins() {
		render();
	}
	
}
