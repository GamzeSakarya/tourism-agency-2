package Model;

import java.sql.PreparedStatement;

import Helper.DBConnect;

public class HotelType {
    private int id;
    private String type;
    private int hotel_id;

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
        String sql = "INSERT INTO hotel_type (type, hotel_id) VALUES (?,?)";
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


}
