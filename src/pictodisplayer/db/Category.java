/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class Category{
    public Integer id;
    public String name;
    public String description;
    public Integer sort;
    public Integer stats;
    
    public static String db = "jdbc:derby:db_picto";

    
    public void loadFromName (String categoryName){
        try {
            
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();
            ResultSet rec = st.executeQuery("SELECT * FROM categories WHERE name = '"+categoryName+"'");
            while (rec.next()) {
                  id  = rec.getInt("id");
                  name = rec.getString("name");
                  description = rec.getString("description");
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
            st.executeUpdate("DELETE FROM categories WHERE id ="+this.id);
            st.close();
        } catch (Exception e) {
            System.out.println("Error - " + e);
        } 
    } 
        
    public static void add(String name, String description, Integer sort, Integer stats ){
        
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection conn = DriverManager.getConnection(Pictodb.getName());
            Statement st = conn.createStatement();

            st.executeUpdate("INSERT INTO categories "
                           + "(name, description, sort, stats, date) VALUES('"+name+"','"+description+"', "+sort+", "+stats+", CURRENT_DATE)");
            
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

            st.executeUpdate("UPDATE categories  SET "
                      + "name='"+this.name+"', description='"+this.description+"', sort="+this.sort+", stats="+this.stats+", date=CURRENT_DATE WHERE id="+this.id);
            
            st.close();
        } catch (Exception e) {
            
            System.out.println("Error - " + e.toString());
        }
    }
    public static void all(){
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