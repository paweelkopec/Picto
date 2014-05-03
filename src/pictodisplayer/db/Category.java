package pictodisplayer.db;

import java.io.File;
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
     * Consturct
     * @param rec 
     */
    public Category(ResultSet rec) {
        try {
                id = rec.getInt("id");
                name = rec.getString("name");
                description = rec.getString("description");
                file_name = rec.getString("file_name");
                sort = rec.getInt("sort");
                stats = rec.getInt("stats");
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
            File file = new File("C:\\PictoDisplayer\\" + this.file_name);
            file.delete();
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
    public static void add(String name, String description, String file_name, Integer sort, Integer stats) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO categories "
                    + "(name, description, file_name, sort, stats, date) VALUES('" + name + "','" + description + "','" + file_name + "', " + sort + ", " + stats + ", CURRENT_DATE)");
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
    /**
     * Search Category key
     * @param categoryID
     * @param categorys
     * @return 
     */
    public static int searchKey(int categoryID, Category[] categorys){
        for(int i=0; i<categorys.length; i++){
           if(categorys[i].id==categoryID)
               return i;
        }
        return 0;
    }
    


}
