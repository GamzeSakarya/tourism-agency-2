package Model;

import java.sql.PreparedStatement;
import Helper.DBConnect;

public class HotelSeason {
    private int id;
    private int hotel_id;
    private String season_start;
    private String season_end;

    public HotelSeason(int id, int hotel_id, String season_start, String season_end) {
        this.id = id;
        this.hotel_id = hotel_id;
        this.season_start = season_start;
        this.season_end = season_end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getSeason_start() {
        return season_start;
    }

    public void setSeason_start(String season_start) {
        this.season_start = season_start;
    }

    public String getSeason_end() {
        return season_end;
    }

    public void setSeason_end(String season_end) {
        this.season_end = season_end;
    }

    public static boolean add(int hotel_id, String season_start, String season_end) {
        String sql = "INSERT INTO hotel_season (hotel_id, season_start, season_end) VALUES (?,?,?)";
        try {
            PreparedStatement pr = DBConnect.getInstance().prepareStatement(sql);
            pr.setInt(1, hotel_id);
            pr.setString(2, season_start);
            pr.setString(3, season_end);
            return pr.executeUpdate() != -1;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }
}
