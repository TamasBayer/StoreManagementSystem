package view.classesForPanels;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Table extends JPanel{

	private JTable inventoryTable;
	private TableRowSorter<DefaultTableModel> rowSorter;
	private DefaultTableModel model;
	
    
    public Table(String[] colNames){
    	
    	 model = new DefaultTableModel(){
         
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
	
	public void fillWithData() {
		model.insertRow(0,new Object[]{"H1322","Apple", "50"});
		model.insertRow(1,new Object[]{"H1323","Bread", "213"});
		model.insertRow(2,new Object[]{"H1324","Strawberry", "32"});
		model.insertRow(3,new Object[]{"H1325","Salat", "43"});
		model.insertRow(4,new Object[]{"H1326","Retek", "2111"});
	}
}
