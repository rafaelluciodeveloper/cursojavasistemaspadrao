package br.com.rldesenvolvimento.agenda.model.dao;

import br.com.rldesenvolvimento.agenda.util.FabricaConexao;
import br.com.rldesenvolvimento.agenda.model.Cliente;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Rafael Da Silva lucio | rafaellucio.developer@gmail.com
 */
public class ClienteDAOImpl implements ClienteDAO {

    private Connection conexao;

    @Override
    public void cadastrarCliente(Cliente cliente) {
        String SQL_NOVO = "INSERT INTO clientes (nome,endereco,email,telefone,observacoes) VALUES (?,?,?,?,?)";
        boolean retorno = false;
        try {
            conexao = FabricaConexao.abrirConexao();
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_NOVO);
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getEndereco());
            preparedStatement.setString(3, cliente.getEmail());
            preparedStatement.setString(4, cliente.getTelefone());
            preparedStatement.setString(5, cliente.getObservacoes());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            retorno = true;
        } catch (SQLException ex) {
            System.out.println("Não Foi possivel Inserir Cliente" + ex.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao);
        }
    }

    @Override
    public void alterarCliente(Cliente cliente) {
        String SQL_ATUALIZAR = "UPDATE clientes SET nome=?,endereco=?,email=?,telefone=?,observacoes=? WHERE codigo=?";
        try {
            conexao = FabricaConexao.abrirConexao();
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_ATUALIZAR);
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getEndereco());
            preparedStatement.setString(3, cliente.getEmail());
            preparedStatement.setString(4, cliente.getTelefone());
            preparedStatement.setString(5, cliente.getObservacoes());
            preparedStatement.setInt(6, cliente.getCodigo());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println(" Não Foi Possivel Alterar Cliente " + ex.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao);
        }
    }

    @Override
    public void excluirCliente(int codigo) {
        String SQL_EXCLUIR = "DELETE FROM clientes WHERE codigo=?";
        try {
            conexao = FabricaConexao.abrirConexao();
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_EXCLUIR);;
            preparedStatement.setInt(1, codigo);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println("Não Foi Possivel Excluir Cliente " + ex.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao);
        }
    }

    @Override
    public List<Cliente> listarClientes(String nome) {
        String SQL_LISTAR = "SELECT * FROM clientes WHERE nome LIKE '%' ? '%'";
        List<Cliente> clientes = new ArrayList<>();
        try {
            conexao = FabricaConexao.abrirConexao();
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_LISTAR);
            preparedStatement.setString(1, nome);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodigo(resultSet.getInt("codigo"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setEndereco(resultSet.getString("endereco"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setTelefone(resultSet.getString("telefone"));
                cliente.setObservacoes(resultSet.getString("observacoes"));
                clientes.add(cliente);
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao);
        }
        return clientes;
    }

}
