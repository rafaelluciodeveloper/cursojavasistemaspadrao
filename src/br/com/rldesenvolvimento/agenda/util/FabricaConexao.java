package br.com.rldesenvolvimento.agenda.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael Da Silva lucio | rafaellucio.developer@gmail.com
 */
public class FabricaConexao {

    public static Connection abrirConexao() {
        Connection conexao = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/agendatelefonica", "root", "");
            System.out.println("Conexao Efetuada com Sucesso");
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return conexao;
    }

    public static void fecharConexao(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Não Possivel Fechar Conexao " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        FabricaConexao.abrirConexao();
    }

}
