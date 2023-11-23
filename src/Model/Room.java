package Model;

import Helper.DBConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.Helper;

public class Room {
    private int id;
    private String room_type;
    private int stock;
    private int season_id;
    private int adult_price;
    private int child_price;
    private int hotel_type_id;
    private int hotel_id;

    public Room() {

    }

    public static ArrayList<Room> getList() {
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM room";
        Room obj;
        try {
            Statement st = DBConnect.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Room();
                obj.setId(rs.getInt("id"));
                obj.setRoom_type(rs.getString("room_type"));
                obj.setStock(rs.getInt("stock"));
                obj.setSeason_id(rs.getInt("season_id"));
                obj.setAdult_price(rs.getInt("adult_price"));
                obj.setChild_price(rs.getInt("child_price"));
                obj.setHotel_type_id(rs.getInt("hotel_type_id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                roomList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public static ArrayList<Room> searchRoom(
            String hotelName
    ) {
        String query = "SELECT * FROM room WHERE hotel_id = ?";
        ArrayList<Room> roomList = new ArrayList<>();
        return roomList;
    }

    public static Room match (ResultSet rs) throws SQLException
    {
        Room obj = new Room ();

        obj.setId(rs.getInt("id"));
        obj.setRoom_type(rs.getString("room_type"));
        obj.setStock(rs.getInt("stock"));
        obj.setSeason_id(rs.getInt("season_id"));
        obj.setAdult_price(rs.getInt("adult_price"));
        obj.setChild_price(rs.getInt("child_price"));
        obj.setHotel_type_id(rs.getInt("hotel_type_id"));
        obj.setHotel_id(rs.getInt("hotel_id"));

        return obj;
    }


    public static ArrayList<Room> searchRoomList (String query)
    {
        ArrayList<Room> roomArrayList = new ArrayList<> ();

        try
        {
            Statement statement = DBConnect.getInstance ().createStatement ();
            ResultSet resultSet = statement.executeQuery (query);

            while (resultSet.next ())
            {
                roomArrayList.add (match (resultSet));
            }
        } catch (SQLException e)
        {
            e.printStackTrace ();

        }
        return roomArrayList;
    }

    public static String searchQuery (String name)
    {
        String query = "SELECT * FROM room INNER JOIN hotel ON room.hotel_id = hotel.id " +
                "WHERE name LIKE '%{{name}}%'";

        query = query.replace ("{{name}}" , name);
        return query;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public int getAdult_price() {
        return adult_price;
    }

    public void setAdult_price(int adult_price) {
        this.adult_price = adult_price;
    }

    public int getChild_price() {
        return child_price;
    }

    public void setChild_price(int child_price) {
        this.child_price = child_price;
    }

    public int getHotel_type_id() {
        return hotel_type_id;
    }

    public void setHotel_type_id(int hotel_type_id) {
        this.hotel_type_id = hotel_type_id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public Room(int id, String room_type, int stock, int season_id, int adult_price, int child_price, int hotel_type_id, int hotel_id) {
        this.id = id;
        this.room_type = room_type;
        this.stock = stock;
        this.season_id = season_id;
        this.adult_price = adult_price;
        this.child_price = child_price;
        this.hotel_type_id = hotel_type_id;
        this.hotel_id = hotel_id;
    }

    public static boolean add(String roomType, int stock, int seasonId, int adultPrice, int childPrice, int hotelTypeId, int hotelId) {
        String query = "INSERT INTO room (room_type, stock, season_id, adult_price, child_price, hotel_type_id, hotel_id) VALUES (?,?,?,?,?,?,?)";
        System.out.println(query);
        try {
            PreparedStatement pr = DBConnect.getInstance().prepareStatement(query);
            pr.setString(1, roomType);
            pr.setInt(2, stock);
            pr.setInt(3, seasonId);
            pr.setInt(4, adultPrice);
            pr.setInt(5, childPrice);
            pr.setInt(6, hotelTypeId);
            pr.setInt(7, hotelId);
            int response = pr.executeUpdate();
            if (response == -1) {
                Helper.showMsg("error");
            }
            return response != -1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

}
