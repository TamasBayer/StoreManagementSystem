package view.classesForPanels;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import model.Goods;
import model.Orders;
import model.StockNames;

public class Table extends JPanel{

	private JTable table;
	private TableRowSorter<DefaultTableModel> rowSorter;
	private DefaultTableModel model;
	JFrame frame = null;
	
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
   		   public boolean isCellEditable(int row, int col) {
   	   
   		       return false;
   	    }
   	    
   	 };
   	
   	table = new JTable(model);
       rowSorter = new TableRowSorter<>(model);
       table.setRowSorter(rowSorter);
       
       setLayout(new BorderLayout());
       
       add(new JScrollPane(table), BorderLayout.CENTER);
       
       
       differentCursor("");
      
   }
    
    public Table(String[] colNames, String panelNames){
    	
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
   		   public boolean isCellEditable(int row, int col) {
   		       switch(panelNames) {
   		       	case "OrdersInfoPanel":
   		       		switch (col) {
   		        	case 4:
   		        	case 5:
   		                return true;
   		            default:
   		                return false;
   		         }
   		       	case "AddItemOrOrderPanel":
   		       		switch (col) {
   		        	case 0:
   		        	case 2:
   		                return true;
   		            default:
   		                return false;
   		       	}
   		       	case "StockInOutPanel":
		       		switch (col) {
		        	case 0:
		        	case 1:
		        	case 3:
		        	case 4:
		                return true;
		            default:
		                return false;
		       	}
   		       
   		   }
   	   
   		       return false;
   	    }
   	    
   	 };
   	
   	table = new JTable(model);
       rowSorter = new TableRowSorter<>(model);
       table.setRowSorter(rowSorter);
       
       setLayout(new BorderLayout());
       
       add(new JScrollPane(table), BorderLayout.CENTER);
       
       
       differentCursor(panelNames);
      
   }
    
    public void comboBoxColumnStockNames(ArrayList<StockNames> stockNames) {
    	TableColumn lastColumn = table.getColumnModel().getColumn(5);
    	
    	JComboBox comboBox = new JComboBox();
    	comboBox.addItem("");
    	for(int i=0; i < stockNames.size(); i++) {
    		comboBox.addItem(stockNames.get(i).getStockName());
    	}
    	
    	
    	lastColumn.setCellEditor(new DefaultCellEditor(comboBox));
    }
    
    
    public void comboBoxColumnItemID(ArrayList<Goods> goods) {
    	TableColumn firstColumn = table.getColumnModel().getColumn(0);
    	
    	JComboBox comboBox = new JComboBox();
    	comboBox.addItem("");
    	for(int i=0; i < goods.size(); i++) {
    		comboBox.addItem(goods.get(i).getItemID());
    	}
    	
    	
    	firstColumn.setCellEditor(new DefaultCellEditor(comboBox));
    }
    
    public void comboBoxColumnItemID() {
    	TableColumn firstColumn = table.getColumnModel().getColumn(0);
    	
    	JComboBox comboBox = new JComboBox();
    	comboBox.addItem("");
    	comboBox.addItem("1005");
    	comboBox.addItem("1323");
    	comboBox.addItem("1324");
    	comboBox.addItem("1325");
    	comboBox.addItem("1326");
    	firstColumn.setCellEditor(new DefaultCellEditor(comboBox));
    }
    
    public void comboBoxColumnInOutPanel(ArrayList<Goods> goods, ArrayList<StockNames> stocks) {
    	TableColumn firstColumn = table.getColumnModel().getColumn(0);

    	
    	JComboBox comboBoxInOut = new JComboBox();
    	comboBoxInOut.addItem("");
    	comboBoxInOut.addItem("In");
    	comboBoxInOut.addItem("Out");
    	firstColumn.setCellEditor(new DefaultCellEditor(comboBoxInOut));
    	
    	TableColumn secondColumn = table.getColumnModel().getColumn(1);
    	
    	JComboBox comboBoxItemName = new JComboBox();

    	comboBoxItemName.addItem("");
    	for(int i=0; i < goods.size(); i++) {
    		comboBoxItemName.addItem(goods.get(i).getItemID());
    	}
    	secondColumn.setCellEditor(new DefaultCellEditor(comboBoxItemName));
    	
    	TableColumn lastColumn = table.getColumnModel().getColumn(4);
    	
    	JComboBox comboBoxStockNames = new JComboBox();
    	
    	comboBoxStockNames.addItem("");
    	for(int i=0; i < stocks.size(); i++) {
    		comboBoxStockNames.addItem(stocks.get(i).getStockName());
    	}
    	lastColumn.setCellEditor(new DefaultCellEditor(comboBoxStockNames));
    	
    }
    

	public void fillWithData() {
		model.insertRow(0,new Object[]{"1322","Apple", "50"});
		model.insertRow(1,new Object[]{"1323","Bread", "213"});
		model.insertRow(2,new Object[]{"1324","Strawberry", "32"});
		model.insertRow(3,new Object[]{"1325","Salat", "43"});
		model.insertRow(4,new Object[]{"1326","Retek", "2111"});
	
		
		
	}
	
	public void fillWithEmptyRows() {
		
		for (int i = model.getRowCount() - 1; i > -1; i--) {
			model.removeRow(i);
	     }
		
		model.insertRow(0,new Object[]{"","",""});
		model.insertRow(1,new Object[]{"","",""});
		model.insertRow(2,new Object[]{"","",""});
		model.insertRow(3,new Object[]{"","",""});
		model.insertRow(4,new Object[]{"","",""});
		model.insertRow(5,new Object[]{"","",""});
		model.insertRow(6,new Object[]{"","",""});
		model.insertRow(7,new Object[]{"","",""});
		model.insertRow(8,new Object[]{"","",""});
		model.insertRow(9,new Object[]{"","",""});
		model.insertRow(10,new Object[]{"","",""});
		model.insertRow(11,new Object[]{"","",""});
		model.insertRow(12,new Object[]{"","",""});
		model.insertRow(13,new Object[]{"","",""});
		model.insertRow(14,new Object[]{"","",""});
		model.insertRow(15,new Object[]{"","",""});
		model.insertRow(16,new Object[]{"","",""});
		model.insertRow(17,new Object[]{"","",""});
		model.insertRow(18,new Object[]{"","",""});
		model.insertRow(19,new Object[]{"","",""});
		model.insertRow(20,new Object[]{"","",""});
		model.insertRow(21,new Object[]{"","",""});
		model.insertRow(22,new Object[]{"","",""});
		model.insertRow(23,new Object[]{"","",""});
		model.insertRow(24,new Object[]{"","",""});
		model.insertRow(25,new Object[]{"","",""});
		model.insertRow(26,new Object[]{"","",""});
		model.insertRow(27,new Object[]{"","",""});
		model.insertRow(28,new Object[]{"","",""});
		model.insertRow(29,new Object[]{"","",""});
		model.insertRow(30,new Object[]{"","",""});
	}
	
	public void newInformationFrameIfClicked(JPanel infoPanel) {
		table.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	           if (e.getClickCount() == 2) {

	        	   
	              
	              if (frame == null) {
		        	   
		              frame = new JFrame();
		              
		              frame.setLayout(new BorderLayout());

		              frame.add(infoPanel, BorderLayout.CENTER);
	            	  frame.setVisible(true);
	            	  frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
	            	  frame.setResizable(true);
	            	  frame.pack();
		              
		              Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		              Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
		              Point newLocation = new Point(middle.x - (frame.getWidth() / 2), 
		                                            middle.y - (frame.getHeight() / 2));
		              frame.setLocation(newLocation);
		              
		              frame.addWindowListener(new java.awt.event.WindowAdapter() {
		  			    @Override
		  			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		  			        frame = null;
		  			    }
		  			});
	  		    } else {
	  		    	frame.setVisible(true);
	  		    }
	           }
	        }
	     });
	}
	
	public void differentCursor(String panelNames){
    	table.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
            	Point pt = e.getPoint();
            	
            	if(panelNames == "OrdersInfoPanel") {
            		if(table.columnAtPoint(pt) > 3) {
            			Cursor cursor = new Cursor(Cursor.TEXT_CURSOR);
                		table.setCursor(cursor);
            		} else {
            			Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
                		table.setCursor(cursor);
            		}
            	} else if(panelNames == "StockInOutPanel") {
            		if(table.columnAtPoint(pt) > 2 && table.columnAtPoint(pt) < 4 ) {
            			Cursor cursor = new Cursor(Cursor.TEXT_CURSOR);
                		table.setCursor(cursor);
            		} else if(table.columnAtPoint(pt) < 2 || table.columnAtPoint(pt) == 4){
            			Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
                		table.setCursor(cursor);
            		} else {
            			Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
                		table.setCursor(cursor);
            		}
            	} else if(panelNames == "AddItemOrOrderPanel") {
            		if(table.columnAtPoint(pt) == 2) {
            			Cursor cursor = new Cursor(Cursor.TEXT_CURSOR);
                		table.setCursor(cursor);
            		} else if(table.columnAtPoint(pt) == 0){
            			Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
                		table.setCursor(cursor);
            		} else {
            			Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
                		table.setCursor(cursor);
            		}
            	} else if(panelNames == "Default") {
                		Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
                		table.setCursor(cursor);
            	} else {
            		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
                    table.setCursor(cursor);
            	}
            	
            }
        });
	}
	
	public void deleteRows() {
		for (int i = model.getRowCount() - 1; i > -1; i--) {
			model.removeRow(i);
	     }
	}
	

    public TableRowSorter<DefaultTableModel> getRowSorter() {
		return rowSorter;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public JTable getTable() {
		return table;
	}

	public JFrame getFrame() {
		return frame;
	}
	
	
	
}
