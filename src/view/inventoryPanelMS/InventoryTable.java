package view.inventoryPanelMS;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class InventoryTable extends JPanel {

	private JTable inventoryTable;
	private TableRowSorter<DefaultTableModel> rowSorter;
	private String[] colNames = {"Goods-ID", "Goods-Name", "Quantity in warehouse", "Selling price"};
	
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
    
    public InventoryTable(){
        inventoryTable = new JTable(model);
        rowSorter = new TableRowSorter<>(model);
        inventoryTable.setRowSorter(rowSorter);
        
        setLayout(new BorderLayout());
        
        add(new JScrollPane(inventoryTable), BorderLayout.CENTER);
    }
    
    public TableRowSorter<DefaultTableModel> getRowSorter() {
		return rowSorter;
	}

	public DefaultTableModel getModel() {
		return model;
	}
}
