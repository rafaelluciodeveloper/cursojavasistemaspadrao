package br.com.sistemaspadrao.agenda.dao;

import br.com.sistemaspadrao.agenda.modelos.Cliente;
import java.util.List;

/**
 *
 * @author Rafael Da Silva lucio | rafaellucio.developer@gmail.com
 */
public interface ClienteDAO {

    public boolean cadastrarCliente(Cliente cliente);

    public boolean alterarCliente(Cliente cliente);

    public boolean excluirCliente(Long codigo);

    public List<Cliente> listarClientes(String nome);



}
