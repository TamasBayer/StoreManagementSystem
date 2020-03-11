package view.StockPanelStockS;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class StockTableStockS extends JPanel{

	private JTable StockTable;
	private TableRowSorter<DefaultTableModel> rowSorter;
	String[] colNames = {"Stock code", "Goods-ID", "Goods-Name", "Quantity in Stock"};
	
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
    
    public StockTableStockS(){
    	StockTable = new JTable(model);
    	rowSorter = new TableRowSorter<>(model);
    	StockTable.setRowSorter(rowSorter);
    	
        setLayout(new BorderLayout());
        
        add(new JScrollPane(StockTable), BorderLayout.CENTER);
    }
    
    public TableRowSorter<DefaultTableModel> getRowSorter() {
		return rowSorter;
	}
	
	public DefaultTableModel getModel() {
		return model;
	}
    
}
