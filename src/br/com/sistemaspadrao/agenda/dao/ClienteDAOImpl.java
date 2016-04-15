package br.com.sistemaspadrao.agenda.dao;

import br.com.sistemaspadrao.agenda.util.Conexao;
import br.com.sistemaspadrao.agenda.modelos.Cliente;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Rafael Da Silva lucio | rafaellucio.developer@gmail.com
 */
public class ClienteDAOImpl implements ClienteDAO {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    private Cliente cliente;

    public ClienteDAOImpl() {
        connection = new Conexao().abrirConexao();
    }

    @Override
    public boolean salvarCliente(Cliente cliente) {
        boolean retorno = false;
        String SQL_NOVO = "INSERT INTO clientes (nome,endereco,email,telefone,observacoes) VALUES (?,?,?,?,?)";
        String SQL_ATUALIZAR = "UPDATE clientes SET nome=?,endereco=?,email=?,telefone=?,observacoes=? WHERE codigo=?";
        if (cliente.getCodigo() == 0) {
            try {
                preparedStatement = connection.prepareStatement(SQL_NOVO);
                preparedStatement.setString(1, cliente.getNome());
                preparedStatement.setString(2, cliente.getEndereco());
                preparedStatement.setString(3, cliente.getEmail());
                preparedStatement.setString(4, cliente.getTelefone());
                preparedStatement.setString(5, cliente.getObservacoes());
                preparedStatement.executeUpdate();
                preparedStatement.close();
                retorno = true;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            try {
                preparedStatement = connection.prepareStatement(SQL_ATUALIZAR);
                preparedStatement.setString(1, cliente.getNome());
                preparedStatement.setString(2, cliente.getEndereco());
                preparedStatement.setString(3, cliente.getEmail());
                preparedStatement.setString(4, cliente.getTelefone());
                preparedStatement.setString(5, cliente.getObservacoes());
                preparedStatement.setInt(6, cliente.getCodigo());
                preparedStatement.executeUpdate();
                preparedStatement.close();
                retorno = true;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());

            }

        }

        return retorno;
    }

    @Override
    public boolean excluirCliente(Long codigo) {
        boolean retorno = false;
        String SQL_EXCLUIR = "DELETE FROM clientes WHERE codigo=?";
        try {
            preparedStatement = connection.prepareStatement(SQL_EXCLUIR);
            preparedStatement.setLong(1, codigo);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            retorno = true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }

    @Override
    public Cliente selecionarCliente(Long codigo) {
        cliente = new Cliente();
        String SQL_SELECT_CLIENTES = "SELECT * FROM clientes WHERE codigo=" + codigo;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_CLIENTES);
            while (resultSet.next()) {
                cliente.setCodigo(resultSet.getInt("codigo"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setEndereco(resultSet.getString("endereco"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setTelefone(resultSet.getString("telefone"));
                cliente.setObservacoes(resultSet.getString("observacoes"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cliente;
    }

    @Override
    public List<Cliente> listarClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String SQL_SELECT_ALL = "SELECT * FROM clientes";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                cliente = new Cliente();
                cliente.setCodigo(resultSet.getInt("codigo"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setEndereco(resultSet.getString("endereco"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setTelefone(resultSet.getString("telefone"));
                cliente.setObservacoes(resultSet.getString("observacoes"));
                clientes.add(cliente);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return clientes;
    }

    @Override
    public ResultSet listarTabelaClientes(String nome) {
        String SELECT = "SELECT * FROM clientes WHERE nome like '%" + nome + "%'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return resultSet;
    }

}
