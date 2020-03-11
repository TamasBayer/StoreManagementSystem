package view.readyOrdersPanelMS;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ReadyOrdersTable extends JPanel{

	private JTable readyOrdersTable;
	private TableRowSorter<DefaultTableModel> rowSorter;
	private String[] colNames = {"Order-ID", "Company name", "Order datum"};
	
	private DefaultTableModel model = new DefaultTableModel(){
	    
        
	   @Override
	   public String getColumnName(int column) {
	       return colNames[column];
	   }
	
	   @Override
	   public int getColumnCount() {
	       return colNames.length;
	   }
	   
	   @Override
	    public boolean isCellEditable(int row, int column) {
	       
	       return false;
	    }
   
    };
    
    public ReadyOrdersTable(){
    	readyOrdersTable = new JTable(model);
        rowSorter = new TableRowSorter<>(model);
        readyOrdersTable.setRowSorter(rowSorter);
        
        setLayout(new BorderLayout());
        
        add(new JScrollPane(readyOrdersTable), BorderLayout.CENTER);
    }
    
    public TableRowSorter<DefaultTableModel> getRowSorter() {
		return rowSorter;
	}

	public DefaultTableModel getModel() {
		return model;
	}
}
