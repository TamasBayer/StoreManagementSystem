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
        setSize(400, 300);
        setMinimumSize( new Dimension(400, 300));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        
        loginPanel = new LoginPanel();
        loginBtn = loginPanel.getLoginButton();
        
        setLayout(new BorderLayout());

        add(loginPanel, BorderLayout.CENTER);
        
        loginButtonPressed();
        
    }
    
    public void loginButtonPressed(){
    	loginBtn.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent e) {
                
            	setSize(1100, 700);
                setMinimumSize( new Dimension(600, 400));
                setLocationRelativeTo(null);
                
                remove(loginPanel);
                add(new TabbedPane(), BorderLayout.CENTER);
                
                
            }
        });

    }
}
