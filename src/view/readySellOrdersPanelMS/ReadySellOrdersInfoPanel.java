package view.readySellOrdersPanelMS;

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

public class ReadySellOrdersInfoPanel extends JPanel{

	private JPanel choosedSellOrderPanel;
	private JLabel sellOrderIDLabel;
	private JTextField sellOrderIDField;
	private JLabel soldForLabel;
	private JTextField soldForField;
	private JLabel sellOrderDatumLabel;
	private JTextField sellOrderDatumField;
	
	private Table sellOrdersTable;
	private String[] sellOrdersColumnNames;

	
	public ReadySellOrdersInfoPanel() {
		setPreferredSize(new Dimension(1000,600));
		
		sellOrdersColumnNames = new String[] {"Item-ID", "Item name", "Sold quantity"};
		sellOrdersTable = new Table(sellOrdersColumnNames, "Default");
		
		
		choosedSellOrderPanel = new JPanel();
		sellOrderIDLabel = new JLabel("Sell Order-ID:");
		sellOrderIDField = new JTextField(10);
		soldForLabel = new JLabel("Sold for:");
		soldForField = new JTextField(10);
		sellOrderDatumLabel = new JLabel("Sold datum:");
		sellOrderDatumField = new JTextField(10);
		
		sellOrderIDField.setEditable(false);
		soldForField.setEditable(false);
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
        choosedSellOrderPanel.add(soldForLabel);
        choosedSellOrderPanel.add(soldForField);
        choosedSellOrderPanel.add(sellOrderDatumLabel);
        choosedSellOrderPanel.add(sellOrderDatumField);
	    Border innerBorderOrder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorderOrder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        choosedSellOrderPanel.setBorder(BorderFactory.createCompoundBorder(outerBorderOrder, innerBorderOrder));
 
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0;
        c.weightx = 1;
        add(choosedSellOrderPanel, c);
        
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

	public JTextField getSoldForField() {
		return soldForField;
	}

	public JTextField getSellOrderDatumField() {
		return sellOrderDatumField;
	}

	public Table getSellOrdersTable() {
		return sellOrdersTable;
	}
	
	
}
