package view.classesForPanels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import model.Goods;

public class AddItemOrOrderPanel extends JPanel {
	
	// Add Item Panel
	private JLabel newItemIDLabel;
	private JTextField newItemIDField;
	private JLabel newItemNameLabel;
	private JTextField newItemNameField;
	private JButton addItemButton;
	private Cursor cursor;
	
	// Add Order Panel
	private JPanel infoOrderPanel;
	private JLabel orderIDLabel;
	private JTextField orderIDField;
	private JLabel companyNameLabel;
	private JTextField companyNameField;
	private JLabel orderDatumLabel;
	private JTextField orderDatumField;
	private JPanel orderTableFlowLayout;
	private Table ordersTable;
	private String[] ordersColumnNames;
	private JButton addOrderButton;
	
	
	public void layoutComponentsAddItem(){
		
		newItemIDLabel = new JLabel("Item-ID"); 
		newItemIDField = new JTextField(10);
		newItemNameLabel = new JLabel("Item name ");
		newItemNameField = new JTextField(10);
		addItemButton = new JButton("Add");
		
		newItemIDField.setEditable(false);
		
		cursor = new Cursor(Cursor.HAND_CURSOR);
        addItemButton.setCursor(cursor);
        
        //////// Set Layout ////////
		
		setPreferredSize(new Dimension(300, 200));
        Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new GridBagLayout());
        
        GridBagConstraints gc = new GridBagConstraints();
        
        /////////// First row /////////////////
     
        gc.gridy = 0;
        
        gc.weightx = 1;
        gc.weighty = 0;
        
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 20, 5);
        add(newItemIDLabel, gc);
        
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 20, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(newItemIDField, gc);
        
        /////////// Second row /////////////////
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty =  0;
        
        gc.gridx = 0;
        gc.insets = new Insets(0, 0 , 20, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(newItemNameLabel, gc);
        
        gc.gridy = 1;
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 20, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(newItemNameField, gc);
        
        /////////// Third row /////////////////
        
	    gc.gridy++;
	      
	    gc.weightx = 1;
	    gc.weighty =  0;
	      
        gc.gridx = 1;
        gc.insets = new Insets(0, 55, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(addItemButton, gc);
        
        
     }
	
	public void layoutComponentsAddOrder(ArrayList<Goods> goods, Boolean sellOrderOrNot) {
		setPreferredSize(new Dimension(1000,600));
		
		if(sellOrderOrNot) {
			ordersColumnNames = new String[] {"Item-ID", "Item-Name", "Sold quantity"};
			orderIDLabel = new JLabel("Sell order-ID:");
			companyNameLabel = new JLabel("Sold for:");
			orderDatumLabel = new JLabel("Sell Order datum:");
		} else {
			ordersColumnNames = new String[] {"Item-ID", "Item-Name", "Ordered quantity"};
			orderIDLabel = new JLabel("Order-ID:");
			companyNameLabel = new JLabel("Ordered from:");
			orderDatumLabel = new JLabel("Order datum:");
		}
		
		ordersTable = new Table(ordersColumnNames, "AddItemOrOrderPanel");
		orderIDField = new JTextField(10);
		companyNameField = new JTextField(10);
		orderDatumField = new JTextField(10);
		addOrderButton = new JButton("Add");
		
		ordersTable.fillWithEmptyRows();
		ordersTable.comboBoxColumnItemID(goods);
		
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		addOrderButton.setCursor(cursor);
		
		orderIDField.setEditable(false);
		
		//////// Set layout ////////
		
		setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        infoOrderPanel = new JPanel();
        infoOrderPanel.setPreferredSize(new Dimension(50, 50));
        
        orderTableFlowLayout = new JPanel();
        
        FlowLayout OrderPanelLayout = new FlowLayout();
        infoOrderPanel.setLayout(OrderPanelLayout);
        infoOrderPanel.add(orderIDLabel);
        infoOrderPanel.add(orderIDField);
        infoOrderPanel.add(companyNameLabel);
        infoOrderPanel.add(companyNameField);
        infoOrderPanel.add(orderDatumLabel);
        infoOrderPanel.add(orderDatumField);
	    Border innerBorderOrder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorderOrder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        infoOrderPanel.setBorder(BorderFactory.createCompoundBorder(outerBorderOrder, innerBorderOrder));
        
        FlowLayout OrderPanelButtonsLayout = new FlowLayout(FlowLayout.RIGHT);
        orderTableFlowLayout.setLayout(OrderPanelButtonsLayout);
        orderTableFlowLayout.add(addOrderButton);
        
        Border innerBorderButtons = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorderButtons = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        orderTableFlowLayout.setBorder(BorderFactory.createCompoundBorder(outerBorderButtons, innerBorderButtons));
        
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0;
        c.weightx = 1;
        add(infoOrderPanel, c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 4;
        c.weightx = 1;
        add(ordersTable, c);
        
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0;
        c.weightx = 1;
        add(orderTableFlowLayout, c);
	}
	
	public JTextField getNewItemIDField() {
		return newItemIDField;
	}

	public JTextField getNewItemNameField() {
		return newItemNameField;
	}

	public JTextField getCompanyNameField() {
		return companyNameField;
	}

	public JTextField getOrderDatumField() {
		return orderDatumField;
	}

	public JButton getAddItemButton() {
		return addItemButton;
	}

	public JButton getAddOrderButton() {
		return addOrderButton;
	}

	public Table getOrdersTable() {
		return ordersTable;
	}

	public JTextField getOrderIDField() {
		return orderIDField;
	}
	
}
