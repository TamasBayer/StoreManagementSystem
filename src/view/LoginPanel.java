package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class LoginPanel extends JPanel{
	
	private JLabel userNameLabel;
    private JTextField userNameField;
    private JLabel passwordLabel;
    private JTextField passwordField;
    private JButton loginButton;
    private Cursor cursor;
    
    String[] textFields;
        
    public LoginPanel(){
        
    	userNameLabel = new JLabel("User name:"); 
    	userNameField = new JTextField(10);
    	passwordLabel = new JLabel("Password: ");
    	passwordField = new JTextField(10);
    	loginButton = new JButton("Login");
        
    	setPreferredSize(new Dimension(300, 200));
        Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        layoutComponents();
        
        cursor = new Cursor(Cursor.HAND_CURSOR);
        loginButton.setCursor(cursor);
        
    }
    
    
    public String[] getTextFields(){
      
        textFields = null;
        
        
      if(!userNameField.getText().isEmpty() && !passwordField.getText().isEmpty() && !passwordField.getText().isEmpty() && !passwordField.getText().isEmpty()){
        textFields = new String[4];
          
        textFields[0] = userNameField.getText();
        textFields[1] = passwordField.getText();

        
      } 
          
        return textFields;
    } 
    
    public JButton getLoginButton() {
		return loginButton;
	}
    
     public void layoutComponents(){
        setLayout(new GridBagLayout());
        
        GridBagConstraints gc = new GridBagConstraints();
        
        /////////// First row /////////////////
     
        gc.gridy = 0;
        
        gc.weightx = 1;
        gc.weighty = 0;
        
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 20, 5);
        add(userNameLabel, gc);
        
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 20, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(userNameField, gc);
        
        /////////// Second row /////////////////
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty =  0;
        
        gc.gridx = 0;
        gc.insets = new Insets(0, 0 , 20, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(passwordLabel, gc);
        
        gc.gridy = 1;
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 20, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(passwordField, gc);
        
        /////////// Third row /////////////////
        
	    gc.gridy++;
	      
	    gc.weightx = 1;
	    gc.weighty =  0;
	      
        gc.gridx = 1;
        gc.insets = new Insets(0, 50, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(loginButton, gc);
        
     }

}
