package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
                ResultSet rs = dbmd.getTables(null, "APP", "USERS", null);
                if (!rs.next()){
                    createStatement.execute("CREATE TABLE Users(userName varchar(20) PRIMARY KEY, userPassword varchar(20))");
                    createStatement.execute("CREATE TABLE Goods(itemID int PRIMARY KEY, itemName varchar(20))");
                    createStatement.execute("CREATE TABLE SellOrders(sellOrderID int PRIMARY KEY, soldFor varchar(20), sellOrderDatum varchar(20))");
                    createStatement.execute("CREATE TABLE SoldGoods(sellOrderID int REFERENCES SellOrders(sellOrderID), soldItemID int REFERENCES Goods(itemID), itemName varchar(20), soldQuantity int NOT NULL, pickedQuantity int)");
                    createStatement.execute("CREATE TABLE ReadySellOrders(sellOrderID int PRIMARY KEY, soldFor varchar(20), sellOrderDatum varchar(20))");
                    createStatement.execute("CREATE TABLE Orders(orderID int PRIMARY KEY, orderedFrom varchar(20), orderDatum varchar(20))");
                    createStatement.execute("CREATE TABLE OrderedGoods(orderID int REFERENCES Orders(orderID), orderedItemID int REFERENCES Goods(itemID), itemName varchar(20), orderedQuantity int NOT NULL, shippedQuantity int)");
                    createStatement.execute("CREATE TABLE ReadyOrders(orderID int PRIMARY KEY, orderedFrom varchar(20), orderDatum varchar(20))");
                    
                    //// Create Stocks Table ////
                    createStatement.execute("CREATE TABLE Stock1(itemID int REFERENCES Goods(itemID), itemName varchar(20), itemQuantity int)");
                    createStatement.execute("CREATE TABLE Stock2(itemID int REFERENCES Goods(itemID), itemName varchar(20), itemQuantity int)");
                    createStatement.execute("CREATE TABLE Stock3(itemID int REFERENCES Goods(itemID), itemName varchar(20), itemQuantity int)");
                    createStatement.execute("CREATE TABLE Stock4(itemID int REFERENCES Goods(itemID), itemName varchar(20), itemQuantity int)");
                    createStatement.execute("CREATE TABLE Stock5(itemID int REFERENCES Goods(itemID), itemName varchar(20), itemQuantity int)");
                    createStatement.execute("CREATE TABLE Stock6(itemID int REFERENCES Goods(itemID), itemName varchar(20), itemQuantity int)");
                    createStatement.execute("CREATE TABLE Stock7(itemID int REFERENCES Goods(itemID), itemName varchar(20), itemQuantity int)");
                    createStatement.execute("CREATE TABLE Stock8(itemID int REFERENCES Goods(itemID), itemName varchar(20), itemQuantity int)");
                    createStatement.execute("CREATE TABLE Stock9(itemID int REFERENCES Goods(itemID), itemName varchar(20), itemQuantity int)");
                    createStatement.execute("CREATE TABLE Stock10(itemID int REFERENCES Goods(itemID), itemName varchar(20), itemQuantity int)");
                }
            } catch (SQLException ex) {
                System.out.println("Error with resultTable");
                System.out.println(" "+ex); 
            }
            
       }
    
    public Connection getConn() {
		return conn;
	}
}
