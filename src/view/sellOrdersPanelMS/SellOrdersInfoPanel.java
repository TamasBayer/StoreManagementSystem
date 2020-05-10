package view.sellOrdersPanelMS;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import view.classesForPanels.Table;

public class SellOrdersInfoPanel extends JPanel{
	
	private JPanel choosedSellOrderPanel;
	private JLabel sellOrderIDLabel;
	private JTextField sellOrderIDField;
	private JLabel companyNameLabel;
	private JTextField companyNameField;
	private JLabel sellOrderDatumLabel;
	private JTextField sellOrderDatumField;
	private JPanel sellOrderTableFlowLayout;
	
	private Table sellOrdersTable;
	private String[] sellOrdersColumnNames;
	private JButton bookButton;
	private JButton editButton;
	
	
	public SellOrdersInfoPanel() {
		setPreferredSize(new Dimension(1000,600));
		
		sellOrdersColumnNames = new String[] {"Item-ID", "Item name", "Sold quantity", "Already picked quantity"};
		sellOrdersTable = new Table(sellOrdersColumnNames, "Default");
		bookButton = new JButton("");
		editButton = new JButton("Edit Order");
		sellOrderTableFlowLayout = new JPanel();
		
		sellOrdersTable.fillWithData();
		
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		bookButton.setCursor(cursor);
		editButton.setCursor(cursor);
		
		choosedSellOrderPanel = new JPanel();
		sellOrderIDLabel = new JLabel("Sell Order-ID:");
		sellOrderIDField = new JTextField(10);
		companyNameLabel = new JLabel("Sold for:");
		companyNameField = new JTextField(10);
		sellOrderDatumLabel = new JLabel("Sold datum:");
		sellOrderDatumField = new JTextField(10);
		
		sellOrderIDField.setEditable(false);
		companyNameField.setEditable(false);
		sellOrderDatumField.setEditable(false);
		
		GridBagLayout();
	}
	
	private void GridBagLayout() {   
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        choosedSellOrderPanel.setPreferredSize(new Dimension(50, 50));
        
        
        FlowLayout OrderPanelLayout = new FlowLayout();
        choosedSellOrderPanel.setLayout(OrderPanelLayout);
        choosedSellOrderPanel.add(sellOrderIDLabel);
        choosedSellOrderPanel.add(sellOrderIDField);
        choosedSellOrderPanel.add(companyNameLabel);
        choosedSellOrderPanel.add(companyNameField);
        choosedSellOrderPanel.add(sellOrderDatumLabel);
        choosedSellOrderPanel.add(sellOrderDatumField);
	    Border innerBorderOrder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorderOrder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        choosedSellOrderPanel.setBorder(BorderFactory.createCompoundBorder(outerBorderOrder, innerBorderOrder));
        
        FlowLayout OrderPanelButtonsLayout = new FlowLayout();
        sellOrderTableFlowLayout.setLayout(OrderPanelButtonsLayout);
     //   sellOrderTableFlowLayout.add(bookButton);
     //   sellOrderTableFlowLayout.add(editButton);
        
        Border innerBorderButtons = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorderButtons = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        sellOrderTableFlowLayout.setBorder(BorderFactory.createCompoundBorder(outerBorderButtons, innerBorderButtons));
        
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0;
        c.weightx = 1;
        add(choosedSellOrderPanel, c);
 /*       
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0;
        c.weightx = 1;
        add(sellOrderTableFlowLayout, c);
*/        
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 4;
        c.weightx = 1;
        add(sellOrdersTable, c);
	}

	public JTextField getSellOrderIDField() {
		return sellOrderIDField;
	}

	public JTextField getCompanyNameField() {
		return companyNameField;
	}

	public JTextField getSellOrderDatumField() {
		return sellOrderDatumField;
	}

	public Table getSellOrdersTable() {
		return sellOrdersTable;
	}
	
	

}
