import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Group {
    private static final Scanner scanner = new Scanner(System.in);

    public static void addGroup() throws SQLException {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            System.out.println("Input group number: ");
            int groupNumber = scanner.nextInt();


            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO un_group(group_number) VALUES(?)");
            preparedStatement.setInt(1, groupNumber);
            preparedStatement.executeUpdate();
            System.out.println("Number group was added.");
        }
    }

    public static void editGroup() throws SQLException {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            System.out.println("Input number row: ");
            int numberRow = Integer.parseInt(scanner.nextLine());

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT ID, group_number FROM un_group WHERE ID = ?");
            preparedStatement.setInt(1, numberRow);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("ID") + "  |  "
                        + resultSet.getString("group_number"));
            }
            System.out.println("Input new number group: ");
            int newNumberGroup = scanner.nextInt();

            PreparedStatement preparedStatementUpdate = connection.prepareStatement(
                    "UPDATE un_group SET group_number = ? WHERE ID = ?");
            preparedStatementUpdate.setInt(1, newNumberGroup);
            preparedStatementUpdate.setInt(2, numberRow);
            preparedStatementUpdate.executeUpdate();
            System.out.println("Number group was updated. ");
        }
    }
}
