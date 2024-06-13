package EN;

public class Car {
    private int id;
    private String plate;
    private String brand;
    private String model;
    private String color;
    private String ownerName;

    public Car() {
    }

    public Car(int id, String plate, String brand, String model, String color,
               String ownerName) {
        this.id = id;
        this.plate = plate;
        this.brand = brand;
        this.color = color;
        this.model = model;
        this.ownerName = ownerName;
    }

    // Setters y Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }


}

