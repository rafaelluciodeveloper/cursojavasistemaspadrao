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

    private Connection connection = null;

    public Connection abrirConexao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agendatelefonica","root","");
            System.out.println("Conexao Efetuada com Sucesso");
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return connection;
    }

    public void fecharConexao(Connection connection) {
        try {
            connection.close();
            System.out.println("Conexão Fechada");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public static void main(String[] args) {
        Connection con = new Conexao().abrirConexao();
        new Conexao().fecharConexao(con);

    }

}
