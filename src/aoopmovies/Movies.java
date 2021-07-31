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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author nickb
 */
public class Movies extends Application {
    
    Stage movie_stage = new Stage();
    
    @Override
    public void start(Stage stage){
        stage.setTitle("Movies");
        stage.setScene(movieScene());
        stage.show();
    }
    
    public static Scene movieScene() {
        
        Text text0 = new Text("Genres: ");
        Text text1 = new Text("Name: ");
        Text text2  = new Text("Registered: ");
        
        TextField movie_name = new TextField();
        
        ComboBox genre_list = new ComboBox();
        
        
        ComboBox registered_movies = new ComboBox();
        ObservableList<String> genre_list1 = FXCollections.observableArrayList();
        
        genre_list.setMinSize(250, 20);
        registered_movies.setMinSize(250, 20);
        
        Button save_movie = new Button("Save Movie");
        Button remove_movie = new Button("Remove Movie");
        
        save_movie.setMinSize(250, 5);
        remove_movie.setMinSize(250, 5);
        
        //FETCH GENRE IN COMBOBOX
        EventHandler<MouseEvent> fetchGenre = (MouseEvent e) ->{
            genre_list.getItems().clear();
            genre_list1.clear();
            try{
                Implementations.fetchGenre(genre_list, genre_list1);
            }catch(SQLException ex){
                Logger.getLogger(Implementations.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        
        // GET USER ID
        EventHandler<MouseEvent> registerMovie = (MouseEvent e) -> {
            try{
                Implementations.registerMovie((String) genre_list.getValue());
                // System.out.println((String) genre_list.getValue());
            }catch(SQLException ex){
                Logger.getLogger(Implementations.class.getName()).log(Level.SEVERE,null, ex);
            }
        };
        
        genre_list.setOnMouseClicked(fetchGenre);
        save_movie.setOnMouseClicked(registerMovie);
        
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(600, 400);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(text0, 0, 0);
        gridPane.add(genre_list, 1, 0);
        
        gridPane.add(text1, 0, 1);
        gridPane.add(movie_name, 1, 1);
        gridPane.add(save_movie, 1, 2);
        
        gridPane.add(text2, 0, 3);
        gridPane.add(registered_movies, 1, 3);
        gridPane.add(remove_movie, 1, 4);
        
        save_movie.setStyle("-fx-background-color: #1A88A5; -fx-text-fill: white; -fx-font-size:13pt;");
        remove_movie.setStyle("-fx-background-color: #1A88A5; -fx-text-fill: white; -fx-font-size:13pt;");
        text0.setStyle("-fx-font: normal bold 20px 'serif' ");
        text1.setStyle("-fx-font: normal bold 20px 'serif' ");
        text2.setStyle("-fx-font: normal bold 20px 'serif' ");
        gridPane.setStyle("-fx-background-color: #9B9B9B; ");
        
        Scene scene = new Scene(gridPane);
        return scene;
                     
    }
    
    public static void main(String [] args){
       launch(args);
    }
    
}
