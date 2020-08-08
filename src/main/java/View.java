import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class View {
    public static void view() throws SQLException {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT c.ID, weekday_and_number_pair, group_number, subject_title, full_name_teacher, auditorium_number " +
                            "FROM un_calender c " +
                            "JOIN un_group g ON g.ID = c.id_group " +
                            "JOIN un_subject s ON s.ID = c.id_subject " +
                            "JOIN un_teacher t ON t.ID = c.id_teacher " +
                            "JOIN un_auditorium a ON a.ID = c.id_auditorium");
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.print("   ID         Date and Time      Group        Subject           " +
                    "       Name teacher            Audit");

            while (resultSet.next()) {
                System.out.println();
                System.out.printf("%5s", resultSet.getString("ID"));
                System.out.printf("%25s", resultSet.getString("weekday_and_number_pair"));
                System.out.printf("%7s", resultSet.getInt("group_number"));
                System.out.printf("%20s", resultSet.getString("subject_title"));
                System.out.printf("%35s", resultSet.getString("full_name_teacher"));
                System.out.printf("%7s", resultSet.getInt("auditorium_number"));
            }
            System.out.println();
        }
    }
}
