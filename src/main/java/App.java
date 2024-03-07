import PostgreSQL.DBFunctions;

import java.sql.Connection;
import java.util.List;

public class App {

    private static String base_name = "phone_book";
    private static String  table_name = "customers";
    private static String user = "postgres";
    private static String pass = "123";
    private static String path = "src/main/java/PostgreSQL/users.csv";

    static void runApp(boolean use_db) {

        if (use_db) {
            connect_db();
        } else {

            greeting();

            Student student = new Student(new Name("Jane", "Blue"));
            student.setAge(18);
            student.setGrade(new Grade(7));
            student.addPerson(student);

            student = new Student(new Name("Mart", "Ben"), new Grade(8, "A"));
            student.setAge(19);
            student.addPerson(student);

            Teacher teacher = new Teacher(new Name("Rick", "Selen")); //, new Course("0001", "Math"));
            teacher.addPerson(teacher);

            teacher = new Teacher(new Name("Sena", "Reagan"), new Course("0001", "Math"));
            teacher.addPerson(teacher);

            teacher = new Teacher(new Name("Teresa", "Crick"), new Course("0002", "History"));
            teacher.addCourse(teacher, new Course("0006", "English"));
            teacher.addPerson(teacher);

            System.out.println(student.getPeople());
        }
    }

    private static void connect_db() {
        DBFunctions db = new DBFunctions();
        Connection conn =  db.get_connection_db(base_name, user, pass);

        db.create_table(conn, table_name);

        List<String[]> customers = DBFunctions.prepare_data(path, ";");
        db.insert_rows(conn, table_name, customers);
        db.insert_row(conn, table_name, "Ken", "Denver", "332");

        db.update_name(conn, table_name, "Rimma", "Katerina");

        db.search_by_name(conn, table_name, "Katerina");
        db.search_by_id(conn, table_name, 5);

        db.delete_row_by_name(conn, table_name, "Ann");
        db.delete_row_by_id(conn, table_name, 2);

        db.read_data(conn, table_name);
        db.delete_table(conn, table_name);

    }

    private static void greeting() {
        System.out.println("Welcome to our school");
        System.out.println();
    }
}
