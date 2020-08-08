import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Subject {
    private static final Scanner scanner = new Scanner(System.in);
    public static void addSubject() throws SQLException {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {

            System.out.println("Input subject: ");
            String subject = scanner.nextLine();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO un_subject(subject_title) VALUES(?)");
            preparedStatement.setString(1, subject);
            preparedStatement.executeUpdate();
            System.out.println("Subject was added.");
        }
    }
    public static void editSubject() throws SQLException {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            System.out.println("Input number row: ");
            int numberRow = Integer.parseInt(scanner.nextLine());

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT ID, subject_title FROM un_subject WHERE ID = ?");
            preparedStatement.setInt(1, numberRow);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("ID") + "  |  "
                        + resultSet.getString("subject_title"));
            }
            System.out.println("Input new subject: ");
            String newSubject = scanner.nextLine();

            PreparedStatement preparedStatementUpdate = connection.prepareStatement(
                    "UPDATE un_subject SET subject_title = ? WHERE ID = ?");
            preparedStatementUpdate.setString(1, newSubject);
            preparedStatementUpdate.setInt(2, numberRow);
            preparedStatementUpdate.executeUpdate();
            System.out.println("Subject was updated.");
        }
    }
}
