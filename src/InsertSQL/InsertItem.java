package InsertSQL;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import Inputter.*;

public class InsertItem {
    private Connection connection;
    private Inputter inputter;
    private String itemName;
    private float itemPrice;
    private int itemTypeId;
    private LocalDate date;
    private Map itemTypeMap;

    public InsertItem(Connection connection) {
        this.connection = connection;
        inputter = new Inputter();

        // item variables
        setItemValues();
        this.itemTypeMap = new HashMap<Integer, String>();
        this.createHashMap();
        this.date = LocalDate.now();
        checkCorrect();

        try {
            addToDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setItemValues() {
        this.itemName = setItemName();
        this.itemPrice = setItemPrice();
        this.itemTypeId = setItemTypeId();
    }

    public String setItemName() {
        System.out.println("Please enter the item name: ");
        return inputter.getStringInput();
    }

    public float setItemPrice() {
        System.out.println("Please enter the item price: ");
        return inputter.getFloatInput();
    }

    public int setItemTypeId() {
        System.out.println("Please enter number for corresponding item type: ");
        System.out.println("1: Food and drink");
        System.out.println("2: Unnecessary (dear lord! what did you do?)");
        System.out.println("3: Toiletries");
        System.out.println("4: Office stuff");

        int itemListId = inputter.getIntInput();

        while (itemListId < 1 || itemListId > 4) {
            System.out.println("That was not a valid option, please enter again");
            itemListId = inputter.getIntInput();
        }

        return itemListId;
    }

    public void checkCorrect() {
        System.out.println("Is this correct, y/n?:");
        System.out.println("Name: " + this.itemName + "\n" + "Price: " + this.itemPrice + "\n"
                + "Type: " + this.itemTypeMap.get(this.itemTypeId));

        String response = inputter.getStringInput().toLowerCase();

        if (!response.equals("y")) {
            System.out.println("Please re-enter information");
            setItemValues();
        }
    }

    public void addToDatabase() throws SQLException {
        Statement statement = connection.createStatement();

        String sqlStatement = "INSERT into items "
                + "(item_id, type_id, cost, purchase_date, name) ";
        sqlStatement += String.format("values(null, %d, %.2f, '%s', '%s')", this.itemTypeId, this.itemPrice, this.date, this.itemName);

        statement.executeUpdate(sqlStatement);
        System.out.println("Update complete \n");
    }

    public void createHashMap() {
        this.itemTypeMap.put(1, "Food and Drink");
        this.itemTypeMap.put(2, "Unnecessary");
        this.itemTypeMap.put(3, "Toiletries");
        this.itemTypeMap.put(4, "Office Stuff");
    }

}
