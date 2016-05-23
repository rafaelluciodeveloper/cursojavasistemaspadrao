package br.com.rldesenvolvimento.agenda.controller;

import br.com.rldesenvolvimento.agenda.model.Cliente;
import br.com.rldesenvolvimento.agenda.model.dao.ClienteDAOImpl;
import java.util.List;

/**
 *
 * @author Rafael Da Silva lucio | rafaellucio.developer@gmail.com
 */
public class ClienteController {

    public void salvar(String nome, String endereco, String email, String telefone, String observacoes) {

        Cliente cliente = new Cliente();

        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setObservacoes(observacoes);

        new ClienteDAOImpl().cadastrarCliente(cliente);

    }

    public void alterar(int codigo, String nome, String endereco, String email, String telefone, String observacoes) {

        Cliente cliente = new Cliente();

        cliente.setCodigo(codigo);
        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setObservacoes(observacoes);

        new ClienteDAOImpl().alterarCliente(cliente);

    }

    public void excluir(int codigo) {
        new ClienteDAOImpl().excluirCliente(codigo);
    }

    public List<Cliente> lista(String nome) {
        List<Cliente> listaClientes = new ClienteDAOImpl().listarClientes(nome);
        return listaClientes;
    }

}
