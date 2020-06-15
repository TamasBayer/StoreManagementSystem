package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import view.StockControlPanelStockS.StockControlPanelStockS;
import view.StockPanelStockS.StockPanelStockS;
import view.inventoryPanelStockS.InventoryPanelStockS;
import view.pickingForOrdersPanelStockS.OrderPickingPanel;


public class TabbedPaneStockSystem extends JPanel{

	private InventoryPanelStockS iPanel;
	private StockPanelStockS sPanel;
	private StockControlPanelStockS sCPanel;
	private OrderPickingPanel oPPanel;
	
	private Connection conn;

	public TabbedPaneStockSystem(Connection getConn) {
		
		this.setLayout(new BorderLayout());
		
		iPanel = new InventoryPanelStockS();
		sPanel = new StockPanelStockS();
		sCPanel = new StockControlPanelStockS();
		oPPanel = new OrderPickingPanel();
		
        // Creates a JTabbedPane with tabs at the bottom.
        JTabbedPane pane = new JTabbedPane();
        pane.addTab("Inventory", iPanel);
        pane.addTab("Stock", sPanel);
        pane.addTab("Stock Control", sCPanel);
        pane.addTab("Sell orders", oPPanel);

      
        setPreferredSize(new Dimension(650, 450));
        this.add(pane, BorderLayout.CENTER);
        
        //// setConn ////
        
        this.conn = getConn;
        
        iPanel.setConn(conn);
        iPanel.fillTableWithData();
        
        sPanel.setConn(conn);
        sPanel.fillTableWithData();
        
        sCPanel.setConn(conn);
        
        oPPanel.setConn(conn);
        oPPanel.setAllSellOrderButton();
	}
	
}
