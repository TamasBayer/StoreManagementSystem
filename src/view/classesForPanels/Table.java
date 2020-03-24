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

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

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
       
       
       differentCursor();
      
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
   		       
   		   }
   	   
   		       return false;
   	    }
   	    
   	 };
   	
   	table = new JTable(model);
       rowSorter = new TableRowSorter<>(model);
       table.setRowSorter(rowSorter);
       
       setLayout(new BorderLayout());
       
       add(new JScrollPane(table), BorderLayout.CENTER);
       
       
       differentCursor();
      
   }
    
    
    public void comboboxColumn() {
    	TableColumn sportColumn = table.getColumnModel().getColumn(0);
    	
    	JComboBox comboBox = new JComboBox();
    	comboBox.addItem("");
    	comboBox.addItem("H1322");
    	comboBox.addItem("H1323");
    	comboBox.addItem("H1324");
    	comboBox.addItem("H1325");
    	comboBox.addItem("H1326");
    	sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
    }
    

	public void fillWithData() {
		model.insertRow(0,new Object[]{"H1322","Apple", "50"});
		model.insertRow(1,new Object[]{"H1323","Bread", "213"});
		model.insertRow(2,new Object[]{"H1324","Strawberry", "32"});
		model.insertRow(3,new Object[]{"H1325","Salat", "43"});
		model.insertRow(4,new Object[]{"H1326","Retek", "2111"});
	
		
		
	}
	
	public void fillWithEmptyRows() {
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
	
	public void differentCursor(){
    	table.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
            	Point pt = e.getPoint();
            	if(table.columnAtPoint(pt) > 3) {
            		Cursor cursor = new Cursor(Cursor.TEXT_CURSOR);
            		table.setCursor(cursor);
            	} else {
            		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
                    table.setCursor(cursor);
            	}
            }
        });
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
	
	
	
}
