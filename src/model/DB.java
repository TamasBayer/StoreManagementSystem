package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DB {

	String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    String URL = "jdbc:derby:sampleDB;create=true";
    String USERNAME = "";
    String PASSWORD = "";
 
     Connection conn = null;
     Statement createStatement = null;
     DatabaseMetaData dbmd = null;
     
     
    public DB(){
        
         try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Done");
        } catch (SQLException ex) {
            System.out.println("Error with Connection");
            System.out.println(" "+ex);
        }
        
        if (conn != null){
            try {
                 createStatement = conn.createStatement();
            } catch (SQLException ex) {
                System.out.println("Error with createStatement");
                System.out.println(" "+ex);
            }
            
        }
            
            try {
                dbmd = conn.getMetaData();
            } catch (SQLException ex) {
                System.out.println("Error with DatabaseMetaData");
                System.out.println(" "+ex);
            }
            
           
            
            try {
                ResultSet rs = dbmd.getTables(null, "APP", "INVENTORY", null);
                if (!rs.next()){
                    createStatement.execute("CREATE TABLE Users(userName varchar(20) PRIMARY KEY, userPassword varchar(20))");
                    createStatement.execute("CREATE TABLE Goods(itemID INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1000, INCREMENT BY 1), itemName varchar(20))");
                    createStatement.execute("CREATE TABLE SellOrders(sellOrderID INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 300000, INCREMENT BY 1), soldFor varchar(20), sellOrderDatum varchar(20))");
                    createStatement.execute("CREATE TABLE SoldGoods(sellOrderID int, soldItemID int REFERENCES Goods(itemID), itemName varchar(20), soldQuantity int NOT NULL, pickedQuantity int)");
                    createStatement.execute("CREATE TABLE ReadySellOrders(sellOrderID int PRIMARY KEY, soldFor varchar(20), sellOrderDatum varchar(20))");
                    createStatement.execute("CREATE TABLE Orders(orderID INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 200000, INCREMENT BY 1), orderedFrom varchar(20), orderDatum varchar(20))");
                    createStatement.execute("CREATE TABLE OrderedGoods(orderID int, orderedItemID int REFERENCES Goods(itemID), itemName varchar(20), orderedQuantity int NOT NULL, shippedQuantity int)");
                    createStatement.execute("CREATE TABLE ReadyOrders(orderID int PRIMARY KEY, orderedFrom varchar(20), orderDatum varchar(20))");
                    
                    //// Create Stocks Tables ////
                    createStatement.execute("CREATE TABLE StockNames(stockName varchar(10) PRIMARY KEY)");
                    createStatement.execute("CREATE TABLE Inventory(stockName varchar(10) REFERENCES StockNames(stockName), itemID int REFERENCES Goods(itemID), itemName varchar(20), itemQuantity int)");
                    
                }
            } catch (SQLException ex) {
                System.out.println("Error with resultTable");
                System.out.println(" "+ex); 
            }
            
       }
    
    public void addUser(Users user){
        try {
              String sql = "INSERT INTO Users VALUES (?,?)";
              PreparedStatement preparedStatement = conn.prepareStatement(sql);
              preparedStatement.setString(1, user.getUserName());
              preparedStatement.setString(2, user.getUserPassword());

              preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van az User hozzáadásakor");
            System.out.println(""+ex);
        }
    }
    
    public void addStock(StockNames user){
        try {
              String sql = "INSERT INTO StockNames VALUES (?)";
              PreparedStatement preparedStatement = conn.prepareStatement(sql);
              preparedStatement.setString(1, user.getStockName());

              preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a Stock hozzáadásakor");
            System.out.println(""+ex);
        }
    }
    
    
    public Connection getConn() {
		return conn;
	}
    
    
    
    public ArrayList<Goods> getAllGoods(){
        String sql = "SELECT Goods.itemID, Goods.itemName, SUM (Inventory.itemQuantity) AS QuantityInWarehause FROM Goods, Inventory WHERE Goods.itemID=Inventory.itemID GROUP BY Goods.itemID, Goods.itemName";
        ArrayList<Goods> goods = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            goods = new ArrayList<>();

        while (rs.next()){
        	Goods actualItem = new Goods(rs.getInt("itemID"), rs.getString("itemName"), rs.getInt("QuantityInWarehause"));
        	goods.add(actualItem);
            }
        } catch (SQLException ex) {
            System.out.println("Error with getAllGoods");
            System.out.println(" "+ex);
            }    

         return goods;
}
}
