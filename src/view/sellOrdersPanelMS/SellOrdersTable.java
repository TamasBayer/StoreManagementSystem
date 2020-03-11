package view.sellOrdersPanelMS;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class SellOrdersTable extends JPanel{

	private JTable sellOrdersTable;
	private TableRowSorter<DefaultTableModel> rowSorter;
	private String[] colNames = {"Sell order-ID", "Company name", "Order datum"};
	
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
    
    public SellOrdersTable(){
    	sellOrdersTable = new JTable(model);
        rowSorter = new TableRowSorter<>(model);
        sellOrdersTable.setRowSorter(rowSorter);
        
        setLayout(new BorderLayout());
        
        add(new JScrollPane(sellOrdersTable), BorderLayout.CENTER);
    }
    
    public TableRowSorter<DefaultTableModel> getRowSorter() {
		return rowSorter;
	}

	public DefaultTableModel getModel() {
		return model;
	}
}
