package RetrieveSQL;

import Inputter.Inputter;

import java.sql.Connection;
import java.sql.SQLException;

public class RetrieveMenu {
    private Inputter inputter;
    private RetrieveItems retrieveItems;

    public RetrieveMenu(Connection connection) throws SQLException {
        this.inputter = new Inputter();
        this.retrieveItems = new RetrieveItems(connection);
    }

    public void listOptions() {
        System.out.println("Please select from the following: ");
        System.out.println("1: Print purchases in last month");
        System.out.println("2: Print total spend for last month");
        System.out.println("3: Print monthly totals");
        System.out.println("4: Print all time total");
        System.out.println(" ");

        try {
            selectOption();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectOption() throws SQLException {
        int selection = 0;

        while (selection < 1 || selection > 4) {
            System.out.println("Please select an option 1-4: ");
            selection = inputter.getIntInput();
        }

        executeOption(selection);
    }

    public void executeOption(int selection) throws SQLException {
        switch(selection) {
            case 1:
                retrieveItems.lastMonthItems();
                break;
            case 2:
                retrieveItems.lastMonthTotal();
                break;
            case 3:
                retrieveItems.monthlyTotals();
                break;
            case 4:
                retrieveItems.allTotal();
                break;
            default:
                System.out.println("Invalid option");
                break;
        }

        System.out.println("Select another option y/n?");
        String response = inputter.getStringInput().toLowerCase();

        if (response.equals("y")) {
            listOptions();
        }

    }
}
