package View;

import Helper.Config;
import Model.Employee;
import Model.Hotel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeGUI extends JFrame {
    private final Employee employee;

    private JPanel wrapper;
    private JTabbedPane tabbedPane1;
    private JButton çıkışYapButton;
    private JButton btn_hotel_add;
    private JTabbedPane tabbedPane2;
    private JTabbedPane tabbedPane3;

    private JTable tbl_hotel_type_list;
    private JTable tbl_hotel_season_list;
    private JTable tbl_hotel_list;

    DefaultTableModel mdl_hotel_list;
    private  Object[] row_hotel_list;

    DefaultTableModel mdl_hotel_type;
    private Object[] row_hotel_type;





    public EmployeeGUI (Employee employee)
        {
            this.employee=employee;
            add (wrapper);
            setSize (1200,800);
            setLocationRelativeTo (null);
            setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
            setTitle (Config.PROJECT_TITLE);
            setVisible (true);

            mdl_hotel_list = new DefaultTableModel (){
                @Override
                public boolean isCellEditable(int row,int column){
                    if (column==0)
                        return false;
                    return super.isCellEditable (row,column);

                }
            };
            Object[] col_hotel_list={"id","Hotel Name" ,"Star","Properties","Address","Phone","E-mail"};
            mdl_hotel_list.setColumnIdentifiers (col_hotel_list);
            row_hotel_list=new Object [col_hotel_list.length];
            loadHotelModel();
            tbl_hotel_list.setModel (mdl_hotel_list);
            tbl_hotel_list.getTableHeader ().setReorderingAllowed (false);
            tbl_hotel_list.getColumnModel ().getColumn (0).setMaxWidth (75);


            çıkışYapButton.addActionListener(e -> {
                dispose();
                new LoginGUI();
            });

            btn_hotel_add.addActionListener(e -> {
                HotelAddGUI hoteladd=new HotelAddGUI(employee);
            });

        }

    private void loadHotelModel ()
        {
            DefaultTableModel clearModel=(DefaultTableModel) tbl_hotel_list.getModel();
            clearModel.setRowCount (0);
            int i;
            for( Hotel obj : Hotel.getList()){
                i=0;
                row_hotel_list[i++]=obj.getId();
                row_hotel_list[i++]=obj.getName();
                row_hotel_list[i++]=obj.getStar();
                row_hotel_list[i++]=obj.getProperty();
                row_hotel_list[i++]=obj.getAddress();
                row_hotel_list[i++]=obj.getPhone();
                row_hotel_list[i++]=obj.getEmail();
                mdl_hotel_list.addRow (row_hotel_list);

            }
        }

    public static void main(String[] args) {
        new EmployeeGUI(new Employee());
    }
}
