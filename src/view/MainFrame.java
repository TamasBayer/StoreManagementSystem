package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
    
	private LoginPanel loginPanel;
	private JButton loginBtn;
	private AppChooserPanel appChooserPanel;
	private JButton mainSystemBtn;
	private JButton stockSystemBtn;

    public MainFrame(){
        
        super("Store Management");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        
        loginPanel = new LoginPanel();
        loginBtn = loginPanel.getLoginButton();
        
        appChooserPanel = new AppChooserPanel();
        mainSystemBtn = appChooserPanel.getMainSystemButton();
        stockSystemBtn = appChooserPanel.getStockSystemButton();
        
        setLayout(new BorderLayout());

        add(loginPanel, BorderLayout.CENTER);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        
        loginButtonPressed();
        mainSystemBtnPressed();
        stockSystemBtnPressed();
        
    }
    
    public void loginButtonPressed(){
    	loginBtn.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent e) {
                
            	remove(loginPanel);
            	
            	
            	add(appChooserPanel, BorderLayout.CENTER);
                pack();
                setLocationRelativeTo(null);
                setResizable(false);
                
            }
        });

    }
    
    public void mainSystemBtnPressed(){
    	mainSystemBtn.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent e) {
                
            	remove(appChooserPanel);
            	
            	
            	add(new TabbedPaneMainSystem(), BorderLayout.CENTER);
                pack();
                setLocationRelativeTo(null);
                setResizable(true);
                setMinimumSize(new Dimension(800, 400));
                
            }
        });

    }
    
    public void stockSystemBtnPressed(){
    	stockSystemBtn.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent e) {
                
            	remove(appChooserPanel);
            	
            	
            	add(new TabbedPaneStockSystem(), BorderLayout.CENTER);
                pack();
                setLocationRelativeTo(null);
                setResizable(false);
                
            }
        });

    }
}
