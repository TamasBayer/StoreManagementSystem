package view.StockControlPanelStockS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class StockControlPanelStockS extends JPanel{

	private ControlPaneStockS controlPane;
    private StockControlButtonsStockS stockControl;
    private int goodsID;
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

    
    public StockControlPanelStockS(){
    	
    	
        
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

    }
}
