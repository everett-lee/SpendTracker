package InsertSQL;

import Inputter.Inputter;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertType {
    private String type;
    private Connection connection;
    private Inputter inputter;

    public InsertType(Connection connection) {
        this.connection = connection;
        inputter = new Inputter();
    }

    public void addType() {
        setType();
        checkCorrect();

        try {
            addToDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setType() {
        System.out.println("Please enter the category type");
        this.type = inputter.getStringInput().toUpperCase();
    }

    public void checkCorrect() {
        System.out.println("Is this correct, y/n?:");
        System.out.println("Name: " + this.type);

        String response = inputter.getStringInput().toLowerCase();

        if (!response.equals("y")) {
            System.out.println("Please re-enter information");
            setType();
        }
    }

    public void addToDatabase() throws SQLException {
        Statement statement = connection.createStatement();

        String sqlStatement = "INSERT into itemtypes "
                + "(type_id, type) ";
        sqlStatement += String.format("values(null, '%s')", this.type);

        statement.executeUpdate(sqlStatement);
        System.out.println("Update complete \n");
    }


}
