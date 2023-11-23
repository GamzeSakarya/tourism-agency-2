package View;

import Helper.Config;
import Helper.Helper;
import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    private JTextField fld_region_hotel_name;
    private JButton odaEkleButton;
    private JTable tbl_room_list;
    private JTable tbl_room_properties;
    private JTextField fld_room_id;
    private JButton rezervsayonYapButton;
    private JButton btn_room_sh;
    private JTextField fld_check_in;
    private JTextField fld_check_out;
    private JTable tbl_res_list;
    private JTextField fld_res_id;
    private JButton btn_res_del;

    DefaultTableModel mdl_hotel_list;
    private Object[] row_hotel_list;

    DefaultTableModel mdl_hotel_type;
    private Object[] row_hotel_type;
    private int select_hotel_id;
    private DefaultTableModel mdl_hotel_season;
    private Object[] row_hotel_season;
    private DefaultTableModel mdl_room_list;
    private DefaultTableModel mdl_res_list;
    private Object[] row_res_list;

    private Object[] row_room_list;
    private String check_in;
    private String check_out;
    private int select_room_id;

    private DefaultTableModel mdl_room_properties;
    private Object[] row_room_properties;
    private int id;


    public EmployeeGUI(Employee employee) {
        this.employee = employee;
        add(wrapper);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        mdl_hotel_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);

            }
        };
        Object[] col_hotel_list = {"id", "Hotel Name", "Star", "Properties", "Address", "Phone", "E-mail"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);
        row_hotel_list = new Object[col_hotel_list.length];
        loadHotelModel();
        tbl_hotel_list.setModel(mdl_hotel_list);
        tbl_hotel_list.getTableHeader().setReorderingAllowed(false);
        tbl_hotel_list.getColumnModel().getColumn(0).setMaxWidth(75);

        tbl_hotel_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                select_hotel_id = Integer.parseInt(tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 0).toString());
            } catch (Exception exception) {
            }
            loadHotelTypeModel(select_hotel_id);
            loadHotelSeasonModel(select_hotel_id);
        });

        mdl_hotel_type = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);

            }
        };
        Object[] col_hotel_type = {"Pansiyon Tipleri"};
        mdl_hotel_type.setColumnIdentifiers(col_hotel_type);
        row_hotel_type = new Object[col_hotel_type.length];
        tbl_hotel_type_list.setModel(mdl_hotel_type);
        tbl_hotel_type_list.getTableHeader().setReorderingAllowed(false);

        mdl_hotel_season = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);

            }
        };
        Object[] col_hotel_season = {"Dönem Başlangıcı", "Dönem Bitişi"};
        mdl_hotel_season.setColumnIdentifiers(col_hotel_season);
        row_hotel_season = new Object[col_hotel_season.length];
        tbl_hotel_season_list.setModel(mdl_hotel_season);

        mdl_room_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);

            }
        };
        Object[] col_room_list = {"id", "Otel Adı", "Oda Tipi", "Stok", "Sezon Tarihleri", "Yetişkin Fiyatı", "Çocuk Fiyatı", "Pansiyon Tipi"};
        mdl_room_list.setColumnIdentifiers(col_room_list);
        row_room_list = new Object[col_room_list.length];
        loadRoomListModel();
        tbl_room_list.setModel(mdl_room_list);
        tbl_room_list.getTableHeader().setReorderingAllowed(false);
        tbl_room_list.getColumnModel().getColumn(0).setMaxWidth(75);

        mdl_room_properties = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);

            }
        };

        Object[] col_room_properties = {"Oda Özellikleri", "Yatak Bilgisi", "Alan(m2)"};
        mdl_room_properties.setColumnIdentifiers(col_room_properties);
        row_room_properties = new Object[col_room_properties.length];
        tbl_room_properties.setModel(mdl_room_properties);
        tbl_room_properties.getTableHeader().setReorderingAllowed(false);

        tbl_room_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                select_room_id = Integer.parseInt(tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(), 0).toString());
            } catch (Exception exception) {
            }
            fld_room_id.setText(Integer.toString(select_room_id));
            loadRoomPropertiesModel(select_room_id);

        });



        mdl_res_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);

            }
        };

        Object[] col_res_list = {"id", "Müşteri Adı", "Müşteri Notu", "Müşteri Mail", "Oda No", "Giriş Tarihi", "Çıkış Tarihi", "Yetişkin Sayisi", "Çocuk Sayisi", "Toplam Fiyat"};
        mdl_res_list.setColumnIdentifiers(col_res_list);
        row_res_list = new Object[col_res_list.length];
        loadResListModel();
        tbl_res_list.setModel(mdl_res_list);
        tbl_res_list.getTableHeader().setReorderingAllowed(false);
        tbl_res_list.getColumnModel().getColumn(0).setMaxWidth(75);


        çıkışYapButton.addActionListener(e -> {
            dispose();
            new LoginGUI();
        });

        btn_hotel_add.addActionListener(e -> {
            HotelAddGUI hotelAdd = new HotelAddGUI(employee);
            hotelAdd.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelModel();
                    tbl_hotel_list.getSelectionModel().clearSelection();
                }
            });
        });

        odaEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomAddGUI roomAdd = new RoomAddGUI(employee);
                roomAdd.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadRoomListModel();
                        tbl_room_list.getSelectionModel().clearSelection();
                    }
                });
            }
        });

      btn_room_sh.addActionListener(e -> {
              String hotel_name = fld_region_hotel_name.getText ();
              String query = Room.searchQuery (hotel_name);
              ArrayList<Room> filterRooms = Room.searchRoomList (query);
              loadRoomListModel(filterRooms);
          });

        rezervsayonYapButton.addActionListener(e -> {
            try
            {
                ReservationAddGUI reservationAddGUI = new ReservationAddGUI ();

            } catch (Exception exception)
            {
                Helper.showMsg ("error");
            }


        });

        btn_res_del.addActionListener(e -> {
            int res_id = Integer.parseInt(fld_res_id.getText());
            if (Reservation.delete(res_id)) {
                Helper.showMsg("Rezervasyon silindi!");
                loadResListModel();
            } else {
                Helper.showMsg("Rezervasyon silinemedi!");
            }
        });
    }

    private void loadTypeModel (ArrayList<HotelType> list)
    {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel ();
        clearModel.setRowCount (0);

        for ( HotelType obj : list )
        {
            int i = 0;
            row_hotel_list[i++] = obj.getId ();
            row_hotel_list[i++] = obj.getType ();
            row_hotel_list[i++] = obj.getHotel_id ();
            mdl_hotel_list.addRow (row_hotel_list);

        }

    }

    private void loadHotelSeasonModel(int select_hotel_id) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_season_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (HotelSeason obj : HotelSeason.getListByHotelID(select_hotel_id)) {
            i = 0;
            row_hotel_season[i++] = obj.getSeason_start();
            row_hotel_season[i++] = obj.getSeason_end();
            mdl_hotel_season.addRow(row_hotel_season);
        }
    }

    private void loadRoomListModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Room obj : Room.getList()) {
            i = 0;
            row_room_list[i++] = obj.getId();
            row_room_list[i++] = Hotel.getFetch(obj.getHotel_id()).getName();
            row_room_list[i++] = obj.getRoom_type();
            row_room_list[i++] = obj.getStock();
            row_room_list[i++] = HotelSeason.getFetch(obj.getSeason_id()).getSeason_start() + " - " + HotelSeason.getFetch(obj.getSeason_id()).getSeason_end();
            row_room_list[i++] = obj.getAdult_price();
            row_room_list[i++] = obj.getChild_price();
            row_room_list[i++] = HotelType.getFetch(obj.getHotel_type_id()).getType();
            mdl_room_list.addRow(row_room_list);

        }

    }

    private void loadRoomListModel(ArrayList<Room> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Room obj : list) {
            i = 0;
            row_room_list[i++] = obj.getId();
            row_room_list[i++] = Hotel.getFetch(obj.getHotel_id()).getName();
            row_room_list[i++] = obj.getRoom_type();
            row_room_list[i++] = obj.getStock();
            row_room_list[i++] = HotelSeason.getFetch(obj.getSeason_id()).getSeason_start() + " - " + HotelSeason.getFetch(obj.getSeason_id()).getSeason_end();
            row_room_list[i++] = obj.getAdult_price();
            row_room_list[i++] = obj.getChild_price();
            row_room_list[i++] = HotelType.getFetch(obj.getHotel_type_id()).getType();
            mdl_room_list.addRow(row_room_list);

        }

    }

    private void loadResListModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_res_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Reservation obj : Reservation.getList()) {
            i = 0;
            row_res_list[i++] = obj.getId();
            row_res_list[i++] = obj.getClient_name();
            row_res_list[i++] = obj.getClient_note();
            row_res_list[i++] = obj.getClient_email();
            row_res_list[i++] = obj.getRoom_id();
            row_res_list[i++] = obj.getCheck_in();
            row_res_list[i++] = obj.getCheck_out();
            row_res_list[i++] = obj.getAdult_numb();
            row_res_list[i++] = obj.getChild_numb();
            row_res_list[i++] = obj.getTotal_price();
            mdl_res_list.addRow(row_res_list);
        }

    }

    private void loadRoomPropertiesModel(int id) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_properties.getModel();
        clearModel.setRowCount(0);
        int i;
        for (RoomProperties obj : RoomProperties.getListByRoomID(id)) {
            i = 0;
            row_room_properties[i++] = obj.getProperty();
            row_room_properties[i++] = obj.getBed();
            row_room_properties[i++] = obj.getArea();
            mdl_room_properties.addRow(row_room_properties);
        }
    }

    private void loadHotelTypeModel(int id) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_type_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (HotelType obj : HotelType.getListByHotelID(id)) {
            i = 0;
            row_hotel_type[i++] = obj.getType();
            mdl_hotel_type.addRow(row_hotel_type);
        }
    }

    private void loadHotelModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Hotel obj : Hotel.getList()) {
            i = 0;
            row_hotel_list[i++] = obj.getId();
            row_hotel_list[i++] = obj.getName();
            row_hotel_list[i++] = obj.getStar();
            row_hotel_list[i++] = obj.getProperty();
            row_hotel_list[i++] = obj.getAddress();
            row_hotel_list[i++] = obj.getPhone();
            row_hotel_list[i++] = obj.getEmail();
            mdl_hotel_list.addRow(row_hotel_list);

        }
    }

    public static void main(String[] args) {
        new EmployeeGUI(new Employee());
    }
}
