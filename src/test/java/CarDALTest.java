import DAL.CarDAL;
import EN.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class CarDALTest {
    @Test
    public void saveTest() throws SQLException {
        Car car = new Car();
        car.setPlate("010201");
        car.setBrand("Mitsubishi");
        car.setModel("Eclipse");
        car.setColor("Verde");
        car.setOwnerName("Javier");

        int esperado = 1;
        int actual = CarDAL.saveCar(car);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void getAllTests() throws SQLException {
        int esperado = 2;
        int actual = CarDAL.getAll().size();
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void modify() throws SQLException{
        Car car = new Car();
        car.setPlate("010102");
        car.setBrand("Toyota");
        car.setModel("Supa");
        car.setColor("Gris");
        car.setOwnerName("Danilo");

        int esperado = 1;
        int actual = CarDAL.updateCar(car);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void delete() throws SQLException{
        Car car = new Car();
        car.setId(1);

        int esperado = 1;
        int actual = CarDAL.deleteCar(car);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void getFilterData() throws SQLException{
        Car car = new Car();
        car.setPlate("222");
        car.setId(0);
        car.setBrand("");

        int actual = CarDAL.getData(car).size();
        Assertions.assertNotEquals(0, actual);
    }
}
