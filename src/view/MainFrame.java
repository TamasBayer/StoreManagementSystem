package view;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.DB;
import model.Users;

public class MainFrame extends JFrame {
    
	private LoginPanel loginPanel;
	private JButton loginBtn;
	private AppChooserPanel appChooserPanel;
	private JButton mainSystemBtn;
	private JButton stockSystemBtn;
	
	private TabbedPaneMainSystem tabbedPaneMainSystem;
	private TabbedPaneStockSystem tabbedPaneStockSystem;
	
	private JFrame testFrame;
	
	private int choosedPanel;
	
	private DB db;
	
	private Connection conn;
	private Statement createStatement = null;

    public MainFrame(){
        
        super("Store Management");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
		////////////Set DB Connection for all Panel ////////////
				
			db = new DB();
			conn = db.getConn();
		
        loginPanel = new LoginPanel();
        loginBtn = loginPanel.getLoginButton();
        
        appChooserPanel = new AppChooserPanel();
        mainSystemBtn = appChooserPanel.getMainSystemButton();
        stockSystemBtn = appChooserPanel.getStockSystemButton();
        
        tabbedPaneMainSystem = new TabbedPaneMainSystem(conn);
        tabbedPaneStockSystem = new TabbedPaneStockSystem();
        
        
        setLayout(new BorderLayout());

        add(loginPanel, BorderLayout.CENTER);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        
        choosedPanel = 0;
        
        loginButtonPressed();
        mainSystemBtnPressed();
        stockSystemBtnPressed();
        
    }
    
    public void loadCreateStatement() {

		
		if (conn != null && createStatement == null){
	        try {
	             createStatement = conn.createStatement();
	        } catch (SQLException ex) {
	            System.out.println("Error with createStatement");
	            System.out.println(" "+ex);

	        }
		}
	        
	}
    
    private Boolean checkUser(){
    	String[] textFieldData = loginPanel.getTextFields();
    	
    	String userPassword = "";
    	
    	if(textFieldData != null) {
    		String sql = "SELECT userPassword FROM Users WHERE userName='"+ textFieldData[0] +"'";
    		try {
            	loadCreateStatement();
                ResultSet rs = createStatement.executeQuery(sql);
                
                if (rs.next()) {
                	userPassword = rs.getString("userPassword");
                    
                }
                
                
                if(userPassword.equals(textFieldData[1])) {
                	return true;
                } else {
                	System.out.println("Incorrect password or user name");
                }
           
            } catch (SQLException ex) {
                System.out.println("Error with login");
                System.out.println(" "+ex);
                }    
    	} else {
    		System.out.println("Please give your user name and password");
    	}
        
        return false;
}
    
    public void loginButtonPressed(){
    	loginBtn.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent e) {
                
            	Boolean correctUserData = checkUser();
            	
            	if(correctUserData == true) {
            		remove(loginPanel);
                	
                	add(appChooserPanel, BorderLayout.CENTER);
                    pack();
                    setLocationRelativeTo(null);
                    setResizable(false);
                    
                    loginPanel.clearTextFields();
            	}
            	
                
            }
        });

    }
    
    public void mainSystemBtnPressed(){
    	mainSystemBtn.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent e) {
                
            	remove(appChooserPanel);
            	
            	
            	add(tabbedPaneMainSystem, BorderLayout.CENTER);
                pack();
                setLocationRelativeTo(null);
                setResizable(true);
                setMinimumSize(new Dimension(800, 400));
                
                choosedPanel = 1;
                
                setJMenuBar(createMenuBar());
            }
        });

    }
    
    public void stockSystemBtnPressed(){
    	stockSystemBtn.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent e) {
                
            	remove(appChooserPanel);
            	
            	
            	add(tabbedPaneStockSystem, BorderLayout.CENTER);
                pack();
                setLocationRelativeTo(null);
                setResizable(true);
                setMinimumSize(new Dimension(500, 400));
                
                choosedPanel = 2;
                
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
        
        if(choosedPanel == 1) {
        	menu.add(openStockManager);
        }
        menu.add(changeApp);          
        menu.addSeparator();
        menu.add(logout);
        menu.addSeparator();
        menu.add(exit);
        
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(menu);
        
        openStockManager.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
               
                    
                    if (testFrame == null) {
 		        	   
                      testFrame = new JFrame();
  		              
                      testFrame.setLayout(new BorderLayout());

                      testFrame.add(tabbedPaneStockSystem, BorderLayout.CENTER);
                      testFrame.setVisible(true);
                      testFrame.setDefaultCloseOperation(testFrame.DISPOSE_ON_CLOSE);
                      testFrame.setResizable(true);
                      testFrame.pack();
  		              
  		              Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  		              Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
  		              Point newLocation = new Point(middle.x - (testFrame.getWidth() / 2), 
  		                                            middle.y - (testFrame.getHeight() / 2));
  		            testFrame.setLocation(newLocation);
  		              
  		         	testFrame.addWindowListener(new java.awt.event.WindowAdapter() {
  		  			    @Override
  		  			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
  		  			    	testFrame = null;
  		  			    }
  		  			});
  	  		    } else {
  	  		    	testFrame.setVisible(true);
  	  		    }
                
            }
        });
        
        changeApp.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                
                	if(choosedPanel == 1) {
                		remove(tabbedPaneMainSystem);
                	} else {
                		remove(tabbedPaneStockSystem);
                	}
                	
                	setJMenuBar(null);
                	add(appChooserPanel, BorderLayout.CENTER);
                	setMinimumSize(new Dimension(250, 150));
                    pack();
                    setLocationRelativeTo(null);
                    setResizable(false);
                    
                    if(testFrame != null) {
                    	testFrame.dispose();
                    }
                    
                
                
            }
        });
        
        logout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                
                int action = JOptionPane.showConfirmDialog(MainFrame.this,
                              "Do you really want to log out?",
                              "ComfirmExit", JOptionPane.OK_CANCEL_OPTION);
                if(action == JOptionPane.OK_OPTION){
                	
                	if(choosedPanel == 1) {
                		remove(tabbedPaneMainSystem);
                	} else {
                		remove(tabbedPaneStockSystem);
                	}
                	
                    setJMenuBar(null);
                	add(loginPanel, BorderLayout.CENTER);
                	setMinimumSize(new Dimension(300, 200));
                    pack();
                    setLocationRelativeTo(null);
                    setResizable(false);
                    
                    if(testFrame != null) {
                    	testFrame.dispose();
                    }
                    
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
