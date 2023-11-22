package View;

import Helper.Config;
import Helper.Helper;
import Model.Employee;
import Model.Hotel;
import Model.HotelSeason;
import Model.HotelType;

import javax.swing.*;

public class HotelAddGUI extends JFrame {
    private final Employee employee;

    private JPanel wrapper;
    private JTextField fld_hotel_name;
    private JComboBox cmb_hotel_star;
    private JTextPane textarea_hotel_property;
    private JTextPane textarea_hotel_address;
    private JTextField fld_hotel_phone;
    private JTextField fld_hotel_email;
    private JRadioButton radioButton7;
    private JRadioButton radioButton6;
    private JRadioButton radioButton1;
    private JRadioButton radioButton5;
    private JRadioButton radioButton4;
    private JRadioButton radioButton3;
    private JRadioButton radioButton2;
    private JTextField fld_hotel_season_end1;
    private JTextField fld_hotel_season_start1;
    private JTextField fld_hotel_season_end2;
    private JTextField fld_hotel_season_start2;
    private JButton btn_hotel_add;

    private String select_star;
    private int added_hotel_id;

    public HotelAddGUI(Employee employee) {
        this.employee = employee;
        add(wrapper);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        radioButton1.setText(Helper.hotelType("1"));
        radioButton2.setText(Helper.hotelType("2"));
        radioButton3.setText(Helper.hotelType("3"));
        radioButton4.setText(Helper.hotelType("4"));
        radioButton5.setText(Helper.hotelType("5"));
        radioButton6.setText(Helper.hotelType("6"));
        radioButton7.setText(Helper.hotelType("7"));

        select_star = cmb_hotel_star.getSelectedItem().toString();


        btn_hotel_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hotel_name) || Helper.isAreaEmpty(textarea_hotel_property) || Helper.isAreaEmpty(textarea_hotel_address) || Helper.isFieldEmpty(fld_hotel_phone) || Helper.isFieldEmpty(fld_hotel_email) || (!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected() && !radioButton4.isSelected() && !radioButton5.isSelected() && !radioButton6.isSelected() && !radioButton7.isSelected()) || Helper.isFieldEmpty(fld_hotel_season_start1) || Helper.isFieldEmpty(fld_hotel_season_end1)) {

                Helper.showMsg("fill");
            } else {
                String name = fld_hotel_name.getText();
                String star = (String) cmb_hotel_star.getSelectedItem();
                String property = textarea_hotel_property.getText();
                String address = textarea_hotel_address.getText();
                String phone = fld_hotel_phone.getText();
                String email = fld_hotel_email.getText();
                String season_start1 = fld_hotel_season_start1.getText();
                String season_end1 = fld_hotel_season_end1.getText();
                String season_start2 = fld_hotel_season_start2.getText();
                String season_end2 = fld_hotel_season_end2.getText();


                if (Hotel.add(name, star, property, address, phone, email)) {
                    Hotel addedHotel = Hotel.getFetch(email);
                    added_hotel_id = addedHotel.getId();

                    if (radioButton1.isSelected()) {
                        HotelType.add(radioButton1.getText(), added_hotel_id);
                    }
                    if (radioButton2.isSelected()) {
                        HotelType.add(radioButton2.getText(), added_hotel_id);
                    }
                    if (radioButton3.isSelected()) {
                        HotelType.add(radioButton3.getText(), added_hotel_id);
                    }
                    if (radioButton4.isSelected()) {
                        HotelType.add(radioButton4.getText(), added_hotel_id);
                    }
                    if (radioButton5.isSelected()) {
                        HotelType.add(radioButton5.getText(), added_hotel_id);
                    }
                    if (radioButton6.isSelected()) {
                        HotelType.add(radioButton6.getText(), added_hotel_id);
                    }
                    if (radioButton7.isSelected()) {
                        HotelType.add(radioButton7.getText(), added_hotel_id);
                    }
                    HotelSeason.add(added_hotel_id, season_start1, season_end1);

                    if (!Helper.isFieldEmpty(fld_hotel_season_start2) && !Helper.isFieldEmpty(fld_hotel_season_end2)) {
                        HotelSeason.add(added_hotel_id, season_start2, season_end2);
                    }
                    Helper.showMsg("done");
                    fld_hotel_name.setText(null);
                    cmb_hotel_star.setSelectedIndex(0);
                    textarea_hotel_property.setText(null);
                    textarea_hotel_address.setText(null);
                    fld_hotel_phone.setText(null);
                    fld_hotel_email.setText(null);
                    fld_hotel_season_start1.setText(null);
                    fld_hotel_season_end1.setText(null);
                    fld_hotel_season_start2.setText(null);
                    fld_hotel_season_end2.setText(null);
                    radioButton1.setSelected(false);
                    radioButton2.setSelected(false);
                    radioButton3.setSelected(false);
                    radioButton4.setSelected(false);
                    radioButton5.setSelected(false);
                    radioButton6.setSelected(false);
                }

            }


        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        new HotelAddGUI(new Employee());
    }
}
