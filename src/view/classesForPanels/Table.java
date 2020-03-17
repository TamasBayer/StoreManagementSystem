package view.classesForPanels;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
    		    public boolean isCellEditable(int row, int column) {
    		       
    		       return false;
    		    }
    	   
    	    };
    	
    	table = new JTable(model);
        rowSorter = new TableRowSorter<>(model);
        table.setRowSorter(rowSorter);
        
        setLayout(new BorderLayout());
        
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        table.setCursor(cursor);
    }
    

	public void fillWithData() {
		model.insertRow(0,new Object[]{"H1322","Apple", "50"});
		model.insertRow(1,new Object[]{"H1323","Bread", "213"});
		model.insertRow(2,new Object[]{"H1324","Strawberry", "32"});
		model.insertRow(3,new Object[]{"H1325","Salat", "43"});
		model.insertRow(4,new Object[]{"H1326","Retek", "2111"});
	
		
		
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

    public TableRowSorter<DefaultTableModel> getRowSorter() {
		return rowSorter;
	}

	public DefaultTableModel getModel() {
		return model;
	}
	
	
}
