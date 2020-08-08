import java.sql.SQLException;
import java.util.Scanner;

public class Schedule {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException{
        System.out.println("Hallo!");
        while (true) {
            System.out.println("What do you want to do(add, delete, modify, reveal)?");
            String choose = scanner.nextLine();
            if(choose.equals("exit"))
                break;

            switch (choose) {
                case "add":
                    Teacher.addTeacher();
                    Subject.addSubject();
                    Group.addGroup();
                    Auditorium.addAuditorium();
                    Calender.addDate();
                    System.out.println("All rows was added");
                    break;
                case "delete":
                    Delete.deleteRow();
                    System.out.println(" The rows was deleted.");
                    break;
                case "modify":
                    System.out.println("What do you want to modify(calender, teacher, subject, group, auditorium)?");
                    String modifyResult = scanner.nextLine();
                    switch (modifyResult) {
                        case "calender":
                            Calender.editDate();
                            break;
                        case "teacher":
                            Teacher.editTeacher();
                            break;
                        case "subject":
                            Subject.editSubject();
                            break;
                        case "group":
                            Group.editGroup();
                            break;
                        case "auditorium":
                            Auditorium.editAuditorium();
                            break;
                    }
                    break;
                case "reveal":
                    View.view();
                    break;
            }
        }
    }
}


