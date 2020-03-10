package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import view.inventoryPanel.InventoryPanel;
import view.ordersPanel.OrdersPanel;
import view.readyOrdersPanel.ReadyOrdersPanel;
import view.readySellOrdersPanel.ReadySellOrdersPanel;
import view.sellOrdersPanel.SellOrdersPanel;

public class TabbedPane extends JPanel {
	
	private InventoryPanel iPanel;
	private OrdersPanel oPanel;
	private SellOrdersPanel sOPanel;
	private ReadyOrdersPanel rOPanel;
	private ReadySellOrdersPanel rSOPanel;

	public TabbedPane() {
		
		this.setLayout(new BorderLayout());
		
		iPanel = new InventoryPanel();
		oPanel = new OrdersPanel();
		sOPanel = new SellOrdersPanel();
		rOPanel = new ReadyOrdersPanel();
		rSOPanel = new ReadySellOrdersPanel();

        // Creates a JTabbedPane with tabs at the bottom.
        JTabbedPane pane = new JTabbedPane(JTabbedPane.LEFT);
        pane.addTab("Inventory", iPanel);
        pane.addTab("Orders", oPanel);
        pane.addTab("Sell orders", sOPanel);
        pane.addTab("Ready orders", rOPanel);
        pane.addTab("Ready sell orders", rSOPanel);
      
        setPreferredSize(new Dimension(1100, 700));
        this.add(pane, BorderLayout.CENTER);
	}
}
