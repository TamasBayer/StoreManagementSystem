package view.pickingForOrdersPanelStockS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import view.classesForPanels.SearchPanel;
import view.classesForPanels.Table;

public class OrderPickingPanel extends JPanel {
	
	
	private JPanel buttonPanel;
    private JScrollPane scrollPane;
    private Connection conn;
    private Statement createStatement = null;
    
    private JPanel ordersPanel;
    private JPanel orderControlPanel;
    private JLabel itemIDLabel;
    private JTextField itemIDField;
    private JLabel itemNameLabel;
    private JTextField itemNameField;
    private JLabel itemNeedQuantityLabel;
    private JTextField itemNeedQuantityField;
    private JLabel itemBookQuantityFromStockLabel;
    private JTextField itemBookQuantityFromStockField;
    private JLabel itemStockLabel;
    private JTextField itemStockField;
    private JPanel buttonsPanel;
    private FlowLayout buttonsFlowL;
    private JButton bookBtn;
    private JButton orderBackBtn;
    private JButton StockBtn;
    
    private JPanel StockPanel;
    private Table stockTable;
    private String[] columnsName;
    private JButton stockBackBtn; 
    private JPanel backBtnPanel;
    private FlowLayout backBtnFlowL;
    
    private JButton button;
    
    public OrderPickingPanel() {
			
    	
    	buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,3,10,10));
        
        
        
        
        scrollPane = new JScrollPane();
 
        int quantityOfButtons = 100;
        for(int i = 0; i < quantityOfButtons; i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(130, 50));
            button.setText("Button #" + i);
            
            button.addActionListener(new ActionListener(){

                
                public void actionPerformed(ActionEvent e) {
                	
                	
                	remove(scrollPane);
                	
                	setOrdersPanel();
                	add(ordersPanel, BorderLayout.CENTER);
               }
         });
            
            buttonPanel.add(button);
            
        }
        scrollPane.setViewportView(buttonPanel);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        
        
    }
    
    public void setOrdersPanel() {
    	
    	ordersPanel = new JPanel();
    	
    	Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        ordersPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    	
    	orderControlPanel = new JPanel();
        itemIDLabel = new JLabel("Item-ID:");
        itemIDField = new JTextField(10);
        itemNameLabel = new JLabel("Item name:");
        itemNameField = new JTextField(10);
        itemNeedQuantityLabel = new JLabel("Need quantity:");
        itemNeedQuantityField = new JTextField(10);
        itemStockLabel = new JLabel("Stock:");
        itemStockField = new JTextField(10);
        itemBookQuantityFromStockLabel = new JLabel("Quantity from stock:");
        itemBookQuantityFromStockField = new JTextField(10);
        
        buttonsPanel = new JPanel();
        buttonsFlowL = new FlowLayout(FlowLayout.RIGHT);
        bookBtn = new JButton("Book");
        orderBackBtn = new JButton("Back");
        StockBtn = new JButton("Stock");
	    
        // FlowLayout //
        
        buttonsPanel.setLayout(buttonsFlowL);
	      
        buttonsPanel.setPreferredSize(new Dimension(40,40));
        buttonsPanel.add(StockBtn);
        buttonsPanel.add(bookBtn);
        buttonsPanel.add(orderBackBtn);
        
        orderBackBtn.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
            	
            	remove(ordersPanel);
                repaint();
            	add(scrollPane, BorderLayout.CENTER);
           }
     });
        
        StockBtn.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
            	
            	setStockPanel();
            	remove(ordersPanel);
                repaint();
            	add(StockPanel, BorderLayout.CENTER);
           }
     });
        
        bookBtn.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
            	
            	
           }
     });
        
        // Control Panel //
        
        
        orderControlPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        
        /////////// First row /////////////////
        
        gc.gridy = 0;
        
        gc.weightx = 1;
        gc.weighty = 0;
        
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        orderControlPanel.add(itemIDLabel, gc);
        
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        orderControlPanel.add(itemIDField, gc);
        
        /////////// Second row /////////////////
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty =  0;
        
        gc.gridx = 0;
        gc.insets = new Insets(0, 0 , 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        orderControlPanel.add(itemNameLabel, gc);
        
        gc.gridy = 1;
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        orderControlPanel.add(itemNameField, gc);
        
        /////////// Third row /////////////////
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty =  0;
        
        gc.gridx = 0;
        gc.insets = new Insets(0, 0 , 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        orderControlPanel.add(itemNeedQuantityLabel, gc);
        
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        orderControlPanel.add(itemNeedQuantityField, gc);
        
        /////////// Fourth row /////////////////
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty =  0;
        
        gc.gridx = 0;
        gc.insets = new Insets(0, 0 , 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        orderControlPanel.add(itemStockLabel, gc);
        
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        orderControlPanel.add(itemStockField, gc);
        
		/////////// Fifth row /////////////////
		gc.gridy++;
		  
		gc.weightx = 1;
		gc.weighty =  0;
		  
		gc.gridx = 0;
		gc.insets = new Insets(0, 0 , 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		orderControlPanel.add(itemBookQuantityFromStockLabel, gc);
		  
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		orderControlPanel.add(itemBookQuantityFromStockField, gc);
		
		
		
		ordersPanel.setLayout(new BorderLayout());
		ordersPanel.add(orderControlPanel, BorderLayout.CENTER);
		ordersPanel.add(buttonsPanel, BorderLayout.SOUTH);
    }
    
    public void setStockPanel() {
    	
    	StockPanel = new JPanel();
    	columnsName = new String[] {"Stock", "Stock quantity"};
        stockTable = new Table(columnsName);
        stockBackBtn = new JButton("Back"); 
        backBtnPanel = new JPanel();
        backBtnFlowL = new FlowLayout(FlowLayout.RIGHT);
        
		 // FlowLayout //
        
        backBtnPanel.setLayout(backBtnFlowL);
	      
        backBtnPanel.setPreferredSize(new Dimension(40,40));
        backBtnPanel.add(stockBackBtn);
        
        stockBackBtn.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
            	
            	remove(StockPanel);
                repaint();
            	add(ordersPanel, BorderLayout.CENTER);
           }
     });
        
        StockPanel.setLayout(new BorderLayout());
        StockPanel.add(stockTable, BorderLayout.CENTER);
        StockPanel.add(backBtnPanel, BorderLayout.SOUTH);
    }
}
