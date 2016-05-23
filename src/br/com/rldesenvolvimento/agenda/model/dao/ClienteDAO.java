package br.com.rldesenvolvimento.agenda.model.dao;

import br.com.rldesenvolvimento.agenda.model.Cliente;
import java.util.List;

/**
 *
 * @author Rafael Da Silva lucio | rafaellucio.developer@gmail.com
 */
public interface ClienteDAO {

    public void cadastrarCliente(Cliente cliente);

    public void alterarCliente(Cliente cliente);

    public void excluirCliente(int codigo);

    public List<Cliente> listarClientes(String nome);

    

}
