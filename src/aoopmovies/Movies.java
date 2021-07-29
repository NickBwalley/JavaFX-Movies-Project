/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aoopmovies;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
    public void start(Stage stage) {
        
        Text text0 = new Text("Genres: ");
        Text text1 = new Text("Name: ");
        Text text2  = new Text("Registered: ");
        
        TextField textField1 = new TextField();
        ComboBox comboBox0 = new ComboBox();
        /*comboBox0.getItems().add("Horror");
        comboBox0.getItems().add("Comedy");
        comboBox0.getItems().add("Action");*/
        comboBox0.getItems().addAll(
        "Horror",
        "Comedy",
        "Action",
        "Romance",
        "Animation"
        );
        
        ComboBox comboBox1 = new ComboBox();
        
        comboBox0.setMinSize(250, 20);
        comboBox1.setMinSize(250, 20);
        
        Button button1 = new Button("Save Movie");
        Button button2 = new Button("Remove Movie");
        
        button1.setMinSize(250, 5);
        button2.setMinSize(250, 5);
        
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(600, 400);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(text0, 0, 0);
        gridPane.add(comboBox0, 1, 0);
        
        gridPane.add(text1, 0, 1);
        gridPane.add(textField1, 1, 1);
        gridPane.add(button1, 1, 2);
        
        gridPane.add(text2, 0, 3);
        gridPane.add(comboBox1, 1, 3);
        gridPane.add(button2, 1, 4);
        
        button1.setStyle("-fx-background-color: #1A88A5; -fx-text-fill: white; -fx-font-size:13pt;");
        button2.setStyle("-fx-background-color: #1A88A5; -fx-text-fill: white; -fx-font-size:13pt;");
        text0.setStyle("-fx-font: normal bold 20px 'serif' ");
        text1.setStyle("-fx-font: normal bold 20px 'serif' ");
        text2.setStyle("-fx-font: normal bold 20px 'serif' ");
        gridPane.setStyle("-fx-background-color: #9B9B9B; ");
        
        Scene scene = new Scene(gridPane);
        
        stage.setTitle("Movie Library System");
        stage.setScene(scene);
        stage.show();
                     
    }
    
}
