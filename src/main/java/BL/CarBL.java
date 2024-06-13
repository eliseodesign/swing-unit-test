package BL;

import DAL.CarDAL;
import EN.Car;

import java.sql.SQLException;
import java.util.ArrayList;

public class CarBL {
    public int save(Car car) throws SQLException {
        return CarDAL.saveCar(car);
    }

    public int update(Car car) throws SQLException{
        return CarDAL.updateCar(car);
    }

    public int delete(Car car) throws SQLException{
        return CarDAL.deleteCar(car);
    }

    public ArrayList<Car> getAll() throws SQLException{
        return CarDAL.getAll();
    }

    public ArrayList<Car> getData(Car car) throws SQLException{
        return CarDAL.getData(car);
    }
}
