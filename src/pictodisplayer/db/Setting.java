package pictodisplayer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Database Page
 *
 * @author Pawel Kopec <paweelkopec@gmail.com>
 */
public class Setting {

    public Integer id;
    public String name;
    public String value;
    public static String db = "jdbc:derby:db_picto";

    /**
     * 
     * @param name 
     */
    public Setting(String name) {
        //this.name = name;
        try {
            try {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                Connection conn = DriverManager.getConnection(Pictodb.getName());
                Statement st = conn.createStatement();
                ResultSet rec = st.executeQuery("SELECT * FROM setting WHERE name = '" + name+"'");

                if (rec.next()){
                    id = rec.getInt("id");
                    this.name = rec.getString("name");
                    value = rec.getString("value");
                }else{
                    this.add(name, "");
                }
                st.close();
                
            }catch (SQLException e) {
                this.add(name, "");
            }
        } catch (Exception e) {
            System.out.println("Error - " + e.toString());
        }
    }

     /**
     * Add new page
     * @param cid
     * @param name
     * @param sort 
     * @param stats
     */
    public void add(String name , String value){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO setting (name, value) VALUES ('"+name+"', '"+value+"')");
            st.close();
        } catch (Exception e) {
            System.out.println("Error add - " + e.toString());
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
            st.executeUpdate("DELETE FROM setting WHERE id =" + this.id);
            st.close();
        } catch (Exception e) {
            System.out.println("Error - " + e);
        }
    }
    
    public void setValue(String value){
        this.value = value;
        update();
    }

    /**
     * Update current Page
     */
    public void update() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            
            String commandString = "UPDATE setting  SET value=? WHERE name=?";
            PreparedStatement st = conn.prepareStatement(commandString);
            
            String nameReplace = this.value.replaceAll("\"", "\\\"");
            st.setString(1,   nameReplace); 
            st.setString(2, this.name);  
            st.execute();
            conn.commit();
            st.close();
        } catch (Exception e) {
            System.out.println("Error update - " + e.toString());
        }
    }


}
