package modelo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

public class Conexion {
       Connection con;
       public Connection getConnection() {
           String url = "jdbc:mysql://localhost:3306/db_ejemplo";
           String user = "root";
           String pass = "root";
           try {
               Class.forName("com.mysql.jdbc.Driver");
               con = (Connection) DriverManager.getConnection(url, user, pass);
           } catch (Exception e) {
           }
           return con;
       }
}
