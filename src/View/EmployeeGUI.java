package View;

import Helper.Config;
import Model.Employee;
import Helper.Helper;
import Model.Hotel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EmployeeGUI extends JFrame {
    private JPanel wrapper;
    private JTable tbl_hotel_list;
    private final Employee employee;

    private DefaultTableModel mdl_hotel_list;
    private Object[] row_list_hotel;
    private Object[] col_list_hotel;

    public EmployeeGUI(Employee employee) {
        this.employee = employee;
        add(wrapper);
        setSize(1000, 500);
        int x = Helper.screenCenter("x", getSize());
        int y = Helper.screenCenter("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        mdl_hotel_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_hotel_list = {"ID", "Otel Adı", "Yıldız", "Tesis Özellikleri", "Adres", "Telefon", "Email"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);
        row_list_hotel = new Object[col_hotel_list.length];
        loadHotelModel();
        tbl_hotel_list.setModel(mdl_hotel_list);
        tbl_hotel_list.getTableHeader().setReorderingAllowed(false);
        tbl_hotel_list.getColumnModel().getColumn(0).setMaxWidth(75);
    }

    private void loadHotelModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Hotel obj : Hotel.getList()) {
            i = 0;
            row_list_hotel[i++] = obj.getId();
            row_list_hotel[i++] = obj.getName();
            row_list_hotel[i++]=obj.getStar();
            row_list_hotel[i++]=obj.getProperty();
            row_list_hotel[i++]=obj.getAddress();
            row_list_hotel[i++]=obj.getPhone();
            row_list_hotel[i++]=obj.getEmail();
            mdl_hotel_list.addRow (row_list_hotel);

        }
    }

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Employee");
        employee.setUname("employee");
        employee.setPass("employee");
        employee.setType("employee");
        new EmployeeGUI(employee);
    }
}


