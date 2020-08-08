import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Teacher {
    private static final Scanner scanner = new Scanner(System.in);

    public static void addTeacher() throws SQLException {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            System.out.println("Input name teacher: ");
            String teacherName = scanner.nextLine();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO un_teacher(full_name_teacher) VALUES(?)");
            preparedStatement.setString(1, teacherName);
            preparedStatement.executeUpdate();
            System.out.println("Name teacher was added.");
        }
    }
    public static void editTeacher() throws SQLException {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            System.out.println("Input number row: ");
            int numberRow = Integer.parseInt(scanner.nextLine());

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT ID, full_name_teacher FROM un_teacher WHERE ID = ?");
            preparedStatement.setInt(1, numberRow);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("ID") + "  |  "
                        + resultSet.getString("full_name_teacher"));
            }
            System.out.println("Input new name teacher: ");
            String newTeacherName = scanner.nextLine();

            PreparedStatement preparedStatementUpdate = connection.prepareStatement(
                    "UPDATE un_teacher SET full_name_teacher = ? WHERE ID = ?");
            preparedStatementUpdate.setString(1, newTeacherName);
            preparedStatementUpdate.setInt(2, numberRow);
            preparedStatementUpdate.executeUpdate();
            System.out.println("Name teacher was updated.");
        }
    }
}
