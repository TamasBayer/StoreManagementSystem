package view.StockControlPanelStockS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.Inventory;


public class StockControlPanelStockS extends JPanel{

	private ControlPaneStockS controlPane;
    private StockControlButtonsStockS stockControl;
    private int itemID;
    private String fromStock;
    private int quantity;
    private String toStock;
    private JButton okBtn;
    private JButton cancelBtn;
    private int dataIsChanged;
    private JFrame frame;
    private ImageIcon stockControlIcon;
    private String[] textFieldsData;
    private Connection conn;
    private Statement createStatement = null;
    private Cursor cursor;
    
    String stockName1;
    String stockName2;
    String stockName3;

    
    public StockControlPanelStockS(){
    	
    	stockName1 = "A01";
    	stockName2 = "A02";
    	stockName3 = "A03";
        
        controlPane = new ControlPaneStockS();
        stockControl = new StockControlButtonsStockS();
        
        okBtn = stockControl.getOkButton();
        cancelBtn = stockControl.getCancelButton();
        
        frame = new JFrame();
        stockControlIcon = new ImageIcon("D:/MyPrograms/eclipse-workspace/Stock Management/Images/StockControl.png");
        
        Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new BorderLayout());
        
        add(stockControl, BorderLayout.NORTH);
        add(controlPane, BorderLayout.CENTER);
        
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        okBtn.setCursor(cursor);
        cancelBtn.setCursor(cursor);
        
        cancelButtonPressed();
        okButtonPressed();
    }
    
    public void loadTextFields(){
        textFieldsData = controlPane.getTextFields();
       
        if(textFieldsData != null){
             fromStock = textFieldsData[1];
             toStock = textFieldsData[3];
             
             try{
                 itemID =  Integer.parseInt(textFieldsData[0]);
             } catch (NumberFormatException ex) {
                  JOptionPane.showMessageDialog(frame, "Invalid Item-ID.", "Missing", JOptionPane.WARNING_MESSAGE);
             }
             
             try{
                 quantity = Integer.parseInt(textFieldsData[2]);
             } catch (NumberFormatException ex) {
                  JOptionPane.showMessageDialog(frame, "Invalid quantity.", "Missing", JOptionPane.WARNING_MESSAGE);
             }
             
             if(quantity != 0){

                 dataIsChanged = stockControll(fromStock, itemID, quantity, toStock);
                 
                 
                 if(dataIsChanged == 1){
                     controlPane.clearTextFieldsContent();
                     JOptionPane.showMessageDialog(frame, "Inventory changed.", "Ok", JOptionPane.INFORMATION_MESSAGE, stockControlIcon);
                 }
             }
        } else {
           JOptionPane.showMessageDialog(frame, "Data missing.", "Missing", JOptionPane.WARNING_MESSAGE);
       }
        
     }
    
    public void cancelButtonPressed(){
        cancelBtn.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent e) {
                
                controlPane.clearTextFieldsContent();
            }
        });

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
    
    public void setConn(Connection conn) {
		this.conn = conn;
	}
    
    public void okButtonPressed(){
        okBtn.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent e) {
            	loadTextFields();
                
            }
        });

    }
    
 public int stockControll(String fStock, int uIID, int uQuantity, String tStock){
        
        
        int userItemID = uIID;
        String fromStock = fStock;
        int userQuantity = uQuantity;
        String toStock = tStock;
        
        int stockIsChanged = 0;
        int stockIsFound = 0;
        int realQuantity;
        int newQuantity;
        
        
        String fromSql = "SELECT itemQuantity FROM Inventory WHERE stockName = '" + fromStock + "' AND itemID = " + userItemID + "";
       
         
        
        if((stockName1.equals(toStock) || stockName2.equals(toStock) || stockName3.equals(toStock))){

            try{
            	loadCreateStatement();
                ResultSet rs = createStatement.executeQuery(fromSql);

                if (rs.next()){
                 realQuantity = rs.getInt("itemQuantity");

                 stockIsFound++;

                 if(realQuantity >= userQuantity){
                     newQuantity = realQuantity - userQuantity;
                     
                     System.out.println(newQuantity);

                     if (newQuantity > 0){
                     String StockMinusSql = "UPDATE Inventory SET itemQuantity = " + newQuantity + " WHERE stockName = '" + fromStock + "' AND itemID = " + userItemID +"";
                     createStatement.executeUpdate(StockMinusSql);
                     
                     } else if (newQuantity == 0){
                     String StockDeleteSql = "DELETE FROM Inventory WHERE stockName = '" + fromStock + "' AND itemID = " + userItemID +"";
                     createStatement.executeUpdate(StockDeleteSql);    

                     
                     }
                    
                     toStock(userItemID, userQuantity, toStock);
                     stockIsChanged++;        
                 } else{
                     JOptionPane.showMessageDialog(frame, "This quantity is not available. ", "Missing", JOptionPane.WARNING_MESSAGE);
                 }


                }

                if(stockIsFound == 0)
                 JOptionPane.showMessageDialog(frame, "From stock code or Item-ID is not found.", "Missing", JOptionPane.WARNING_MESSAGE);

            } catch (SQLException ex) {
                    System.out.println("Error with stockControll");
                    System.out.println(" "+ex);
                    }
              } else {
                JOptionPane.showMessageDialog(frame, "To stock is not found.", "Missing", JOptionPane.WARNING_MESSAGE);
        }
        
        return stockIsChanged;
    } 






            public void toStock(int uIID, int uQuantity, String tStock){

                int userItemID = uIID;
                String toStock = tStock;
                int userQuantity = uQuantity;

                String itemName = null;
                int itemIsFound = 0;
                int realItemID;
                int realQuantity;
                int newQuantity;

                

                String toSql = "SELECT itemID, itemQuantity FROM Inventory WHERE stockName = '" + toStock + "'";

                try {
                    ResultSet rs = createStatement.executeQuery(toSql);


                while (rs.next()){
                    realQuantity = rs.getInt("itemQuantity");
                    realItemID = rs.getInt("itemID");

                    if (userItemID == realItemID){
                        itemIsFound++;
                        newQuantity = realQuantity + userQuantity;

                        String plusStockSql = "UPDATE Inventory SET itemQuantity = " + newQuantity + " WHERE stockName = '" + toStock +"' AND itemID = " + userItemID + "";
                        createStatement.executeUpdate(plusStockSql);
                        break;
                    }
                }
                    if (itemIsFound == 0){
                        String newStockItemNameSql = "SELECT itemName FROM Inventory WHERE goodsID = " + userItemID + "";
                        ResultSet rsItemName = createStatement.executeQuery(newStockItemNameSql);
                        
                        if (rsItemName.next()){
                            itemName = rsItemName.getString("itemName");

                            String newStockSql = "INSERT INTO Inventory VALUES ('" + toStock + "', " + userItemID + ", '" + itemName + "', " + userQuantity + " )";
                            createStatement.executeUpdate(newStockSql);
                         }
                    } else if(itemIsFound == 0){
                        System.out.println("to Stock or Goods is not found");

                    }

                } catch (SQLException ex) {
                    System.out.println("Error with toStock ");
                    System.out.println(" "+ex);
                    }   
    }

			
}
