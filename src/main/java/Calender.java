import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Calender {
    private static final Scanner scanner = new Scanner(System.in);

    public static void addDate() throws SQLException {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {

            System.out.println("Input weekday(MONDAY, TUESDAY, WEDNESDAY, THURSDAY or FRIDAY): ");
            String weekday = scanner.nextLine();

            System.out.println("Input number pair(FIRST, SECOND, THIRD): ");
            String pairNumber = scanner.nextLine();

            switch (pairNumber) {
                case "FIRST":
                    pairNumber = "9:00-10:45";
                    break;
                case "SECOND":
                    pairNumber = "11:00-12:45";
                    break;
                case "THIRD":
                    pairNumber = "14:00-15:45";
                    break;
            }

            String weekdayAndPairNumber = weekday + " " + pairNumber;
            scanner.close();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO un_calender(weekday_and_number_pair) VALUES(?)");
            preparedStatement.setString(1, weekdayAndPairNumber);
            preparedStatement.executeUpdate();
            System.out.println("Date and time was added.");
        }
    }

    public static void editDate() throws SQLException {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {

            System.out.println("Input number row: ");
            int numberRow = Integer.parseInt(scanner.nextLine());

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT ID, weekday_and_number_pair FROM un_calender WHERE ID = ?");
            preparedStatement.setInt(1, numberRow);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("ID") + "  |  "
                        + resultSet.getString("weekday_and_number_pair"));
            }

            System.out.println("Input new weekday(MONDAY, TUESDAY, WEDNESDAY, THURSDAY or FRIDAY): ");
            String weekday = scanner.nextLine();

            System.out.println("Input new number pair(FIRST, SECOND, THIRD): ");
            String pairNumber = scanner.nextLine();

            switch (pairNumber) {
                case "FIRST":
                    pairNumber = "9:00-10:45";
                    break;
                case "SECOND":
                    pairNumber = "11:00-12:45";
                    break;
                case "THIRD":
                    pairNumber = "14:00-15:45";
                    break;
            }
            String weekdayAndPairNumber = weekday + " " + pairNumber;
            PreparedStatement preparedStatementUpdate = connection.prepareStatement(
                    "UPDATE un_calender SET weekday_and_number_pair = ? WHERE ID = ?");
            preparedStatementUpdate.setString(1, weekdayAndPairNumber);
            preparedStatementUpdate.setInt(2, numberRow);
            preparedStatementUpdate.executeUpdate();
            System.out.println("Row was updated.");
        }
    }
}
