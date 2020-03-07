package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedPane extends JPanel {

	public TabbedPane() {
		this.setLayout(new BorderLayout());

        // Creates a JTabbedPane with tabs at the bottom.
        JTabbedPane pane = new JTabbedPane(JTabbedPane.LEFT);
        pane.addTab("Inventory", createPanel("Panel 1"));
        pane.addTab("Orders", createPanel("Panel 2"));
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