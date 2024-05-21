package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static Connection con;
    private static final String puerto = "3306";
    private static final String nomservidor = "localhost";
    private static final String db = "labpets2";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String pass = "";
    private static final String url = "jdbc:mysql://" + nomservidor + ":" + puerto + "/" + db;

    public Connection conectar() {
        con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pass);
            if (con != null) {
                System.out.println("Conexion establecida..");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar " + e);
        }
        return con;
    }

    public void desconectar() {
        con = null;
        if (con == null) {
            System.out.println("Conexion terminada..");
        }
    }
}
