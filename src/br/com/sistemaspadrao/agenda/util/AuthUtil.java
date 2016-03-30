package br.com.sistemaspadrao.agenda.util;

import java.sql.Connection;
import java.sql.ResultSet;
import br.com.sistemaspadrao.agenda.conf.Conexao;

/**
 *
 * @author Rafael Lucio
 */
public class AuthUtil {

    private final Connection connection;
    private ResultSet rs;
    private int qtd_registros;
    private boolean resultado;

    public AuthUtil(Connection con) {
        this.connection = con;
    }

    public  boolean Autenticar(String usuario, String senha){
        String SQL_USUARIO_SENHA = "SELECT COUNT(*) as qtd_registros FROM usuarios WHERE usuario='" + usuario + "' and senha=MD5('" + senha + "')";
        try {
            rs = connection.createStatement().executeQuery(SQL_USUARIO_SENHA);
            rs.next();
            qtd_registros = rs.getInt("qtd_registros");
            if (qtd_registros == 1) {
                resultado = true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return resultado;
    }
    
}
