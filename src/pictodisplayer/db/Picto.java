
package pictodisplayer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import pictodisplayer.*;


/**
 *
 * @author Paweł
 */
public class Picto{
    
    public Integer id;
    public String name;
    public String fileName;
    public Integer pageId;
    public Integer sort;
    public Integer stats;
    
    public static String db = "jdbc:derby:db_picto";

    
    public void loadFromName (String pageName){
        try {
            
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            ResultSet rec = st.executeQuery("SELECT * FROM pictos WHERE name = '"+pageName+"'");
            while (rec.next()) {
                  id  = rec.getInt("id");
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
        
    public static void add(Integer pid, String name, String fileName, Integer sort, Integer stats ){
        
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();

            st.executeUpdate("INSERT INTO pictos "
                           + "(pid, name, file_name, sort, stats, date) VALUES("+pid+", '"+name+"', '"+fileName+"', "+sort+", "+stats+", CURRENT_DATE)");
            
            st.close();
        } catch (Exception e) {
            
            System.out.println("Error - " + e.toString());
        }
    }
    
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
