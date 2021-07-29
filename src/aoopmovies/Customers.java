/*s
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
import java.util.ArrayList;
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
import javafx.scene.control.Alert;
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
public class Customers extends Application {
    Stage customers_stage = new Stage();
    
    @Override
    public void start(Stage stage){
        stage.setTitle("Customers");
        stage.setScene(customersScene());
        stage.show();
    }
    
    public static Scene customersScene(){
        //gridPane()
        GridPane gridPane = new GridPane();
        
        Text name_label = new Text("Name: ");
        Text phone_label = new Text("Phone: ");
        Text email_label = new Text("Email: ");
        Text text4 = new Text("Registered: ");
        Text printoutQry = new Text("");
        
        TextField name = new TextField();
        TextField phone = new TextField();
        TextField email = new TextField();
        
        Button save_customer = new Button("Save Customer");
        Button remove_customer = new Button("Remove Customer");
        
        
        ComboBox names = new ComboBox();
        ObservableList<String> names_list= FXCollections.observableArrayList();
        
        //EVENTHANDLING MOUSEEVENTS
        //Register Customer
        EventHandler<MouseEvent> registerCustomer = (MouseEvent e) ->{
            try{
                Implementations.registerCustomer(name.getText(), phone.getText(), email.getText());
                printoutQry.setText("Customer Successfully Registered!");
            }catch(SQLException ex){
                Logger.getLogger(Implementations.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        
        //Fetch Customer
        EventHandler<MouseEvent> fetchCustomer = (MouseEvent e) ->{
            names.getItems().clear();
            names_list.clear();
            
            try{
                Implementations.fetchCustomer(names, names_list);
            }catch(SQLException ex){
                Logger.getLogger(Implementations.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        
        //Delete Customer
        EventHandler<MouseEvent> deleteCustomer = (MouseEvent e) ->{
            try{
                Implementations.deleteCustomer((String) names.getValue());
                printoutQry.setText("Customer Deleted!");
            }catch(SQLException ex){
                Logger.getLogger(Implementations.class.getName()).log(Level.SEVERE,null, ex);
            }
        };
        
        save_customer.setOnMouseClicked(registerCustomer);
        remove_customer.setOnMouseClicked(deleteCustomer);
        names.setOnMouseClicked(fetchCustomer);
        
        save_customer.setMinSize(250, 5);
        remove_customer.setMinSize(250, 5);
        
        
        gridPane.setMinSize(600, 400);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(name_label, 0, 0);
        gridPane.add(name, 1, 0);
        
        gridPane.add(phone_label, 0, 1);
        gridPane.add(phone, 1, 1);
        
        gridPane.add(email_label, 0, 2);
        gridPane.add(email, 1, 2);
        
        
        gridPane.add(save_customer, 1, 3);
        gridPane.add(text4, 0, 4);
        gridPane.add(names, 1, 4);
        gridPane.add(remove_customer, 1, 5);
        gridPane.add(printoutQry, 1, 6);
        

        
        save_customer.setStyle("-fx-background-color: #1A88A5; -fx-text-fill: white; -fx-font-size:13pt;");
        remove_customer.setStyle("-fx-background-color: #1A88A5; -fx-text-fill: white; -fx-font-size:13pt;");
        name.setStyle("-fx-font: normal  20px 'serif' ");
        phone.setStyle("-fx-font: normal  20px 'serif' ");
        email.setStyle("-fx-font: normal  20px 'serif' ");
        text4.setStyle("-fx-font: normal  20px 'serif' ");
        gridPane.setStyle("-fx-background-color: #9B9B9B; ");
        
        
        
     Scene myScene = new Scene(gridPane);
     return myScene;
    }
    
    public static void main(String[] args){
        launch(args);
    }
    
}
