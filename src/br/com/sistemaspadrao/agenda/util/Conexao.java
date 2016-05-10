package br.com.sistemaspadrao.agenda.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael Da Silva lucio | rafaellucio.developer@gmail.com
 */
public class Conexao {

    public Connection abrirConexao() {
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

    public static void main(String[] args) {
        new Conexao().abrirConexao();

    }

}
