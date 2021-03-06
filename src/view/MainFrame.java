package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.DB;

public class MainFrame extends JFrame {
    
	private LoginPanel loginPanel;
	private JButton loginBtn;
	private AppChooserPanel appChooserPanel;
	private JButton mainSystemBtn;
	private JButton stockSystemBtn;
	private TabbedPaneMainSystem tabbedPaneMainSystem;
	private TabbedPaneStockSystem tabbedPaneStockSystem;
	
	private JFrame menubarStockSystemFrame;
	private int choosedPanel;
	private String userName;
	private JFrame warningMessageFrame;
	private Cursor cursor;
	
	private DB db;
	private Connection conn;
	private Statement createStatement = null;

    public MainFrame(){
        
        super("Store Management");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
				
		db = new DB();
		conn = db.getConn();
		
        loginPanel = new LoginPanel();
        loginBtn = loginPanel.getLoginButton();      
        appChooserPanel = new AppChooserPanel();
        mainSystemBtn = appChooserPanel.getMainSystemButton();
        stockSystemBtn = appChooserPanel.getStockSystemButton();   
        tabbedPaneMainSystem = new TabbedPaneMainSystem(conn);
        tabbedPaneStockSystem = new TabbedPaneStockSystem(conn);
              
        warningMessageFrame = new JFrame();
        
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
    
    private void loginButtonPressed(){
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
    
    private void mainSystemBtnPressed(){
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
    
    private void stockSystemBtnPressed(){
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
    
    private Boolean checkUser(){
    	String[] textFieldData = loginPanel.getTextFields();
    	
    	String userPassword = "";
    	
    	if(textFieldData != null) {
    		String sql = "SELECT userPassword FROM Users WHERE userName='"+ textFieldData[0] +"'";
    		userName = textFieldData[0];
    		try {
            	loadCreateStatement();
                ResultSet rs = createStatement.executeQuery(sql);
                
                if (rs.next()) {
                	userPassword = rs.getString("userPassword");
                }
                
                if(userPassword.equals(textFieldData[1])) {
                	return true;
                } else {
                	System.out.println();
                	JOptionPane.showMessageDialog(warningMessageFrame, "Incorrect password or user name", "Incorrect", JOptionPane.WARNING_MESSAGE);
                }
           
            } catch (SQLException ex) {
                System.out.println("Error with login");
                System.out.println(" "+ex);
                }    
    	} else {
    		JOptionPane.showMessageDialog(warningMessageFrame, "Please give your user name and password", "Missing", JOptionPane.WARNING_MESSAGE);
    	}
        return false;
}
    
    private  JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menu = new JMenu(userName);
        JMenuItem openStockManager = new JMenuItem("Open Stock Manager");
        JMenuItem changeApp = new JMenuItem("Change App");
        JMenuItem logout = new JMenuItem("Logout");
        JMenuItem exit = new JMenuItem("Exit");
        
        cursor = new Cursor(Cursor.HAND_CURSOR);
        menu.setCursor(cursor);
        openStockManager.setCursor(cursor);
        changeApp.setCursor(cursor);
        logout.setCursor(cursor);
        exit.setCursor(cursor);
        
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
               
                    if (menubarStockSystemFrame == null) {
 		        	   
                      menubarStockSystemFrame = new JFrame();
  		              
                      menubarStockSystemFrame.setLayout(new BorderLayout());

                      menubarStockSystemFrame.add(tabbedPaneStockSystem, BorderLayout.CENTER);
                      menubarStockSystemFrame.setVisible(true);
                      menubarStockSystemFrame.setDefaultCloseOperation(menubarStockSystemFrame.DISPOSE_ON_CLOSE);
                      menubarStockSystemFrame.setResizable(true);
                      menubarStockSystemFrame.pack();
  		              
  		              Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  		              Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
  		              Point newLocation = new Point(middle.x - (menubarStockSystemFrame.getWidth() / 2), 
  		                                            middle.y - (menubarStockSystemFrame.getHeight() / 2));
  		            menubarStockSystemFrame.setLocation(newLocation);
  		              
  		         	menubarStockSystemFrame.addWindowListener(new java.awt.event.WindowAdapter() {
  		  			    @Override
  		  			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
  		  			    	menubarStockSystemFrame = null;
  		  			    }
  		  			});
  	  		    } else {
  	  		    	menubarStockSystemFrame.setVisible(true);
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
                    
                    if(menubarStockSystemFrame != null) {
                    	menubarStockSystemFrame.dispose();
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
                    
                    if(menubarStockSystemFrame != null) {
                    	menubarStockSystemFrame.dispose();
                    }
                }
            }
        });
        
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                
                int action = JOptionPane.showConfirmDialog(MainFrame.this,
                              "Do you really want to exit from the application?",
                              "ComfirmExit", JOptionPane.OK_CANCEL_OPTION);
                if(action == JOptionPane.OK_OPTION){
                System.exit(0);
                }
            }
        });
        return menuBar;
    }
    
    private void loadCreateStatement() {

		if (conn != null && createStatement == null){
	        try {
	             createStatement = conn.createStatement();
	        } catch (SQLException ex) {
	            System.out.println("Error with createStatement");
	            System.out.println(" "+ex);
	        }
		}
	}
}
