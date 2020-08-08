import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Auditorium {
     static Scanner scanner = new Scanner(System.in);

    public static void addAuditorium(){
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            System.out.println("Input auditorium number: ");
            int auditoriumGroup = scanner.nextInt();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO un_auditorium(auditorium_number) VALUES(?)");
            preparedStatement.setInt(1, auditoriumGroup);
            preparedStatement.executeUpdate();
            System.out.println("Number auditorium was updated.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void editAuditorium() throws SQLException{
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            System.out.println("Input number row: ");
            int numberRow = Integer.parseInt(scanner.nextLine());

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT ID, auditorium_number FROM un_auditorium WHERE ID = ?");
            preparedStatement.setInt(1, numberRow);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("ID") + "  |  "
                        + resultSet.getString("auditorium_number"));
            }
            System.out.println("Input new number auditorium: ");
            int newNumberAuditorium = scanner.nextInt();

            PreparedStatement preparedStatementUpdate = connection.prepareStatement(
                    "UPDATE un_auditorium SET auditorium_number = ? WHERE ID = ?");
            preparedStatementUpdate.setInt(1, newNumberAuditorium);
            preparedStatementUpdate.setInt(2, numberRow);
            preparedStatementUpdate.executeUpdate();
            System.out.println("Number auditorium was updated. ");
        }
    }
}
