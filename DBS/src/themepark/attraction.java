package themepark;
import javax.print.DocFlavor;
import java.sql.*;
import java.util.Scanner;

public class attraction {
    public attraction() {
    }

    public static Connection getconnection() throws  SQLException {
        String username ="root";
        String password ="";
        String url = "jdbc:mysql://localhost:3306/themepark";
        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }


    public void print() throws SQLException {
        Connection con = attraction.getconnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from attraction");
        System.out.format("%-20s | %-20s | %-20s | %-20s | %-20s\n", "No", "Name", "Age", "Capacity", "Code");
        while (rs.next()) {
            System.out.format("%-20s | %-20s | %-20s | %-20s | %-20s\n",
                    rs.getInt("attract_no"),
                    rs.getString("attract_name"), rs.getInt("attract_age"),
                    rs.getInt("attract_capacity"), rs.getString("park_code"));

        }
        con.close();
    }
    public void insert() throws  SQLException{
        Connection con = attraction.getconnection();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter information\n No: ");
        int no = sc.nextInt();
        sc.nextLine();
        System.out.println("Name: ");
        String name = sc.nextLine();
        System.out.println("Age: ");
        int age = sc.nextInt();
        System.out.println("Capacity: ");
        int capacity = sc.nextInt();
        System.out.println("Code: ");
        String code = sc.nextLine();
        PreparedStatement ps = con.prepareStatement("Insert into attraction value(?,?,?,?,?)");
        ps.setInt(1,no);
        ps.setString(2,name);
        ps.setInt(3,age);
        ps.setInt(4,capacity);
        ps.setString(5,code);

        int no_of_row = ps.executeUpdate();
        if (no_of_row>0)
            System.out.println("Successfully");
        else
            System.out.println("Could not insert data");
    }



    public static void main(String[] args) {
        attraction at = new attraction();
        try {
            at.insert();
            at.print();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
