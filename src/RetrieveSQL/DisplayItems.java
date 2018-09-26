package RetrieveSQL;
import java.sql.*;

//add join to get itemtype
public class DisplayItems {
    private Connection connection;

    public DisplayItems(Connection connection) {
        this.connection = connection;
    }

    public void printer(ResultSet results) throws SQLException {

        while (results.next()) {
            String itemName = results.getString("name");
            float itemCost = results.getFloat("cost");
            String purchaseDate = results.getString("purchase_date");

            System.out.println(String.format("Item: %s | Cost: £%.2f | Purchase date %s", itemName, itemCost, purchaseDate));
        }

    }
    
    public void printTotal(ResultSet results) throws SQLException {
        while (results.next()) {
            System.out.println("Total spend: £" + results.getFloat(1));
        }
    }

    public void printMonthlyTotal(ResultSet results) throws SQLException {
        while (results.next()) {
            System.out.println(results.getString("year") + "/" + results.getString("month") + ": £" + results.getFloat(3));
        }
     }
}