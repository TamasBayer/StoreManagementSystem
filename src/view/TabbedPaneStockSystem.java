package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import view.StockControlPanelStockS.StockControlPanelStockS;
import view.StockPanelStockS.StockPanelStockS;
import view.inventoryPanelStockS.InventoryPanelStockS;


public class TabbedPaneStockSystem extends JPanel{

	private InventoryPanelStockS iPanel;
	private StockPanelStockS sPanel;
	private StockControlPanelStockS sCPanel;


	public TabbedPaneStockSystem() {
		
		this.setLayout(new BorderLayout());
		
		iPanel = new InventoryPanelStockS();
		sPanel = new StockPanelStockS();
		sCPanel = new StockControlPanelStockS();


        // Creates a JTabbedPane with tabs at the bottom.
        JTabbedPane pane = new JTabbedPane();
        pane.addTab("Inventory", iPanel);
        pane.addTab("Stock", sPanel);
        pane.addTab("Stock Control", sCPanel);

      
        setPreferredSize(new Dimension(650, 450));
        this.add(pane, BorderLayout.CENTER);
	}
}
