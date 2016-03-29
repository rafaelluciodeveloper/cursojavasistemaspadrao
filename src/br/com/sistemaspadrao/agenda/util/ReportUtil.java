package br.com.sistemaspadrao.agenda.util;

import br.com.sistemaspadrao.agenda.conf.Conexao;
import java.util.HashMap;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Rafael Da Silva lucio | rafaellucio.developer@gmail.com
 */
public class ReportUtil {

    public void imprimir(String relatorio, HashMap parametros, String titulo_relatorio) {
        JasperPrint relat;
        try {
            JDialog viewer = new JDialog(new javax.swing.JFrame(), titulo_relatorio, true);
            viewer.setSize(900, 600);
            viewer.setLocationRelativeTo(null);
            relat = gerar(relatorio, parametros);
            JasperViewer jrViewer = new JasperViewer(relat, true);
            viewer.getContentPane().add(jrViewer.getContentPane());
            viewer.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public JasperPrint gerar(String arquivoJasper, HashMap parametros) {
        JasperPrint rel = null;
        try {
            HashMap map = parametros;
            map.putAll(parametros);
            rel = JasperFillManager.fillReport(getClass().getResourceAsStream(arquivoJasper), map, new Conexao().abrirConexao());
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return rel;
    }
}
