
package pictodisplayer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import pictodisplayer.*;


/**
 *
 * @author Pawel Kopec <paweelkopec@gmail.com>
 */
public class Page{
    public Integer id;
    public String name;
    public Integer categoryId;
    public Integer sort;
    public Integer stats;
    
    public static String db = "jdbc:derby:db_picto";

    /**
     * Load form database
     * @param pageName 
     */
    public void loadFromName (String pageName){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            ResultSet rec = st.executeQuery("SELECT * FROM pages WHERE name = '"+pageName+"'");
            while (rec.next()) {
                  id  = rec.getInt("id");
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
     * Delete current Page
     */
    public void delete (){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM pages WHERE id ="+this.id);
            st.close();
        } catch (Exception e) {
            System.out.println("Error - " + e);
        } 
    } 
    /**
     * Add new Page
     * @param cid ID kategori
     * @param name Nazwa kategori
     * @param sort Sortowanie kategori
     * @param stats Liczba wyświetleń
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
     * Update current Page
     */ 
    public void update(){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            st.executeUpdate("UPDATE pages  SET "
                      + "name='"+this.name+"', sort="+this.sort+", stats="+this.stats+", date=CURRENT_DATE WHERE id="+this.id);
            st.close();
        } catch (Exception e) {
            System.out.println("Error - " + e.toString());
        }
    }

}
