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

    public MainFrame(){
        
        super("Login");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
        
        loginPanel = new LoginPanel();
        loginBtn = loginPanel.getLoginButton();
        
        setLayout(new BorderLayout());

        add(loginPanel, BorderLayout.CENTER);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        loginButtonPressed();
        
    }
    
    public void loginButtonPressed(){
    	loginBtn.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent e) {
                
            	remove(loginPanel);
            	
            	
            	add(new AppChooserPanel(), BorderLayout.CENTER);
                pack();
                setLocationRelativeTo(null);
                setResizable(false);
                
            }
        });

    }
}
