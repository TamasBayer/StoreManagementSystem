package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import view.inventoryPanel.InventoryPanel;
import view.ordersPanel.OrdersPanel;

public class TabbedPane extends JPanel {
	
	private InventoryPanel iPanel;
	private OrdersPanel oPanel;

	public TabbedPane() {
		
		this.setLayout(new BorderLayout());
		
		iPanel = new InventoryPanel();
		oPanel = new OrdersPanel();

        // Creates a JTabbedPane with tabs at the bottom.
        JTabbedPane pane = new JTabbedPane(JTabbedPane.LEFT);
        pane.addTab("Inventory", iPanel);
        pane.addTab("Orders", oPanel);
        pane.addTab("Goods", createPanel("Panel 3"));
      

        this.add(pane, BorderLayout.CENTER);
	}
	
	private JPanel createPanel(String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JLabel(title), BorderLayout.NORTH);
        return panel;
    }
}
