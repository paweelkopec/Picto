package pictodisplayer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import pictodisplayer.*;

/**
 * Database Category
 *
 * @author Pawel Kopec <paweelkopec@gmail.com>
 */
public class Category {

    public Integer id;
    public String name;
    public String description;
    public String file_name;
    public Integer sort;
    public Integer stats;

    public static String db = "jdbc:derby:db_picto";

    /**
     * Construct load from id
     * @param id 
     */
    public Category(int id){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            ResultSet rec = st.executeQuery("SELECT * FROM categories WHERE id = " +  id );
            while (rec.next()) {
                this.id = rec.getInt("id");
                name = rec.getString("name");
                description = rec.getString("description");
                file_name = rec.getString("file_name");
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
     * Construct load from name
     *
     * @param categoryName
     */
    public Category(String categoryName) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            ResultSet rec = st.executeQuery("SELECT * FROM categories WHERE name = '" + categoryName + "'");
            while (rec.next()) {
                id = rec.getInt("id");
                name = rec.getString("name");
                description = rec.getString("description");
                file_name = rec.getString("file_name");
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
     * Delete Category
     */
    public void delete() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM categories WHERE id =" + this.id);
            Page pages[] = Page.list(this.id);
            for(int i = 0; i < pages.length; i++) { 
                pages[i].delete();
            }
            st.close();
        } catch (Exception e) {
            System.out.println("Error - " + e);
        }
    }

    /**
     * Add New Category
     *
     * @param name
     * @param description
     * @param sort
     * @param stats
     */
    public static void add(String name, String description, Integer sort, Integer stats) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO categories "
                    + "(name, description, sort, stats, date) VALUES('" + name + "','" + description + "', " + sort + ", " + stats + ", CURRENT_DATE)");
            st.close();
        } catch (Exception e) {

            System.out.println("Error - " + e.toString());
        }
    }

    /**
     * Update Category
     */
    public void update() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            st.executeUpdate("UPDATE categories  SET "
                    + "name='" + this.name + "', description='" + this.description + "', sort=" + this.sort + ", stats=" + this.stats + ", date=CURRENT_DATE WHERE id=" + this.id);
            st.close();
        } catch (Exception e) {
            System.out.println("Error - " + e.toString());
        }
    }

    /**
     * Load all Categories
     */
    public static void all() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            ResultSet rec = st.executeQuery("SELECT * FROM categories ORDER BY name");
            while (rec.next()) {
            }
            st.close();
        } catch (Exception e) {
            System.out.println("Error - " + e.toString());
        }
    }

}
