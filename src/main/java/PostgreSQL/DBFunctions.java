package PostgreSQL;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBFunctions {
    public Connection get_connection_db(String dbname, String user, String pass) {

        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
            if (conn != null) {
                System.out.println("Connection Established");
            } else {
                System.out.println("connection failed");
            }

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
        return conn;
    }

    public void create_table(Connection conn, String table_name) {
        Statement statement;
        try {
            String query = "create table if NOT exists " + table_name +
                    "(" +
                    "id SERIAL," +
                    " name varchar(200)," +
                    " address varchar(200)," +
                    " phone varchar(200)," +
                    " primary key(id)" +
                    ");";

            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void delete_table(Connection conn, String table_name) {

        Statement statement;
        try {
            String query = String.format("drop table %s", table_name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Deleted.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insert_row(Connection conn, String table_name, String name, String address, String phone) {
        Statement statement;
        try {
            String query = String.format("insert into %s(name, address, phone) values('%s', '%s', '%s');", table_name, name, address, phone);

            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Inserted.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insert_rows(Connection conn, String table_name, List<String[]> lines) {
        Statement statement;
        try {

            for (String[] line : lines) {
                String query = String.format("insert into %s(name, address, phone) values('%s', '%s', '%s');", table_name, line[0], line[1], line[2]);
                statement = conn.createStatement();
                statement.executeUpdate(query);
            }
            System.out.println("Rows Inserted.");

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
    }

    public static ArrayList<String[]> prepare_data(String path, String delimiter) {
        System.out.println("Preparing data ....");

        ArrayList<String[]> db_lines = new ArrayList<String[]>();

        try {
            FileReader fileReader = new FileReader(path);
            CSVReader csvReader = new CSVReader(fileReader);
            String[] line;
            csvReader.readNext();

            while ((line = csvReader.readNext()) != null) {
                for (String data : line) {
                    String[] db_line = data.split(delimiter);
                    db_lines.add(db_line);
                }
            }
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }

        return db_lines;
    }

    public void read_data(Connection conn, String table_name) {

        System.out.println("\n\nReading data ...................");
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("select * from %s", table_name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.print(rs.getString("id") + " ");
                System.out.print(rs.getString("name") + " ");
                System.out.print(rs.getString("address"));
                System.out.println(rs.getString("phone"));
            }

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
    }

    public void update_name(Connection conn, String table_name, String old_name, String new_name) {

        System.out.println("Updating data ...................");
        Statement statement;

        try {
            String query = String.format("update %s set name = '%s' where name = '%s'", table_name, new_name, old_name );
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Updated.");

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
    }

    public void search_by_name(Connection conn, String table_name, String name) {

        System.out.printf("Searching name: %s\n", name);
        Statement statement;
        ResultSet rs;

        try {
            String query = String.format("select * from %s where name = '%s'", table_name, name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.print(rs.getString("id") + " ");
                System.out.print(rs.getString("name") + " ");
                System.out.print(rs.getString("address"));
                System.out.println(rs.getString("phone"));
            }

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
    }

    public void search_by_id(Connection conn, String table_name, int id) {

        System.out.println("\nSearching data id: " + id);
        Statement statement;
        ResultSet rs;

        try {
            String query = String.format("select * from %s where id = %s", table_name, id );
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.print(rs.getString("id") + " ");
                System.out.print(rs.getString("name") + " ");
                System.out.print(rs.getString("address") + " ");
                System.out.println(rs.getString("phone"));
            }

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
    }

    public void delete_row_by_name(Connection conn, String table_name, String name) {

        Statement statement;

        try {
            String query = String.format("delete from %s where name = '%s'", table_name, name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.printf("\nName: '%s' Deleted%n ", name);
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
    }

    public void delete_row_by_id(Connection conn, String table_name, int id) {

        Statement statement;

        try {
            String query = String.format("delete from %s where id = %s", table_name, id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.printf("\nId: %s Deleted. ", id);
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
    }

}
