import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Delete {
    private static final Scanner scanner = new Scanner(System.in);

    public static void deleteRow() throws SQLException {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            System.out.println("Input number row: ");
            int deleteNumberRow = scanner.nextInt();

            PreparedStatement preparedStatementCalender = connection.prepareStatement(
                    "DELETE FROM un_calender WHERE ID = ?");
            PreparedStatement preparedStatementGroup = connection.prepareStatement(
                    "DELETE FROM un_group WHERE ID = ?");
            PreparedStatement preparedStatementTeacher = connection.prepareStatement(
                    "DELETE FROM un_teacher WHERE ID = ?");
            PreparedStatement preparedStatementSubject = connection.prepareStatement(
                    "DELETE FROM un_subject WHERE ID = ?");
            PreparedStatement preparedStatementAuditorium = connection.prepareStatement(
                    "DELETE FROM un_auditorium WHERE ID = ?");

            preparedStatementCalender.setInt(1, deleteNumberRow);
            preparedStatementGroup.setInt(1, deleteNumberRow);
            preparedStatementTeacher.setInt(1, deleteNumberRow);
            preparedStatementSubject.setInt(1, deleteNumberRow);
            preparedStatementAuditorium.setInt(1, deleteNumberRow);

            preparedStatementCalender.executeUpdate();
            preparedStatementGroup.executeUpdate();
            preparedStatementTeacher.executeUpdate();
            preparedStatementSubject.executeUpdate();
            preparedStatementAuditorium.executeUpdate();

        }
    }
}
