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
    
    // FETCH LIST OF REGISTERED MOVIES IN A SPECIFIC GENRE
    public static void fetchRegisteredMovies(String genre_name, ComboBox genre, ObservableList<String> genre_list1) throws SQLException{
        Connection conn = dbConnect();
        Statement st = conn.createStatement();
        String query = "SELECT movie_name FROM movies WHERE genre_name='"+genre_name+"'";
        ResultSet rs = st.executeQuery(query);
        // genre_list1.clear();
        while(rs.next()){
            genre_list1.add(rs.getString("movie_name"));
        }
        genre.setItems(genre_list1);
        conn.close();
    }
    
    /*-------------------------------------------------------------------------
        RENTALS
      -------------------------------------------------------------------------*/
    // BORROW MOVIE
    public static void borrowMovie(String customer_name, String genre_name, String movie_name, String borrowed_movie, String returned_movie )throws SQLException{
        Connection conn = dbConnect();
        Statement st = conn.createStatement();
        String query = "INSERT INTO rentals(customer_name, genre_name, borrowed_movie, returned_movie) "
                + "VALUES('"+customer_name+"', '"+genre_name+"', '"+movie_name+"', '"+returned_movie+"')";
        st.executeUpdate(query);
        conn.close();
    }
    
    // FETCH BORROWED MOVIES BY A CUSTOMER
    public static void fetchBorrowedMovies(String customer_name, ComboBox genre, ObservableList<String> genre_list1) throws SQLException{
        Connection conn = dbConnect();
        Statement st = conn.createStatement();
        String query = "SELECT borrowed_movie FROM rentals WHERE customer_name='"+customer_name+"'";
        ResultSet rs = st.executeQuery(query);
        // genre_list1.clear();
        while(rs.next()){
            genre_list1.add(rs.getString("borrowed_movie"));
        }
        genre.setItems(genre_list1);
        conn.close();
    }
    
    //UPDATE ON CUSTOMER RETURN MOVIE
    public static void customerReturnMovie(String customer_name, String borrowed_movie) throws SQLException{
//        System.out.println(customer_name);
//        System.out.println(borrowed_movie);
        Connection conn = dbConnect();
        Statement st = conn.createStatement();
        String query = "SELECT id from rentals WHERE customer_name = '"+customer_name+"' AND borrowed_movie = '"+borrowed_movie+"'";    
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            int id = rs.getInt("id");
            System.out.println(id);
            String query2 = "UPDATE rentals SET  borrowed_movie = '', returned_movie = '"+borrowed_movie+"' WHERE id = '"+id+"' ";
            st.executeUpdate(query2);
        }
        
        //st.executeUpdate(query2);
        //System.out.println( name + " successfully returned!");
        // System.out.println(query2);
        conn.close();
    }
    
    // FETCH RETURNED MOVIES BY A CUSTOMER
    public static void fetchReturnedMovies(String customer_name, ComboBox genre, ObservableList<String> genre_list4) throws SQLException{
        Connection conn = dbConnect();
        Statement st = conn.createStatement();
        String query = "SELECT returned_movie FROM rentals WHERE customer_name='"+customer_name+"'";
        ResultSet rs = st.executeQuery(query);
        // genre_list1.clear();
        while(rs.next()){
            genre_list4.add(rs.getString("returned_movie"));
        }
        genre.setItems(genre_list4);
        conn.close();
    }
}
  