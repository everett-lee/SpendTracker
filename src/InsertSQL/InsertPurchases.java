package InsertSQL;
import Inputter.Inputter;

import java.sql.Connection;

public class InsertPurchases {
    private Connection connection;
    private Inputter inputter;

    public InsertPurchases(Connection connection) {
        this.connection = connection;
        this.inputter = new Inputter();
    }

    public void startAdding() {
        addPurchases();

        boolean continueAdding = addAdditional();

        while (continueAdding) {
            addPurchases();
            continueAdding = addAdditional();
        }
    }

   public void addPurchases() {
        System.out.println("How many items would you like to add?");
        int noOfItems = inputter.getIntInput();

        for (int i = 0; i < noOfItems; i++) {
            InsertItem item = new InsertItem(connection);
        }
        System.out.println("All items added");
    }

    public boolean addAdditional() {
        System.out.println("Would you like to add more items y/n?");
        String response = inputter.getStringInput().toLowerCase();

        if (response.equals("y")) {
            return true;
        }
        return false;
    }
}
