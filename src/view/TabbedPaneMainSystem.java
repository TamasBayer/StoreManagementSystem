package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import view.inventoryPanelMS.InventoryPanel;
import view.ordersPanelMS.OrdersPanel;
import view.readyOrdersPanelMS.ReadyOrdersPanel;
import view.readySellOrdersPanelMS.ReadySellOrdersPanel;
import view.sellOrdersPanelMS.SellOrdersPanel;
import view.stockInOutPanelMS.StockInOutPanel;

public class TabbedPaneMainSystem extends JPanel {
	
	private InventoryPanel iPanel;
	private OrdersPanel oPanel;
	private SellOrdersPanel sOPanel;
	private ReadyOrdersPanel rOPanel;
	private ReadySellOrdersPanel rSOPanel;
	private StockInOutPanel sIOPanel;
	
	private Connection conn;

	public TabbedPaneMainSystem(Connection getConn) {
		
		this.setLayout(new BorderLayout());
		
		iPanel = new InventoryPanel();
		oPanel = new OrdersPanel();
		sOPanel = new SellOrdersPanel();
		rOPanel = new ReadyOrdersPanel();
		rSOPanel = new ReadySellOrdersPanel();
		sIOPanel = new StockInOutPanel();

        // Creates a JTabbedPane with tabs at the bottom.
        JTabbedPane pane = new JTabbedPane(JTabbedPane.LEFT);
        pane.addTab("Inventory", iPanel);
        pane.addTab("Orders", oPanel);
        pane.addTab("Sell orders", sOPanel);
        pane.addTab("Ready orders", rOPanel);
        pane.addTab("Ready sell orders", rSOPanel);
        pane.addTab("Stock In-Out", sIOPanel);
        
        setPreferredSize(new Dimension(1100, 700));
        this.add(pane, BorderLayout.CENTER);
        
        //// setConn ////
        
        this.conn = getConn;
        iPanel.setConn(conn);
        iPanel.fillTableWithData();
        
        oPanel.setConn(conn);
        oPanel.fillTableWithData();
	}
	
	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
