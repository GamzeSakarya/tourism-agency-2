package Model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;


import Helper.DBConnect;

public class HotelType {
    private int id;
    private String type;
    private int hotel_id;


    public HotelType() {

    }


    public HotelType(int id, String type, int hotel_id) {
        this.id = id;
        this.type = type;
        this.hotel_id = hotel_id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public static boolean add(String type, int hotel_id) {
        String sql = "INSERT INTO type_hotel (type, hotel_id) VALUES (?,?)";
        try {
            PreparedStatement pr = DBConnect.getInstance().prepareStatement(sql);
            pr.setString(1, type);
            pr.setInt(2, hotel_id);
            return pr.executeUpdate() != -1;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    public static ArrayList<HotelType> getListByHotelID(int id) {
        ArrayList<HotelType> hotelTypeList = new ArrayList<>();
        HotelType obj;
        String query = "SELECT * FROM type_hotel WHERE hotel_id = ?";
        try {
            PreparedStatement pr = DBConnect.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                obj = new HotelType(rs.getInt("id"), rs.getString("type"), rs.getInt("hotel_id"));
                hotelTypeList.add(obj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelTypeList;
    }

    public static HotelType getFetch(int id) {
        HotelType obj = null;
        String query = "SELECT * FROM type_hotel WHERE id = ?";
        try {
            PreparedStatement pr = DBConnect.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new HotelType(rs.getInt("id"), rs.getString("type"), rs.getInt("hotel_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
