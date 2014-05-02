package pictodisplayer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import pictodisplayer.*;

/**
 * Database Page
 *
 * @author Pawel Kopec <paweelkopec@gmail.com>
 */
public class Page {

    public Integer id;
    public String name;
    public Integer categoryId;
    public Integer sort;
    public Integer stats;
    public static String db = "jdbc:derby:db_picto";

    /**
     * Load form database
     *
     * @param pageName
     */
    public Page(String pageName) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            ResultSet rec = st.executeQuery("SELECT * FROM pages WHERE name = '" + pageName + "'");
            while (rec.next()) {
                id = rec.getInt("id");
                name = rec.getString("name");
                categoryId = rec.getInt("cid");
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
     * Load form database
     *
     * @param id
     */
    public Page(int id) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            ResultSet rec = st.executeQuery("SELECT * FROM pages WHERE id = " + id);
            while (rec.next()) {
                this.id = rec.getInt("id");
                name = rec.getString("name");
                categoryId = rec.getInt("cid");
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
     * Load form database
     *
     * @param rec
     */
    public Page(ResultSet rec) throws SQLException {
        this.id = rec.getInt("id");
        name = rec.getString("name");
        categoryId = rec.getInt("cid");
        sort = rec.getInt("sort");
        stats = rec.getInt("stats");
    }
     /**
     * Add new page
     * @param cid
     * @param name
     * @param sort 
     * @param stats
     */
    public static void add(Integer cid, String name, Integer sort, Integer stats ){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO pages "
                           + "(cid, name, sort, stats, date) VALUES("+cid+", '"+name+"', "+sort+", "+stats+", CURRENT_DATE)");
            st.close();
        } catch (Exception e) {
            
            System.out.println("Error - " + e.toString());
        }
    }
    /**
     * Delete current Page
     */
    public void delete() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM pages WHERE id =" + this.id);
            Picto pictos[] = Picto.list(this.id);
            for(int i = 0; i < pictos.length; i++) { 
                pictos[i].delete();
            }
            st.close();
        } catch (Exception e) {
            System.out.println("Error - " + e);
        }
    }

    /**
     * Update current Page
     */
    public void update() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            st.executeUpdate("UPDATE pages  SET "
                    + "name='" + this.name + "', sort=" + this.sort + ", stats=" + this.stats + ", date=CURRENT_DATE WHERE id=" + this.id);
            st.close();
        } catch (Exception e) {
            System.out.println("Error - " + e.toString());
        }
    }
    /**
     * Load pages from category id
     * @param categoryID
     * @return 
     */
    public static Page[] list(int categoryID) throws ClassNotFoundException, SQLException{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            int rows = 0;
            ResultSet rec0 = st.executeQuery("SELECT COUNT(*) FROM pages WHERE cid="+categoryID);
            while (rec0.next()){
                rows = rec0.getInt(1);
            }
            ResultSet rec = st.executeQuery("SELECT * FROM pages WHERE cid="+categoryID);
            System.out.println("iiiiiiiiiiii"+ rows);
            Page pages[] = new Page[rows];
            int i = 0;
            while (rec.next()) {
                pages[i] = new Page(rec);
                i++;
            }
            return pages;     
    }

}
