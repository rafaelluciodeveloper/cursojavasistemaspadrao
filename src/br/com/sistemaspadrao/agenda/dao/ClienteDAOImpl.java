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

    private Connection conexao;

    @Override
    public boolean cadastrarCliente(Cliente cliente) {
        String SQL_NOVO = "INSERT INTO clientes (nome,endereco,email,telefone,observacoes) VALUES (?,?,?,?,?)";
        boolean retorno = false;
        try {
            conexao = new Conexao().abrirConexao();
            PreparedStatement preparedStatement;
            preparedStatement = conexao.prepareStatement(SQL_NOVO);
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getEndereco());
            preparedStatement.setString(3, cliente.getEmail());
            preparedStatement.setString(4, cliente.getTelefone());
            preparedStatement.setString(5, cliente.getObservacoes());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            conexao.close();
            retorno = true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }

    @Override
    public boolean alterarCliente(Cliente cliente) {
        String SQL_ATUALIZAR = "UPDATE clientes SET nome=?,endereco=?,email=?,telefone=?,observacoes=? WHERE codigo=?";
        boolean retorno = false;
        try {
            conexao = new Conexao().abrirConexao();
            PreparedStatement preparedStatement;
            preparedStatement = conexao.prepareStatement(SQL_ATUALIZAR);
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getEndereco());
            preparedStatement.setString(3, cliente.getEmail());
            preparedStatement.setString(4, cliente.getTelefone());
            preparedStatement.setString(5, cliente.getObservacoes());
            preparedStatement.setInt(6, cliente.getCodigo());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            conexao.close();
            retorno = true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return retorno;
    }

    @Override
    public boolean excluirCliente(Long codigo) {
        String SQL_EXCLUIR = "DELETE FROM clientes WHERE codigo=?";
        boolean retorno = false;
        try {
            conexao = new Conexao().abrirConexao();
            PreparedStatement preparedStatement;
            preparedStatement = conexao.prepareStatement(SQL_EXCLUIR);
            preparedStatement.setLong(1, codigo);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            conexao.close();
            retorno = true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }

    @Override
    public List<Cliente> listarClientes(String nome) {
        String SQL_LISTAR = "SELECT * FROM clientes WHERE nome LIKE"+"'%"+nome+"%'";
        ArrayList<Cliente> clientes = new ArrayList<>();
        Cliente cliente;
        try {
            conexao = new Conexao().abrirConexao();
            Statement statement;
            ResultSet resultSet;
            statement = conexao.createStatement();
            resultSet = statement.executeQuery(SQL_LISTAR);
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
            conexao.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return clientes;
    }

}
