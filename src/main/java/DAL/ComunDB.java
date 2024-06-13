package DAL;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class ComunDB {
    private static String connectionString = "jdbc:mysql://localhost:3306/AutosDb?user=root&password=root_password";

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        Connection conexion = DriverManager.getConnection(connectionString);
        return conexion;
    }

    public static PreparedStatement createPreparedStatement(Connection conexion, String sql) throws SQLException{
        PreparedStatement ps = conexion.prepareStatement(sql);
        return ps;
    }

    public static ResultSet getResultSet(PreparedStatement ps) throws SQLException{
        ResultSet resultSet = ps.executeQuery();
        return resultSet;
    }
}
