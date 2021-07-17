/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exprobenetworks;


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
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author nickb
 */
public class Customers extends Application {
    Stage customers_stage = new Stage();
    
    @Override
    public void start(Stage stage) {
        
        Text text1 = new Text("Name: ");
        Text text2 = new Text("Phone: ");
        Text text3 = new Text("Email: ");
        Text text4 = new Text("Registered: ");
        
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();
        ComboBox comboBox = new ComboBox();
        
        comboBox.setMinSize(250, 10);
       
       
        Button button1 = new Button("Save Customer");
        Button button2 = new Button("Remove Customer");
        
        button1.setMinSize(250, 5);
        button2.setMinSize(250, 5);
        
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(600, 400);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(text1, 0, 0);
        gridPane.add(textField1, 1, 0);
        
        gridPane.add(text2, 0, 1);
        gridPane.add(textField2, 1, 1);
        
        gridPane.add(text3, 0, 2);
        gridPane.add(textField3, 1, 2);
        
        
        gridPane.add(button1, 1, 3);
        gridPane.add(text4, 0, 4);
        gridPane.add(comboBox, 1, 4);
        gridPane.add(button2, 1, 5);
        
        button1.setStyle("-fx-background-color: #1A88A5; -fx-text-fill: white; -fx-font-size:13pt;");
        button2.setStyle("-fx-background-color: #1A88A5; -fx-text-fill: white; -fx-font-size:13pt;");
        text1.setStyle("-fx-font: normal  20px 'serif' ");
        text2.setStyle("-fx-font: normal  20px 'serif' ");
        text3.setStyle("-fx-font: normal  20px 'serif' ");
        text4.setStyle("-fx-font: normal  20px 'serif' ");
        gridPane.setStyle("-fx-background-color: #9B9B9B; ");
        
        Scene scene = new Scene(gridPane);
        
        stage.setTitle("Movie Library System");
        stage.setScene(scene);
        stage.show();
        
       
        
    }
    
}
