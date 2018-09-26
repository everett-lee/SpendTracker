package RetrieveSQL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RetrieveItems {
    private Connection connection;
    private LocalDate date;
    private DisplayItems displayItems;
    private LocalDate lastMonth;
    private DateTimeFormatter formatter;
    private String strDate;

    public RetrieveItems(Connection connection) {
        this.connection = connection;
        this.date = LocalDate.now();
        this.displayItems = new DisplayItems(connection);

        // Calculate and format date minus one month
        this.lastMonth = date.minusMonths(1);
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.strDate = formatter.format(lastMonth);
    }

    public void lastMonthItems() throws SQLException {
        PreparedStatement lastMonthQuery =
                connection.prepareStatement("SELECT * FROM items WHERE purchase_date > ?");
        lastMonthQuery.setString(1, strDate);

        ResultSet results = lastMonthQuery.executeQuery();

        displayItems.printer(results);
    }

    public void lastMonthTotal() throws SQLException {
        PreparedStatement lastMonthTotalQuery =
                connection.prepareStatement("SELECT SUM(cost) FROM items WHERE purchase_date > ?");
        lastMonthTotalQuery.setString(1, strDate);

        ResultSet result = lastMonthTotalQuery.executeQuery();

        displayItems.printTotal(result);
    }

    public void monthlyTotals() throws SQLException {
        PreparedStatement monthlyTotalQuery =
                connection.prepareStatement("select extract(year from purchase_date) as year, extract(month from purchase_date) as month," +
                        " sum(cost) from items group by extract(year from purchase_date), extract(month from purchase_date)" +
                        "order by year, month;");

        ResultSet results = monthlyTotalQuery.executeQuery();

        displayItems.printMonthlyTotal(results);

    }

    public void allTotal() throws SQLException {
        PreparedStatement totalQuery =
                connection.prepareStatement("SELECT SUM(cost) FROM items");

        ResultSet result = totalQuery.executeQuery();

        displayItems.printTotal(result);
    }
}
