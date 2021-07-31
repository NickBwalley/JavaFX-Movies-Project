/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aoopmovies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 *
 * @author nickb
 */
public class Implementations {
    
/*------------------------------------------
    CUSTOMERS METHOD IMPLEMENTATIONS
  -----------------------------------------*/
    //DATABASE CONNECTION
    public static Connection dbConnect(){
        
        String url = "jdbc:mysql://localhost:3306/exprobe_networks";
        String username = "root";
        String password = "";
        
        //load drivers
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        
        //database connection
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    //REGISTER CUSTOMERS
    public static void registerCustomer(String name, String phonenumber, String email)throws SQLException{
        Connection conn = dbConnect();
        Statement st = conn.createStatement();
        String query = "INSERT INTO customers(name, phone, email) VALUES('"+name+"', '"+phonenumber+"', '"+email+"')";
        st.executeUpdate(query);
        System.out.println(name + " successfully registered!");
        conn.close();
    }
    
    //FETCH AND DISPLAY CUSTOMERS IN COMBOBOX
    public static void fetchCustomer(ComboBox customers, ObservableList<String> registeredList) throws SQLException{
        Connection conn = dbConnect();
        Statement st = conn.createStatement();
        String query = "SELECT name from customers";
        ResultSet rs = st.executeQuery(query);
        
        while(rs.next()){
            registeredList.add(rs.getString("name"));
        }
        customers.setItems(registeredList);
        conn.close();
    }
    
    //DELETE CUSTOMERS
    public static void deleteCustomer(String name) throws SQLException{
        Connection conn = dbConnect();
        Statement st = conn.createStatement();
        String query = "DELETE FROM customers WHERE name='"+name+"'";
        st.executeUpdate(query);
        System.out.println(name + " successfully Deleted!");
        conn.close();
        
    }
    
    /*-------------------------------------------------------------------------
        GENRES
      -------------------------------------------------------------------------*/
     //REGISTER GENRE   
    public static void registerGenre(String genre_name)throws SQLException{
        Connection conn = dbConnect();
        Statement st = conn.createStatement();
        String query = "INSERT INTO genres(genre_name) VALUES('"+genre_name+"')";
        st.executeUpdate(query);
        System.out.println(genre_name + " successfully registered!");
        conn.close();
    }
    
    //FETCH AND DISPLAY GENRE IN COMBOBOX
    public static void fetchGenre(ComboBox genre, ObservableList<String> registeredList) throws SQLException{
        Connection conn = dbConnect();
        Statement st = conn.createStatement();
        String query = "SELECT genre_name from genres";
        ResultSet rs = st.executeQuery(query);
        
        while(rs.next()){
            registeredList.add(rs.getString("genre_name"));
        }
        genre.setItems(registeredList);
        conn.close();
    }
    
    // DELETE GENRE
    public static void deleteGenre(String name) throws SQLException{
        Connection conn = dbConnect();
        Statement st = conn.createStatement();
        String query = "DELETE FROM genres WHERE genre_name='"+name+"'";
        st.executeUpdate(query);
        System.out.println( name + " successfully deleted");
        conn.close();
    }
    
    /*-------------------------------------------------------------------------
        MOVIES
      -------------------------------------------------------------------------*/
    // REGISTER MOVIE
    public static void registerMovie(String name, String movie_name) throws SQLException{
        Connection conn = dbConnect();
        Statement st = conn.createStatement();
        String query = "SELECT id FROM genres WHERE genre_name='"+name+"'";
        ResultSet rs = st.executeQuery(query);
        
        while(rs.next()){
            int genre_id = rs.getInt("id");
            // System.out.println(genre_id);
            // System.out.println(name);
            Statement st1 = conn.createStatement();
            String query1 = "INSERT INTO movies(genre_id, genre_name, movie_name) VALUES('"+genre_id+"','"+name+"', '"+movie_name+"')";
            st1.executeUpdate(query1);
            
        }
        
        conn.close();
    }
    
}
  