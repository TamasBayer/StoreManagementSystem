package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    

    public MainFrame(){
        
        super("ViewProject");
        setSize(1000, 600);
        setMinimumSize( new Dimension(650, 450));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());

        add(new TabbedPane(), BorderLayout.CENTER);
        
        
        
    }
}
