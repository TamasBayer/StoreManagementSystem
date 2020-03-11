package view.ordersPanelMS;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class OrdersTable extends JPanel{
	
	private JTable ordersTable;
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
    
    public OrdersTable(){
    	ordersTable = new JTable(model);
        rowSorter = new TableRowSorter<>(model);
        ordersTable.setRowSorter(rowSorter);
        
        setLayout(new BorderLayout());
        
        add(new JScrollPane(ordersTable), BorderLayout.CENTER);
    }
    
    public TableRowSorter<DefaultTableModel> getRowSorter() {
		return rowSorter;
	}

	public DefaultTableModel getModel() {
		return model;
	}

}
