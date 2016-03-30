package br.com.sistemaspadrao.agenda.tabelas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rafael Da Silva lucio | rafaellucio.developer@gmail.com
 */
public class TabelaClientes extends AbstractTableModel {

    private static final String[] colNomes = {
        "Código", "Nome", "Telefone"
    };

    private ArrayList<String[]> ResultSets;

    public TabelaClientes(ResultSet rs) {
        try {
            setResult(rs);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public int getRowCount() {
        return ResultSets.size();
    }

    @Override
    public int getColumnCount() {
        return colNomes.length;
    }

    @Override
    public String getColumnName(int param) {
        return colNomes[param];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] row = ResultSets.get(rowIndex);
        return row[columnIndex];
    }

    public void setResult(ResultSet rs) throws SQLException {

        ResultSets = new ArrayList<String[]>();

        while (rs.next()) {
            String[] row = {
                rs.getString("codigo"),
                rs.getString("nome"),
                rs.getString("telefone")
            };
            ResultSets.add(row);
        }

        fireTableStructureChanged();
    }

    public void deleteRow(int row) {
        ResultSets.remove(row);
        fireTableRowsDeleted(row, row);
    }

}