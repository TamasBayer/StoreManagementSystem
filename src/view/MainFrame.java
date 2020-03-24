package view;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame {
    
	private LoginPanel loginPanel;
	private JButton loginBtn;
	private AppChooserPanel appChooserPanel;
	private JButton mainSystemBtn;
	private JButton stockSystemBtn;
	
	private JFrame testFrame;

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
                setJMenuBar(createMenuBar());
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
                setResizable(true);
                setJMenuBar(createMenuBar());
            }
        });

    }
    
    private  JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        
                
        
        JMenu menu = new JMenu("Tamás Bayer");
        JMenuItem openStockManager = new JMenuItem("Open Stock Manager");
        JMenuItem changeApp = new JMenuItem("Change App");
        JMenuItem logout = new JMenuItem("Logout");
        JMenuItem exit = new JMenuItem("Exit");
        
        menu.add(openStockManager);
        menu.add(changeApp);          
        menu.addSeparator();
        menu.add(logout);
        menu.addSeparator();
        menu.add(exit);
        
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(menu);
        
        openStockManager.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                

                	testFrame = new JFrame();
                	testFrame.add(new TabbedPaneStockSystem(), BorderLayout.CENTER);
                	pack();
                    setLocationRelativeTo(null);
                    setResizable(true);
                    setJMenuBar(createMenuBar());
                
            }
        });
        
        changeApp.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                
                int action = JOptionPane.showConfirmDialog(MainFrame.this,
                              "Do you really want to log out?",
                              "ComfirmExit", JOptionPane.OK_CANCEL_OPTION);
                if(action == JOptionPane.OK_OPTION){
                remove(getContentPane());
                System.out.println(getContentPane().getName());
                
                }
            }
        });
        
        logout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                
                int action = JOptionPane.showConfirmDialog(MainFrame.this,
                              "Do you really want to log out?",
                              "ComfirmExit", JOptionPane.OK_CANCEL_OPTION);
                if(action == JOptionPane.OK_OPTION){
                remove(getContentPane());
                System.out.println(getContentPane().getName());
                
                }
            }
        });
        
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                
                int action = JOptionPane.showConfirmDialog(MainFrame.this,
                              "Do you really want to exit the application?",
                              "ComfirmExit", JOptionPane.OK_CANCEL_OPTION);
                if(action == JOptionPane.OK_OPTION){
                System.exit(0);
                }
            }
        });
        
        return menuBar;
    }
}
