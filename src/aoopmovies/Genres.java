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
public class Genres extends Application {
    Stage genres_stage = new Stage();
    
    @Override
    public void start(Stage stage) {
        stage.setTitle("Genres");
        stage.setScene(genreScene());
        stage.show();
    }
    
    public static Scene genreScene(){
        GridPane gridPane = new GridPane();
        Text text1 = new Text("Name: ");
        Text text2  = new Text("Registered: ");
        Text printoutQry = new Text("");
        
        TextField genre_name = new TextField();
        genre_name.setMinSize(250, 5);
        
        ComboBox comboBox = new ComboBox();
        ObservableList<String> genre_list= FXCollections.observableArrayList();
        
        comboBox.setMinSize(250, 10);
        
        Button save_genre = new Button("Save");
        save_genre.setMinSize(250, 5);
        Button remove_genre = new Button("Remove");
        remove_genre.setMinSize(250, 5);
        
        
        //REGISTER GENRE
        EventHandler<MouseEvent> registerGenre = (MouseEvent e) ->{
            try{
                Implementations.registerGenre(genre_name.getText());
                printoutQry.setText("Genre Successfully Registered!");
            }catch(SQLException ex){
                Logger.getLogger(Implementations.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        
        
        //FETCH GENRE IN COMBOBOX
        EventHandler<MouseEvent> fetchGenre = (MouseEvent e) ->{
            comboBox.getItems().clear();
            genre_list.clear();
            try{
                Implementations.fetchGenre(comboBox, genre_list);
            }catch(SQLException ex){
                Logger.getLogger(Implementations.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        
        save_genre.setOnMouseClicked(registerGenre);
        comboBox.setOnMouseClicked(fetchGenre);
        // remove_genre.setOnMouseClicked(removeGenre);
        
        
        gridPane.setMinSize(600, 400);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(text1, 0, 0);
        gridPane.add(genre_name, 1, 0);
        gridPane.add(save_genre, 1, 1);
        gridPane.add(text2, 0, 2);
        gridPane.add(comboBox, 1, 2);
        gridPane.add(remove_genre, 1, 3);
        gridPane.add(printoutQry, 1, 4);
        
        save_genre.setStyle("-fx-background-color: #1A88A5; -fx-text-fill: white; -fx-font-size:13pt;");
        remove_genre.setStyle("-fx-background-color: #1A88A5; -fx-text-fill: white; -fx-font-size:13pt;");
        text1.setStyle("-fx-font: normal bold 20px 'serif' ");
        text2.setStyle("-fx-font: normal bold 20px 'serif' ");
        gridPane.setStyle("-fx-background-color: #9B9B9B; ");
        
        Scene scene = new Scene(gridPane);
        return scene;
    }
    
    public static void main (String[] args){
        launch(args);
    }
    
}