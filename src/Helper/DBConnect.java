package Helper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnect {
    private Connection connect=null;

    public Connection connectDB ()
    {
        try
        {
            this.connect= DriverManager.getConnection (Config.DB_URL,Config.DB_NAME ,Config.DB_PASSWORD);
        } catch (SQLException e)
        {
            System.out.println (e.getMessage ());
        }
        return connect;
    }

    public static Connection getInstance(){
        DBConnect db = new DBConnect ();
        return db.connectDB ();
    }
}

