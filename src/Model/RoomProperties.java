package Model;

import Helper.DBConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomProperties {
    private int id;
    private String property;
    private int room_id;
    private String bed;
    private int area;

    public RoomProperties(int id, String property, int room_id, String bed, int area) {
        this.id = id;
        this.property = property;
        this.room_id = room_id;
        this.bed = bed;
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public RoomProperties() {
    }
    public static boolean add(String property, int room_id , String bed, int area){
        String query = "INSERT INTO `room_property` (`property`, `room_id`, `bed`, `area`) VALUES (?,?,?,?)";
        System.out.println(query);
        try{
            PreparedStatement pr = DBConnect.getInstance().prepareStatement(query);
            pr.setString(1,property);
            pr.setInt(2,room_id);
            pr.setString(3,bed);
            pr.setInt(4,area);
            return pr.executeUpdate() != -1;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }


    public static ArrayList<RoomProperties> getListByRoomID(int id){
        ArrayList<RoomProperties> roomPropertiesList = new ArrayList<>();
        RoomProperties obj;
        String query = "SELECT * FROM room_property WHERE room_id = ?";
        try{
            PreparedStatement pr = DBConnect.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new RoomProperties();
                obj.setProperty(rs.getString("property"));
                obj.setBed(rs.getString("bed"));
                obj.setArea(rs.getInt("area"));
                roomPropertiesList.add(obj);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return roomPropertiesList;
    }
}
