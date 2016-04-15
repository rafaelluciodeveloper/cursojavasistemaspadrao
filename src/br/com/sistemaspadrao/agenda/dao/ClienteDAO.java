package br.com.sistemaspadrao.agenda.dao;

import br.com.sistemaspadrao.agenda.modelos.Cliente;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Rafael Da Silva lucio | rafaellucio.developer@gmail.com
 */
public interface ClienteDAO {
    
    public boolean salvarCliente(Cliente cliente);

    public boolean excluirCliente(Long codigo);

    public List<Cliente> listarClientes();

    public Object selecionarCliente(Long codigo);
    
    public ResultSet listarTabelaClientes(String nome);

}
