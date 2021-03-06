package view.inventoryPanelMS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import view.classesForPanels.Table;

public class GoodsInfoPanel extends JPanel{

	private Table quantityTable;
	private Table buyingOrdersTable;
	private Table sellingOrdersTable;
	private Table stocksTable;
	private JPanel choosedItemPanel;
	private String[] columnNamesTable;
	private String[] columnNamesP1;
	private String[] columnNamesP2;
	private String[] columnNamesP3;
	private JLabel itemIDLabel;
	private JLabel itemNameLabel;
	private JTextField itemIDTextField;
	private JTextField itemNameTextField;
	
	public GoodsInfoPanel() {
		setPreferredSize(new Dimension(500,500));
		setMinimumSize(new Dimension(500,500));
		
		choosedItemPanel = new JPanel();
		itemIDLabel = new JLabel("Item-ID:");
		itemNameLabel = new JLabel("Item name:");
		itemIDTextField = new JTextField(10);
		itemNameTextField = new JTextField(10);
		itemIDTextField.setEditable(false);
		itemNameTextField.setEditable(false);
		columnNamesTable = new String[] {"Booked", "Ordered", "In warehouse"};
		quantityTable = new Table(columnNamesTable);
		columnNamesP1 = new String[] {"Number", "Quantity"};
		buyingOrdersTable = new Table(columnNamesP1);
		columnNamesP2 = new String[] {"Number", "Quantity"};
		sellingOrdersTable = new Table(columnNamesP2);
		columnNamesP3 = new String[] {"Stock", "Quantity"};
		stocksTable = new Table(columnNamesP3);
		
		GridBagLayoutExample();
	}
	
	public void GridBagLayoutExample() {   
	        setLayout(new GridBagLayout());
	        GridBagConstraints c = new GridBagConstraints();

	        
	        choosedItemPanel.setPreferredSize(new Dimension(50, 50));
	        
	        FlowLayout ItemPanelLayout = new FlowLayout();
		    choosedItemPanel.setLayout(ItemPanelLayout);
		    choosedItemPanel.add(itemIDLabel);
		    choosedItemPanel.add(itemIDTextField);
		    choosedItemPanel.add(itemNameLabel);
		    choosedItemPanel.add(itemNameTextField);
		    Border innerBorderItem = BorderFactory.createLineBorder(Color.GRAY);
	        Border outerBorderItem = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	        choosedItemPanel.setBorder(BorderFactory.createCompoundBorder(outerBorderItem, innerBorderItem));
	        
	        buyingOrdersTable.setPreferredSize(new Dimension(50, 50));
	        
	        Border innerBorderP1 = BorderFactory.createTitledBorder("Buying Orders");
	        Border outerBorderP1 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	        buyingOrdersTable.setBorder(BorderFactory.createCompoundBorder(outerBorderP1, innerBorderP1));



	        sellingOrdersTable.setPreferredSize(new Dimension(50, 50));
	  
	        
	        Border innerBorderP2 = BorderFactory.createTitledBorder("Selling Orders");
	        Border outerBorderP2 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	        sellingOrdersTable.setBorder(BorderFactory.createCompoundBorder(outerBorderP2, innerBorderP2));
	        
	        stocksTable.setPreferredSize(new Dimension(100, 200));
	  
	        
	        Border innerBorderP3 = BorderFactory.createTitledBorder("Stock");
	        Border outerBorderP3 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	        stocksTable.setBorder(BorderFactory.createCompoundBorder(outerBorderP3, innerBorderP3));
	       

	        quantityTable.setPreferredSize(new Dimension(50, 80));

	        Border innerBorderTable = BorderFactory.createTitledBorder("Quantity");
	        Border outerBorderTable = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	        quantityTable.setBorder(BorderFactory.createCompoundBorder(outerBorderTable, innerBorderTable));
	        

	        c.gridx = 0;
	        c.gridy = 0;
	        c.gridheight = 1;
	        c.gridwidth = 2;
	        c.fill = GridBagConstraints.HORIZONTAL;
	        c.weighty = 0;
	        c.weightx = 1;
	        add(choosedItemPanel, c);
	        
	        c.gridx = 0;
	        c.gridy = 1;
	        c.fill = GridBagConstraints.BOTH;
	        c.gridwidth = 1;
	        c.weightx = 1;
	        c.weighty = 1;
	        add(buyingOrdersTable, c);
      
	        c.gridx = 1;
	        c.gridy = 1;
	        c.weighty = 1;
	        c.weightx = 1;
	        c.fill = GridBagConstraints.BOTH;
	        c.gridwidth = 1;
	        add(sellingOrdersTable, c);

	        c.gridx = 0;
	        c.gridy = 2;
	        c.gridheight = 1;
	        c.gridwidth = 2;
	        c.fill = GridBagConstraints.HORIZONTAL;
	        c.weighty = 0;
	        c.weightx = 1;
	        add(quantityTable, c);
	        
	        c.gridx = 0;
	        c.gridy = 3;
	        c.gridheight = 1;
	        c.gridwidth = 2;
	        c.fill = GridBagConstraints.HORIZONTAL;
	        c.weighty = 0;
	        c.weightx = 1;
	        add(stocksTable, c);

	    }

	public JTextField getItemIDTextField() {
		return itemIDTextField;
	}

	public JTextField getItemNameTextField() {
		return itemNameTextField;
	}

	public Table getQuantityTable() {
		return quantityTable;
	}

	public Table getBuyingOrdersTable() {
		return buyingOrdersTable;
	}

	public Table getSellingOrdersTable() {
		return sellingOrdersTable;
	}

	public Table getStocksTable() {
		return stocksTable;
	}
	
	
}
	 

