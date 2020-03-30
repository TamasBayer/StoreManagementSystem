package view.stockInOutPanelMS;

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

public class StockInOutPanel extends JPanel {

	private JPanel orderTableFlowLayout;
	private Table ordersTable;
	private String[] ordersColumnNames;
	private JButton addOrderButton;
	
	public StockInOutPanel() {

		setPreferredSize(new Dimension(1000,600));
		
		ordersColumnNames = new String[] {"In or Out", "Item-ID", "Item name", "Quantity", "Stock"};
		ordersTable = new Table(ordersColumnNames, "StockInOutPanel");
		addOrderButton = new JButton("Book");
		orderTableFlowLayout = new JPanel();
		
		ordersTable.fillWithEmptyRows();
		ordersTable.comboBoxColumnInOutPanel();
		
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		addOrderButton.setCursor(cursor);
		
		setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        
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
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 4;
        c.weightx = 1;
        add(ordersTable, c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0;
        c.weightx = 1;
        add(orderTableFlowLayout, c);
	}
}
