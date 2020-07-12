package view.StockControlPanelStockS;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ControlPaneStockS extends JPanel{

	 private JLabel goodsIDLabel;
	    private JTextField goodsIDField;
	    private JLabel goodsFromStockLabel;
	    private JTextField goodsFromStockField;
	    private JLabel quantityLabel;
	    private JTextField quantityField;
	    private JLabel goodsToStockLabel;
	    private JTextField goodsToStockField;
	    String[] textFields;
	        
	    public ControlPaneStockS(){
	        
	        goodsIDLabel = new JLabel("Item-ID"); 
	        goodsIDField = new JTextField(10);
	        goodsFromStockLabel = new JLabel("From");
	        goodsFromStockField = new JTextField(10);
	        quantityLabel = new JLabel("Quantity");
	        quantityField = new JTextField(5);
	        goodsToStockLabel = new JLabel("To");
	        goodsToStockField = new JTextField(10);
	        
	        Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
	        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
	        
	        layoutComponents();
	    }
	    
	    public void clearTextFieldsContent(){
	        goodsIDField.setText(null);
	        goodsFromStockField.setText(null);
	        quantityField.setText(null);
	        goodsToStockField.setText(null);
	    }
	    
	    public String[] getTextFields(){
	      
	        textFields = null;
	        
	        
	      if(!goodsIDField.getText().isEmpty() && !goodsFromStockField.getText().isEmpty() && !quantityField.getText().isEmpty() && !goodsToStockField.getText().isEmpty()){
	        textFields = new String[4];
	          
	        textFields[0] = goodsIDField.getText();
	        textFields[1] = goodsFromStockField.getText();
	        textFields[2] = quantityField.getText();
	        textFields[3] = goodsToStockField.getText();
	        
	      } 
	          
	        return textFields;
	    } 
	    
	    private void layoutComponents(){
	        setLayout(new GridBagLayout());
	        
	        GridBagConstraints gc = new GridBagConstraints();
	        
	        /////////// First row /////////////////
	     
	        gc.gridy = 0;
	        
	        gc.weightx = 1;
	        gc.weighty = 0;
	        
	        gc.gridx = 0;
	        gc.fill = GridBagConstraints.NONE;
	        gc.anchor = GridBagConstraints.LINE_END;
	        gc.insets = new Insets(0, 0, 0, 5);
	        add(goodsIDLabel, gc);
	        
	        gc.gridx = 1;
	        gc.gridy = 0;
	        gc.insets = new Insets(0, 0, 0, 0);
	        gc.anchor = GridBagConstraints.LINE_START;
	        add(goodsIDField, gc);
	        
	        /////////// Second row /////////////////
	        gc.gridy++;
	        
	        gc.weightx = 1;
	        gc.weighty =  0;
	        
	        gc.gridx = 0;
	        gc.insets = new Insets(0, 0 , 0, 5);
	        gc.anchor = GridBagConstraints.LINE_END;
	        add(goodsFromStockLabel, gc);
	        
	        gc.gridy = 1;
	        gc.gridx = 1;
	        gc.insets = new Insets(0, 0, 0, 0);
	        gc.anchor = GridBagConstraints.LINE_START;
	        add(goodsFromStockField, gc);
	        
	        /////////// Third row /////////////////
	        gc.gridy++;
	        
	        gc.weightx = 1;
	        gc.weighty =  0;
	        
	        gc.gridx = 0;
	        gc.insets = new Insets(0, 0 , 0, 5);
	        gc.anchor = GridBagConstraints.LINE_END;
	        add(quantityLabel, gc);
	        
	        gc.gridx = 1;
	        gc.insets = new Insets(0, 0, 0, 0);
	        gc.anchor = GridBagConstraints.LINE_START;
	        add(quantityField, gc);
	        
	        /////////// Fourth row /////////////////
	        gc.gridy++;
	        
	        gc.weightx = 1;
	        gc.weighty =  0;
	        
	        gc.gridx = 0;
	        gc.insets = new Insets(0, 0 , 0, 5);
	        gc.anchor = GridBagConstraints.LINE_END;
	        add(goodsToStockLabel, gc);
	        
	        gc.gridx = 1;
	        gc.insets = new Insets(0, 0, 0, 0);
	        gc.anchor = GridBagConstraints.LINE_START;
	        add(goodsToStockField, gc);
	             
	     }
}
