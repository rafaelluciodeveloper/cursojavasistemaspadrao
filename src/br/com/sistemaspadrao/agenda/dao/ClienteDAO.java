package br.com.sistemaspadrao.agenda.dao;

import br.com.sistemaspadrao.agenda.conf.Conexao;
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
public class ClienteDAO {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    private Cliente cliente;

    public ClienteDAO() {
        connection = new Conexao().abrirConexao();
    }

    public void salvar(Cliente cliente) {
        String SQL_NOVO = "INSERT INTO clientes (nome,endereco,email,telefone,observacoes) VALUES (?,?,?,?,?)";
        String SQL_ATUALIZAR = "UPDATES clientes SET nome=?,endereco=?,email=?,telefone=?,observacoes=? WHERE codigo=?";
        if (cliente.getCodigo() == 0) {
            try {
                preparedStatement = connection.prepareStatement(SQL_NOVO);
                preparedStatement.setString(1, cliente.getNome());
                preparedStatement.setString(2, cliente.getEndereco());
                preparedStatement.setString(3, cliente.getEmail());
                preparedStatement.setString(4, cliente.getTelefone());
                preparedStatement.setString(5, cliente.getObservacoes());
                preparedStatement.executeUpdate();
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
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());

            }

        }
    }

    public void excluir(Long codigo) {
        String SQL_EXCLUIR = "DELETE * FROM clientes WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(SQL_EXCLUIR);
            preparedStatement.setLong(1, codigo);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Cliente> listar() {
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
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return clientes;
    }

    public Cliente selecionar(Long codigo) {
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
    
    public ResultSet tabelaClientes(String nome) {
        String SELECT = "SELECT * FROM clientes WHERE nome like '%"+nome+"%'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return resultSet;
    }
}
