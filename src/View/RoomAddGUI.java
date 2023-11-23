package View;

import Helper.Helper;
import Model.Employee;
import Helper.Config;
import Helper.Item;
import Model.Hotel;
import Model.HotelSeason;
import Model.HotelType;
import Model.Room;

import javax.swing.*;
import java.util.ArrayList;
import Model.RoomProperties;

public class RoomAddGUI extends JFrame {
    private JComboBox cmb_room_hotelname;
    private JComboBox cmb_room_hoteltype;
    private JTextField fld_room_stock;
    private JComboBox cmb_room_type;
    private JComboBox cmb_season;
    private JTextField fld_adult_price;
    private JTextField fld_child_price;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JTextField fld_room_area;
    private JButton odaEkleButton;
    private JPanel wrapper;
    private JTextField fld_room_bed;
    private final Employee employee;

    private int addedRoom_id;

    public RoomAddGUI(Employee employee) {
        this.employee = employee;
        add(wrapper);
        setSize(800, 400);
        setLocation(Helper.screenCenter("x", getSize()), Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        radioButton1.setText(Helper.roomProperty("1"));
        radioButton2.setText(Helper.roomProperty("2"));
        radioButton3.setText(Helper.roomProperty("3"));
        radioButton4.setText(Helper.roomProperty("4"));
        radioButton5.setText(Helper.roomProperty("5"));

        loadHotelNameCombo();
        loadHotelTypeCombo();
        loadSeasonCombo();

        cmb_room_hotelname.addActionListener(e -> {
            loadHotelTypeCombo();
            loadSeasonCombo();
            cmb_room_type.setSelectedIndex(0);
            cmb_season.setSelectedIndex(0);
        });

        odaEkleButton.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_room_stock) || Helper.isFieldEmpty(fld_adult_price) || Helper.isFieldEmpty(fld_child_price) || Helper.isFieldEmpty(fld_room_bed) || Helper.isFieldEmpty(fld_room_area) ||
                    cmb_room_type.getSelectedItem().toString().equals("") || cmb_room_hoteltype.getSelectedItem() == null ||
                    cmb_season.getSelectedItem() == null || cmb_room_hotelname.getSelectedItem() == null ||
                    (!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected() && !radioButton4.isSelected() && !radioButton5.isSelected())) {
                Helper.showMsg("fill");
            } else {
                String room_type = cmb_room_type.getSelectedItem().toString();
                int stock = Integer.parseInt(fld_room_stock.getText());
                int season_id = 0;
                int adult_price = Integer.parseInt(fld_adult_price.getText().toString());
                int child_price = Integer.parseInt(fld_child_price.getText().toString());
                Item hotelTypeItem = (Item) cmb_room_hoteltype.getSelectedItem();
                int hotel_type_id = hotelTypeItem.getKey();
                Item hotelItem = (Item) cmb_room_hotelname.getSelectedItem();
                int hotel_id = hotelItem.getKey();
                for (HotelSeason obj : HotelSeason.getListByHotelID(hotel_id)) {
                    String season = (obj.getSeason_start().toString() + " - " + obj.getSeason_end().toString());
                    if (season.equals(cmb_season.getSelectedItem().toString())) {
                        season_id = obj.getId();
                        break;
                    }
                }
                if (Room.add(room_type, stock, season_id, adult_price, child_price, hotel_type_id, hotel_id)) {
                    ArrayList<Room> roomList = Room.getList();
                    Room addedRoom = roomList.get(Room.getList().size() - 1);
                    addedRoom_id = addedRoom.getId();
                    String room_properties = "";
                    if (radioButton1.isSelected()) {
                        room_properties += radioButton1.getText() + ",";
                    }
                    if (radioButton2.isSelected()) {
                        room_properties += radioButton2.getText() + ",";
                    }
                    if (radioButton3.isSelected()) {
                        room_properties += radioButton3.getText() + ",";
                    }
                    if (radioButton4.isSelected()) {
                        room_properties += radioButton4.getText() + ",";
                    }
                    if (radioButton5.isSelected()) {
                        room_properties += radioButton5.getText() + ",";
                    }
                    RoomProperties.add(room_properties, addedRoom_id, fld_room_bed.getText(), Integer.parseInt(fld_room_area.getText().toString()));
                    Helper.showMsg("done");
                    cmb_room_hotelname.setSelectedIndex(0);
                    cmb_room_type.setSelectedIndex(0);
                    fld_room_stock.setText(null);
                    cmb_room_hoteltype.setSelectedIndex(0);
                    cmb_season.setSelectedIndex(0);
                    fld_adult_price.setText(null);
                    fld_child_price.setText(null);
                    fld_room_bed.setText(null);
                    fld_room_area.setText(null);
                    radioButton1.setSelected(false);
                    radioButton2.setSelected(false);
                    radioButton3.setSelected(false);
                    radioButton4.setSelected(false);
                    radioButton5.setSelected(false);
                }
            }
        });
    }


        private void loadSeasonCombo () {
            Item hotelItem = (Item) cmb_room_hotelname.getSelectedItem();
            cmb_season.removeAllItems();
            cmb_season.addItem(new Item(0, null));
            for (HotelSeason obj : HotelSeason.getListByHotelID(hotelItem.getKey())) {
                cmb_season.addItem(new Item(obj.getId(), (obj.getSeason_start() + " - " + obj.getSeason_end())));
            }
        }

        private void loadHotelTypeCombo () {
            Item hotelItem = (Item) cmb_room_hotelname.getSelectedItem();

            cmb_room_hoteltype.removeAllItems();
            cmb_room_hoteltype.addItem(new Item(0, null));
            for (HotelType obj : HotelType.getListByHotelID(hotelItem.getKey())) {
                cmb_room_hoteltype.addItem(new Item(obj.getId(), obj.getType()));
            }
        }


        private void loadHotelNameCombo() {
            cmb_room_hotelname.removeAllItems();
            cmb_room_hotelname.addItem(new Item(0, null));
            for (Hotel obj : Hotel.getList()) {
                cmb_room_hotelname.addItem(new Item(obj.getId(), obj.getName()));
            }
        }
}
