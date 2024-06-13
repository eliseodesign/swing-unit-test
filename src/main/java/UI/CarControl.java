package UI;

import BL.CarBL;
import EN.Car;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarControl extends JFrame {

    ArrayList<Car> carList;
    Car car;
    CarBL carBL = new CarBL();
    private JPanel PrincipalPanel;
    private JTextField txtId;
    private JButton btnNew;
    private JButton btnSave;
    private JButton btnModify;
    private JButton btnDelete;
    private JButton btnCancel;
    private JComboBox cbCriterio;
    private JTextField txtPlate;
    private JTextField txtBrand;
    private JTextField txtModel;
    private JTextField txtColor;
    private JTextField txtSearch;
    private JButton btnExit;
    private JButton btnSearch;
    private JTable CarsTable;
    private JTextField txtOwner;

    public static void main(String[] args) throws SQLException {
        new CarControl();
    }
    public CarControl() throws SQLException {
        initClass();
        updateForm();

        btnNew.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                txtPlate.setEnabled(true);
                txtBrand.setEnabled(true);
                txtModel.setEnabled(true);
                txtColor.setEnabled(true);
                txtOwner.setEnabled(true);
                txtPlate.grabFocus();

                btnSave.setEnabled(true);
                btnNew.setEnabled(false);
                btnCancel.setEnabled(true);
            }
        });

        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                car = new Car();
                car.setPlate(txtPlate.getText());
                car.setBrand(txtBrand.getText());
                car.setModel(txtModel.getText());
                car.setOwnerName(txtOwner.getText());
                car.setColor(txtColor.getText());
                try{
                    carBL.save(car);
                    JOptionPane.showMessageDialog(null, "Se guardó correctamente");
                    updateForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });

        btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    updateForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        CarsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int fila = CarsTable.getSelectedRow();
                txtId.setText(CarsTable.getValueAt(fila, 0).toString());
                txtPlate.setText(CarsTable.getValueAt(fila, 1).toString());
                txtBrand.setText(CarsTable.getValueAt(fila, 2).toString());
                txtModel.setText(CarsTable.getValueAt(fila, 3).toString());
                txtColor.setText(CarsTable.getValueAt(fila, 4).toString());
                txtOwner.setText(CarsTable.getValueAt(fila, 5).toString());

                txtPlate.setEnabled(true);
                txtBrand.setEnabled(true);
                txtModel.setEnabled(true);
                txtColor.setEnabled(true);
                txtOwner.setEnabled(true);
                txtPlate.grabFocus();

                btnNew.setEnabled(false);
                btnModify.setEnabled(true);
                btnDelete.setEnabled(true);
                btnCancel.setEnabled(true);
            }
        });

        btnModify.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                car = new Car();
                car.setId(Integer.parseInt(txtId.getText()));
                car.setPlate(txtPlate.getText());
                car.setBrand(txtBrand.getText());
                car.setModel(txtModel.getText());
                car.setColor(txtColor.getText());
                car.setOwnerName(txtOwner.getText());
                try {
                    carBL.update(car);
                    JOptionPane.showMessageDialog(null, "Se modificó con éxito");
                    updateForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                car = new Car();
                car.setId(Integer.parseInt(txtId.getText()));
                try{
                    carBL.delete(car);
                    JOptionPane.showMessageDialog(null, "Se eliminó correctamente");
                    updateForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(txtSearch.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null,
                            "El campo de busqueda no debe quedar vacio!");
                    return;
                }

                car = new Car();
                int idx = cbCriterio.getSelectedIndex();

                if (idx == 0)
                {
                    car.setId(Integer.parseInt(txtSearch.getText()));
                    try{
                        fillTable(carBL.getData(car));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (idx == 1)
                {
                    car.setPlate(txtSearch.getText());
                    try{
                        fillTable(carBL.getData(car));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (idx == 2)
                {
                    car.setBrand(txtSearch.getText());
                    try{
                        fillTable(carBL.getData(car));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (idx == 3)
                {
                    car.setModel(txtSearch.getText());
                    try{
                        fillTable(carBL.getData(car));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (idx == 4)
                {
                    car.setColor(txtSearch.getText());
                    try{
                        fillTable(carBL.getData(car));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (idx == 5)
                {
                    car.setOwnerName(txtSearch.getText());
                    try{
                        fillTable(carBL.getData(car));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }


            }
        });


    }

    void initClass(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 700);
        setTitle("Control de Autos");
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        setContentPane(PrincipalPanel);
        setVisible(true);
    }
    void updateForm() throws SQLException {
        txtId.setText("");
        txtPlate.setText("");
        txtBrand.setText("");
        txtModel.setText("");
        txtColor.setText("");
        txtOwner.setText("");

        txtId.setEnabled(false);
        txtPlate.setEnabled(false);
        txtBrand.setEnabled(false);
        txtModel.setEnabled(false);
        txtColor.setEnabled(false);
        txtOwner.setEnabled(false);

        btnNew.setEnabled(true);
        btnSave.setEnabled(false);
        btnModify.setEnabled(false);
        btnDelete.setEnabled(false);

        txtSearch.setText("");

        fillTable(carBL.getAll());
    }

    void fillTable(ArrayList<Car> cars){
        Object[] obj = new Object[6];
        carList = new ArrayList<>();
        String[] encabezado = {"ID", "PLACA", "MARCA", "MODELO", "COLOR", "DUEÑO"};
        DefaultTableModel tm = new DefaultTableModel(null, encabezado);
        carList.addAll(cars);
        for(Car item : carList){
            obj[0] = item.getId();
            obj[1] = item.getPlate();
            obj[2] = item.getBrand();
            obj[3] = item.getModel();
            obj[4] = item.getColor();
            obj[5] = item.getOwnerName();

            tm.addRow(obj);
        }
        CarsTable.setModel(tm);
    }
}
