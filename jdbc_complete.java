
import java.sql.*;
import java.util.Scanner;

public class jdbc_complete {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Locked and Loaded");

            String url = "jdbc:mysql://localhost:3306/Student";
            String username = "root";
            String password = "root";
            Connection con = DriverManager.getConnection(url,username, password);

            Statement stmt = con.createStatement();
            stmt.executeUpdate("insert into students values('aayush',1,100))");
            stmt.close();

            PreparedStatement psmt = con.prepareStatement("insert into students values(?,?,?)");
            System.out.println("Enter your name");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your id");
            int id = sc.nextInt();
            String sname = sc.next();
            System.out.println("Enter your marks");
            int marks = sc.nextInt();
            sc.close();

            psmt.setString(1, sname);
            psmt.setInt(2, id);
            psmt.setInt(3, marks);
            int output = psmt.executeUpdate();
            System.out.println(output + "rows affected");
            psmt.close();

            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("select * from students");
            while (rs.next()) {
                String name1 = rs.getString("name");
                int id1 = rs.getInt("id");
                int marks1 = rs.getInt("marks");
                System.out.println("name:" + name1 + ", id:" + id1 + ", marks:" + marks1);
            }
            smt.close();
            con.close();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }
}
