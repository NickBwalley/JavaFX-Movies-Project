/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aoopmovies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Register extends Application{
    
    //create a new stage for registration form
    Stage register_page = new Stage();
    
    @Override
    public void start(Stage primaryStage){
     
        Text username_label = new Text("Username: ");
        Text email_label = new Text("Email Address: ");
        Text password_label = new Text("SetPassword: ");
        Text printoutQry = new Text();
        
        Button register_button = new Button("Register");
        Button close_button = new Button("Close");
        
        TextField username = new TextField();
        TextField email = new TextField();
        PasswordField password = new PasswordField();
        
        
        GridPane gridPane = new GridPane();
        //Label Fields 
        gridPane.add(username_label, 0,0);
        gridPane.add(email_label, 0,1);
        gridPane.add(password_label, 0,2);
        //Text Fields
        gridPane.add(username, 1,0);
        gridPane.add(email, 1,1);
        gridPane.add(password, 1,2);
        gridPane.add(register_button, 1,3);
        gridPane.add(close_button, 2,3);
        gridPane.add(printoutQry, 1,4);
        
        
        
        
        gridPane.setMinSize(500,250);
        gridPane.setVgap(10);
        gridPane.setHgap(20);
        gridPane.setAlignment(Pos.CENTER);
        
        
        //EventHandler for close button
        close_button.setOnMouseClicked((new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                primaryStage.close();
            }
        }));
        
        
        //REGISTER AND INSERT INFORMATION TO THE DATABASE
        register_button.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                final String username1 = username.getText();
                final String email1 = email.getText();
                final String password1 = password.getText();
                
                try
                {
                    //step one
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    
                    //step two
                    Connection con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/exprobe_networks", "root","");
                    
                    //step three
                    Statement stmt = con.createStatement();
                    
                    //step four
                    String mySQL_stmt = "INSERT INTO `users`(`username`,`email`, `password`) VALUES ("+"'"+username1+"'"+","+"'"+email1+"'"+","+"'"+password1+"'"+")";
                    System.out.println(mySQL_stmt);
                    stmt.executeUpdate(mySQL_stmt);
                    
                    //step five
                    con.close();
                    printoutQry.setText("Record Successfully Saved!");
                }
                catch(Exception ee){
                    System.out.println("ee");printoutQry.setText("Record NOT SAVED!");;
                }
                
            }
        });
        
        // SCENE
        Scene my_scene = new Scene(gridPane);
        gridPane.setStyle("-fx-background-color: #9B9B9B; ");
        primaryStage.setScene(my_scene);
        primaryStage.setTitle("Welcome to Exprobe Networks Limited!");
        primaryStage.show();      
               
      
    }
}

