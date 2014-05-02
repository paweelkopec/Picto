
package pictodisplayer.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Create tables
 * @author Pawel Kopec paweelkopec@gmail.com
 */
public class Pictodb {
    protected static String name = "jdbc:derby:db_picto"; //default
    public static Connection conn;
    /**
     * Construct set database name
     * @param dbFileNamePrefix 
     */
    public Pictodb(String dbFileNamePrefix) {
        name = "jdbc:derby:"+dbFileNamePrefix;
    }
    /**
     * Create tables
     */        
    public static void createDatabase() {
        String data = name+";create=true";
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(data);
            Statement st = conn.createStatement();
            
            DatabaseMetaData dbmd = conn.getMetaData();
            // st.executeUpdate("TRUNCATE TABLE categories");
            ResultSet rs = dbmd.getTables(null, "APP", "CATEGORIES", null);
            if (!rs.next()) {
                st.executeUpdate("CREATE TABLE categories (" +
                                  "id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS identity (START WITH 1, INCREMENT BY 1)," +
                                  "name VARCHAR(240) ," +
                                  "description VARCHAR(240) default NULL ," +
                                  "file_name VARCHAR(240)," +
                                  "sort INTEGER default 0," +
                                  "stats INTEGER default 0," +
                                  "date date)");
                
            }
            rs = dbmd.getTables(null, "APP", "PAGES", null);
            if (!rs.next()) {
                st.executeUpdate("CREATE TABLE pages (" +
                                  "id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS identity (START WITH 1, INCREMENT BY 1)," +
                                  "cid INTEGER NOT NULL, "+
                                  "name VARCHAR(240)," +
                                  "sort INTEGER default 0," +
                                  "stats INTEGER default 0," +
                                  "date date)");
                
            }
            rs = dbmd.getTables(null, "APP", "PICTOS", null);
            if (!rs.next()) {
                st.executeUpdate("CREATE TABLE pictos (" +
                                  "id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS identity (START WITH 1, INCREMENT BY 1)," +
                                  "pid INTEGER NOT NULL, "+
                                  "name VARCHAR(240)," +
                                  "file_name VARCHAR(240)," +
                                  "sort INTEGER default 0," +
                                  "stats INTEGER default 0," +
                                  "date date)");
            }
            st.close();
        } catch (Exception e) {
             System.out.println(e.toString());
        }
    }
    
    public static  String getName(){
        return name;
    }
}
