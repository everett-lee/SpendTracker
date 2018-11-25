package OptionsMenu;

import Inputter.Inputter;
import InsertSQL.*;
import RetrieveSQL.RetrieveMenu;
import java.sql.Connection;
import java.sql.SQLException;

public class Menu {
    private Inputter inputter;
    private InsertPurchases purchases;
    private InsertType types;
    private RetrieveMenu totals;

    public Menu(Connection connection) throws SQLException {
        this.inputter = new Inputter();
        this.purchases = new InsertPurchases(connection);
        this.types = new InsertType(connection);
        this.totals = new RetrieveMenu(connection);

        listOptions();
        selectOption();
    }

    public void listOptions() {
        System.out.println("Please select from the following: ");
        System.out.println("1: Add items");
        System.out.println("2: Add a type");
        System.out.println("3: Display totals");
        System.out.println(" ");
    }

    public void selectOption() {
        int selection = 0;

        while (selection < 1 || selection > 3) {
            System.out.println("Please select an option 1-3: ");
            selection = inputter.getIntInput();
        }
        executeOption(selection);
        selection = repeat();

        if (selection == 2) {
            listOptions();
            selectOption();
        } else {
            System.out.println("\n Bye!");
        }
    }

    public void executeOption(int selection) {
        switch(selection) {
            case 1:
                purchases.startAdding();
                break;
            case 2:
                types.addType();
                break;
            case 3:
                totals.listOptions();

            default:
                System.out.println("Invalid option");
                break;
        }
    }

    public int repeat() {
        int selection = 0;

        while (selection != 1 && selection != 2) {
            System.out.println("Enter 1 to exit or 2 to return to the menu");
            selection = inputter.getIntInput();
        }

        return selection;
    }
}
