/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exprobenetworks;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    Connection con;
    ComboBox registered_users;
    
    @Override
    public void start(Stage stage) {
        
        Text name_label = new Text("Name: ");
        Text phone_label = new Text("Phone: ");
        Text email_label = new Text("Email: ");
        Text text4 = new Text("Registered: ");
        Text printoutQry = new Text();
        
        TextField name = new TextField();
        TextField phone = new TextField();
        TextField email = new TextField();
        
        
                     
        
         
       
        Button save_customer = new Button("Save Customer");
        Button remove_customer = new Button("Remove Customer");
        Button registered = new Button("View Registered");
        
        
        // List registered users fillComboBox() method
        registered.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                fillComboBox();
            }
        });
        
        try{
          Class.forName("com.mysql.cj.jdbc.Driver"); //step one


           con =DriverManager.getConnection("jdbc:mysql://localhost:3306/exprobe_networks?autoReconnect=true&useSSL=false","root","");  //step two
          
          Statement st=con.createStatement();   //step three
          String statement = "SELECT *  from customers";
          ResultSet rs = st.executeQuery(statement); //step four

          while(rs.next()){
            String col=rs.getString("name"); 
              System.out.println(col);
              registered_users = new ComboBox(FXCollections.observableArrayList(col));
              registered_users.setMinSize(250, 10);
              
           // gives you column value on each iteration
          }
          con.close();
            
        }
        catch(Exception ee){System.out.println(ee);System.out.println("Connection error");
        }
        
        
        
        
        
        
        //String week_days[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        //registered_users = new ComboBox(FXCollections.observableArrayList(week_days));
        
        //registered_users.setMinSize(250, 10);
        
        
        //REGISTER AND INSERT INFORMATION TO THE DATABASE
        save_customer.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                final String name1 = name.getText();
                final String phone1 = phone.getText();
                final String email1 = email.getText();
                
                try
                {
                    //step one
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    
                    //step two
                    Connection con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/exprobe_networks", "root","");
                    
                    //step three
                    Statement stmt = con.createStatement();
                    
                    //step four
                    String mySQL_stmt = "INSERT INTO `customers`(`name`,`phone`, `email`) VALUES ("+"'"+name1+"'"+","+"'"+phone1+"'"+","+"'"+email1+"'"+")";
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
        
           
                
        
        save_customer.setMinSize(250, 5);
        remove_customer.setMinSize(250, 5);
        
        GridPane gridPane = new GridPane();
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
        gridPane.add(registered_users, 1, 4);
        gridPane.add(remove_customer, 1, 5);
        gridPane.add(printoutQry, 1, 6);
        gridPane.add(registered, 1, 7);

        
        save_customer.setStyle("-fx-background-color: #1A88A5; -fx-text-fill: white; -fx-font-size:13pt;");
        remove_customer.setStyle("-fx-background-color: #1A88A5; -fx-text-fill: white; -fx-font-size:13pt;");
        name.setStyle("-fx-font: normal  20px 'serif' ");
        phone.setStyle("-fx-font: normal  20px 'serif' ");
        email.setStyle("-fx-font: normal  20px 'serif' ");
        text4.setStyle("-fx-font: normal  20px 'serif' ");
        gridPane.setStyle("-fx-background-color: #9B9B9B; ");
        
        Scene scene = new Scene(gridPane);
        
        stage.setTitle("Movie Library System(Customer)");
        stage.setScene(scene);
        stage.show();
        
       
       
       
        
    }
    
    //method fillComboBox
    public void fillComboBox(){
        try{
          Class.forName("com.mysql.cj.jdbc.Driver"); //step one


           con =DriverManager.getConnection("jdbc:mysql://localhost:3306/exprobe_networks?autoReconnect=true&useSSL=false","root","");  //step two
          
          Statement st=con.createStatement();   //step three
          String statement = "SELECT *  from customers";
          ResultSet rs = st.executeQuery(statement); //step four

          while(rs.next()){
            String col=rs.getString("name"); 
              System.out.println(col);
              // registered_users = new ComboBox(FXCollections.observableArrayList(col));
              // System.out.println(registered_users);
           // gives you column value on each iteration
          }
          con.close();
            
        }
        catch(Exception ee){System.out.println(ee);System.out.println("Connection error");
        } 
        
    }
    
    public static void main(String[] args){
        launch(args);
    }
    
}
