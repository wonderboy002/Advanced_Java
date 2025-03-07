package JDBC;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        String url="jdbc:mysql://localhost:3306/college";
        String user="root";
        String password="nipun@2002";
        String query="select * from student";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,user,password);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        while (rs.next()){
            System.out.println(rs.getInt(1)+" : "+rs.getString(2));
        }

//        String insertQuery="insert into student (name) values ('Mohini')";
//        int count=st.executeUpdate(insertQuery);
//        System.out.println(count+"rows Affected!!!");

        //taking user input to insert names

//        String insertQuery="insert into student (name) values ('" + name + "')";
//
//        int cnt=st.executeUpdate(insertQuery);
//        System.out.println(cnt+"rows affected...");

        //prepared statements easy...
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter name to insert into the db...");
        String name=sc.next();
        String insertQuery="insert into student (name) values (?)";
        PreparedStatement pst=con.prepareStatement(insertQuery);
        pst.setString(1,name);
        int cnt=pst.executeUpdate();
        System.out.println(cnt+" rows affected...");
        st.close();
        con.close();
    }
}