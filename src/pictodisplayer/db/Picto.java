package pictodisplayer.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pictodisplayer.*;

/**
 * Picto Images
 *
 * @author Pawel Kopec <paweelkopec@gmail.com>
 */
public class Picto {

    public Integer id;
    public String name;
    public String fileName;
    public Integer pageId;
    public Integer sort;
    public Integer stats;

    public static String db = "jdbc:derby:db_picto";

    /**
     * Create object Picto from id
     * @param id 
     */
    public Picto(Integer id) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            ResultSet rec = st.executeQuery("SELECT * FROM pictos WHERE id = " + id + "");
            while (rec.next()) {
                this.id = rec.getInt("id");
                this.name = rec.getString("name");
                this.fileName = rec.getString("file_name");
                this.sort = rec.getInt("sort");
                this.stats = rec.getInt("stats");
                break;
            }
            st.close();
        } catch (Exception e) {

            System.out.println("Error - " + e.toString());
        }
        System.out.println(this.id);
    }

    /**
     * Set Picto from ResulSet
     *
     * @param rec
     */
    public Picto(ResultSet rec) {
        try {
            this.id = rec.getInt("id");
            this.name = rec.getString("name");
            this.fileName = rec.getString("file_name");
            this.sort = rec.getInt("sort");
            this.stats = rec.getInt("stats");
        } catch (Exception e) {
            System.out.println("Error - " + e.toString());
        }
    }

    /**
     * Set object Picto from name
     *
     * @param pageName
     */
    public void loadFromName(String pageName) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            ResultSet rec = st.executeQuery("SELECT * FROM pictos WHERE name = '" + pageName + "'");
            while (rec.next()) {
                id = rec.getInt("id");
                name = rec.getString("name");
                sort = rec.getInt("sort");
                stats = rec.getInt("stats");
                break;
            }
            st.close();

        } catch (Exception e) {

            System.out.println("Error - " + e.toString());
        }
    }

    /**
     * Delete current Picto
     */
    public void delete() {
        try {
            File file = new File("C:\\PictoDisplayer\\" + this.fileName);
            file.delete();
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM pictos WHERE id =" + this.id);
            st.close();
        } catch (Exception e) {
            System.out.println("Error - " + e);
        }
    }

    /**
     * Add new Picto
     *
     * @param pid
     * @param name
     * @param fileName
     * @param sort
     * @param stats
     */
    public static void add(Integer pid, String name, String fileName, Integer sort, Integer stats) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO pictos "
                    + "(pid, name, file_name, sort, stats, date) VALUES(" + pid + ", '" + name + "', '" + fileName + "', " + sort + ", " + stats + ", CURRENT_DATE)");
            st.close();
        } catch (Exception e) {
            System.out.println("Error - " + e.toString());
        }
    }

    /**
     * Update current Picto
     */
    public void update() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            st.executeUpdate("UPDATE pictos  SET "
                    + "name='" + this.name + "', sort=" + this.sort + ", stats=" + this.stats + ", date=CURRENT_DATE WHERE id=" + this.id);
            st.close();
        } catch (Exception e) {
            System.out.println("Error - " + e.toString());
        }
    }

    /**
     * Increase Picto stats
     */
    public void increaseStats() {
        this.stats++;
        this.update();
    }
    /**
     * List pictos from page
     * @param pageID
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static Picto[] list(int pageID) throws ClassNotFoundException, SQLException{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            int rows = 0;
            ResultSet rec0 = st.executeQuery("SELECT COUNT(*) FROM pictos WHERE pid="+pageID);
            while (rec0.next()){
                rows = rec0.getInt(1);
            }
            ResultSet rec = st.executeQuery("SELECT * FROM pictos WHERE pid="+pageID);
            Picto pictos[] = new Picto[rows];
            int i = 0;
            while (rec.next()) {
                pictos[i] = new Picto(rec);
                i++;
            }
            return pictos;     
    }
    

}
