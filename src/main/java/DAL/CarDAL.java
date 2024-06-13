package DAL;

import EN.Car;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarDAL {
    public static ArrayList<Car> getAll() throws SQLException {
        ArrayList<Car> lista = new ArrayList<>();
        Car Cars;
        try{
            String sql = "SELECT * FROM Car";
            Connection conexion = ComunDB.getConnection();
            PreparedStatement ps = ComunDB.createPreparedStatement(conexion, sql);
            ResultSet rs = ComunDB.getResultSet(ps);
            while (rs.next()){
                Cars = new Car(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                        );
                lista.add(Cars);
            }
            conexion.close();
            ps.close();
            rs.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return lista;
    }

    public static int saveCar(Car car) throws SQLException{
        int result = 0;
        try {
            String sql = "INSERT INTO Car(plate,brand,model,color,ownerName) VALUES(?,?,?,?,?)";
            Connection conexion = ComunDB.getConnection();
            PreparedStatement ps = ComunDB.createPreparedStatement(conexion, sql);
            ps.setString(1, car.getPlate());
            ps.setString(2, car.getBrand());
            ps.setString(3, car.getModel());
            ps.setString(4, car.getColor());
            ps.setString(5, car.getOwnerName());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public static int updateCar(Car car) throws SQLException {
        int result = 0;
        Connection conexion = null;
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE Car SET plate = ?, brand = ?, model = ?, color = ?, ownerName = ? WHERE Id = ?";
            conexion = ComunDB.getConnection();
            ps = ComunDB.createPreparedStatement(conexion, sql);
            ps.setString(1, car.getPlate());
            ps.setString(2, car.getBrand());
            ps.setString(3, car.getModel());
            ps.setString(4, car.getColor());
            ps.setString(5, car.getOwnerName());
            ps.setInt(6, car.getId());

            result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
        return result;
    }
    public static int deleteCar(Car car) throws SQLException{
        int result = 0;
        try {
            String sql = "DELETE FROM Car WHERE Id = ?";
            Connection conexion = ComunDB.getConnection();
            PreparedStatement ps = ComunDB.createPreparedStatement(conexion, sql);
            ps.setInt(1, car.getId());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    public static ArrayList<Car> getData(Car car) throws SQLException{
        ArrayList<Car> lista = new ArrayList<>();
        Car est;
        try{
            String sql = "SELECT * FROM Car WHERE id = ? or plate like ? or brand like ? or model like ? or color LIKE ?";
            Connection connection = ComunDB.getConnection();
            PreparedStatement ps = ComunDB.createPreparedStatement(connection, sql);
            ps.setInt(1, car.getId());
            ps.setString(2, "%" + car.getPlate() + "%");
            ps.setString(3, "%" + car.getBrand() + "%");
            ps.setString(4, "%" + car.getModel() + "%");
            ps.setString(5, "%" + car.getColor() + "%");
            ResultSet rs = ComunDB.getResultSet(ps);
            while (rs.next()){
                est = new Car(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                );
                lista.add(est);
            }
            connection.close();
            ps.close();
            rs.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }

}
