/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aoopmovies;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author nickb
 */
public class Rentals extends Application {
    
    Stage rentals_stage = new Stage();
    
    @Override
    public void start(Stage stage){
        stage.setTitle("Rentals");
        stage.setScene(rentalScene());
        stage.show();
    }
    
    
    public static Scene rentalScene() {
        
        Text text1 = new Text("Customer: ");
        Text text2 = new Text("Genre: ");
        Text text3 = new Text("Movies: ");
        Text text4 = new Text("Borrowed: ");
        Text text5 = new Text("Returned: ");
        Text borrowed_movie = new Text("");
        Text returned_movie = new Text("");
        Text printoutQry = new Text("");
        
        ComboBox comboBox1 = new ComboBox();
        ComboBox comboBox2 = new ComboBox();
        ComboBox comboBox3 = new ComboBox();
        ComboBox comboBox4 = new ComboBox();
        ComboBox comboBox5 = new ComboBox();
        
        ObservableList<String> genre_list1 = FXCollections.observableArrayList();
        ObservableList<String> genre_list2 = FXCollections.observableArrayList();
        ObservableList<String> genre_list3 = FXCollections.observableArrayList();
        ObservableList<String> genre_list4 = FXCollections.observableArrayList();
        ObservableList<String> genre_list5 = FXCollections.observableArrayList();
        
        comboBox1.setMinSize(250, 10);
        comboBox2.setMinSize(250, 10);
        comboBox3.setMinSize(250, 10);
        comboBox4.setMinSize(250, 10);
        comboBox5.setMinSize(250, 10);
        
        Button button1 = new Button("Save Rental");
        Button button2 = new Button("Return Movie");
        
        button1.setMinSize(250, 5);
        button2.setMinSize(250, 5);
        
        //Fetch Customer
        EventHandler<MouseEvent> fetchCustomer = (MouseEvent e) ->{
            comboBox1.getItems().clear();
            genre_list1.clear();
            
            try{
                Implementations.fetchCustomer(comboBox1, genre_list1);
            }catch(SQLException ex){
                Logger.getLogger(Implementations.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        
        comboBox1.setOnMouseClicked(fetchCustomer);
        
        
        //FETCH GENRE IN COMBOBOX
        EventHandler<MouseEvent> fetchGenre = (MouseEvent e) ->{
            // comboBox2.getItems().clear();
            // genre_list1.clear();
            try{
                Implementations.fetchGenre(comboBox2, genre_list3);
            }catch(SQLException ex){
                Logger.getLogger(Implementations.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        
        comboBox2.setOnMouseClicked(fetchGenre);
        
        // FETCH LIST OF REGISTERED MOVIES IN A GENRE
        EventHandler<MouseEvent> fetchRegisteredMovies = (MouseEvent e) ->{
            comboBox3.getItems().clear();
            try{
                Implementations.fetchRegisteredMovies((String) comboBox2.getValue(), comboBox3, genre_list2);
            }catch(SQLException ex){
                Logger.getLogger(Implementations.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        
        comboBox3.setOnMouseClicked(fetchRegisteredMovies);
        
        
        // CUSTOMER RENT/BORROW MOVIE 
        EventHandler<MouseEvent> borrowMovie = (MouseEvent e) -> {
            try{
                Implementations.borrowMovie((String) comboBox1.getValue(), (String) comboBox2.getValue(), (String) comboBox3.getValue(), borrowed_movie.getText(), returned_movie.getText());
                printoutQry.setText((String) comboBox1.getValue() + " successfully borrowed " + (String) comboBox3.getValue());
            }catch(SQLException ex){
                Logger.getLogger(Implementations.class.getName()).log(Level.SEVERE,null, ex);
            }
            // printoutQry.setText((String) comboBox3.getValue());
        };
        
        // FETCH LIST OF BORROWED MOVIES BY A CUSTOMER
        EventHandler<MouseEvent> fetchBorrowedMovies = (MouseEvent e) ->{
            // registered_movies.getItems().clear();
            genre_list4.clear();
            
            try{
                Implementations.fetchBorrowedMovies((String) comboBox1.getValue(), comboBox4, genre_list4);
            }catch(SQLException ex){
                Logger.getLogger(Implementations.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        comboBox4.setOnMouseClicked(fetchBorrowedMovies);
        button1.setOnMouseClicked(borrowMovie);
        
        // RETURN MOVIE BORROWED BY CUSTOMER
        EventHandler<MouseEvent> customerReturnMovie = (MouseEvent e) ->{
            // registered_movies.getItems().clear();
            genre_list5.clear();
            
            try{
                Implementations.customerReturnMovie((String)comboBox1.getValue(), (String)comboBox4.getValue());
                System.out.println((String) comboBox1.getValue());
                System.out.println((String) comboBox4.getValue());
            }catch(SQLException ex){
                Logger.getLogger(Implementations.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        button2.setOnMouseClicked(customerReturnMovie);
        
        // FETCH LIST OF BORROWED MOVIES BY A CUSTOMER
        EventHandler<MouseEvent> fetchReturnedMovies = (MouseEvent e) ->{
            // registered_movies.getItems().clear();
            genre_list4.clear();
            
            try{
                Implementations.fetchReturnedMovies((String) comboBox1.getValue(), comboBox5, genre_list4);
            }catch(SQLException ex){
                Logger.getLogger(Implementations.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        
        comboBox5.setOnMouseClicked(fetchReturnedMovies);
        
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(600, 400);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(text1, 0, 0);
        gridPane.add(comboBox1, 1, 0);
        
        gridPane.add(text2,   0, 1);
        gridPane.add(comboBox2, 1, 1);
        
        gridPane.add(text3, 0, 2);
        gridPane.add(comboBox3, 1, 2);
        
        
        gridPane.add(button1, 1, 3);
        gridPane.add(text4, 0, 4);
        
        gridPane.add(comboBox4, 1, 4);
        gridPane.add(button2, 1, 5);
        
        gridPane.add(text5  , 0, 6);
        gridPane.add(comboBox5, 1, 6);
        gridPane.add(printoutQry, 1, 7);
        
        
        button1.setStyle("-fx-background-color: #1A88A5; -fx-text-fill: white; -fx-font-size:13pt;");
        button2.setStyle("-fx-background-color: #1A88A5; -fx-text-fill: white; -fx-font-size:13pt;");
        text1.setStyle("-fx-font: normal  20px 'serif' ");
        text2.setStyle("-fx-font: normal  20px 'serif' ");
        text3.setStyle("-fx-font: normal  20px 'serif' ");
        text4.setStyle("-fx-font: normal  20px 'serif' ");
        text5.setStyle("-fx-font: normal  20px 'serif' ");
        
        gridPane.setStyle("-fx-background-color: #9B9B9B; ");
        
        Scene scene = new Scene(gridPane);
        
        return scene;
        
    }
    
    public static void main(String [] args){
        launch(args);
    }
    
}
