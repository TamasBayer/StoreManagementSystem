package view.readyOrdersPanelMS;

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

public class ReadyOrdersInfoPanel extends JPanel{

	private JPanel choosedOrderPanel;
	private JLabel orderIDLabel;
	private JTextField orderIDField;
	private JLabel companyNameLabel;
	private JTextField companyNameField;
	private JLabel orderDatumLabel;
	private JTextField orderDatumField;
	private JPanel orderTableFlowLayout;
	
	private Table ordersTable;
	private String[] ordersColumnNames;
	private JButton bookButton;
	private JButton editButton;
	
	
	public ReadyOrdersInfoPanel() {
		setPreferredSize(new Dimension(1000,600));
		
		ordersColumnNames = new String[] {"Item-ID", "Item-Name", "Ordered quantity"};
		ordersTable = new Table(ordersColumnNames);
		bookButton = new JButton("Book");
		editButton = new JButton("Edit Order");
		orderTableFlowLayout = new JPanel();
		
		ordersTable.fillWithData();
		
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		bookButton.setCursor(cursor);
		editButton.setCursor(cursor);
		
		choosedOrderPanel = new JPanel();
		orderIDLabel = new JLabel("Order-ID:");
		orderIDField = new JTextField(10);
		companyNameLabel = new JLabel("Ordered from:");
		companyNameField = new JTextField(10);
		orderDatumLabel = new JLabel("Order datum:");
		orderDatumField = new JTextField(10);
		
		orderIDField.setEditable(false);
		companyNameField.setEditable(false);
		orderDatumField.setEditable(false);
		
		GridBagLayout();
	}
	
	private void GridBagLayout() {   
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        choosedOrderPanel.setPreferredSize(new Dimension(50, 50));
        
        
        FlowLayout OrderPanelLayout = new FlowLayout();
        choosedOrderPanel.setLayout(OrderPanelLayout);
        choosedOrderPanel.add(orderIDLabel);
        choosedOrderPanel.add(orderIDField);
        choosedOrderPanel.add(companyNameLabel);
        choosedOrderPanel.add(companyNameField);
        choosedOrderPanel.add(orderDatumLabel);
        choosedOrderPanel.add(orderDatumField);
	    Border innerBorderOrder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorderOrder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        choosedOrderPanel.setBorder(BorderFactory.createCompoundBorder(outerBorderOrder, innerBorderOrder));
        
        FlowLayout OrderPanelButtonsLayout = new FlowLayout();
        orderTableFlowLayout.setLayout(OrderPanelButtonsLayout);
        orderTableFlowLayout.add(bookButton);
        orderTableFlowLayout.add(editButton);
        
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
        add(choosedOrderPanel, c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0;
        c.weightx = 1;
        add(orderTableFlowLayout, c);
        
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 4;
        c.weightx = 1;
        add(ordersTable, c);
	}
}
