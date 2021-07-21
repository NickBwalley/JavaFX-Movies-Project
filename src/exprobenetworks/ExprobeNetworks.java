package exprobenetworks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 *
 * @author a47
 */
public class ExprobeNetworks extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        //LABELS
    Text uname_label=new Text("UserName"); 
    Text pass_label=new Text("Password");
   
    
    //TEXTFIELD
    TextField user_name_tf=new TextField();
        
    //PASSWORDFIELD
    PasswordField pass_field = new PasswordField();
    
    //BUTTON
    Button login_button = new Button("Login");
    Button register_button = new Button("Register");
    Button close_button = new Button("Close");
    
    
    //Event Handler for the login_button    
    login_button.setOnMouseClicked((new EventHandler<MouseEvent>() { 
    public void handle(MouseEvent event) {        
        String username = user_name_tf.getText();
        String password = pass_field.getText();
        
        try{
          Class.forName("com.mysql.cj.jdbc.Driver"); //step one


          Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx?autoReconnect=true&useSSL=false","root","");  //step two
          
          Statement st=con.createStatement();   //step three
          String statement = "SELECT * FROM exprobe_networks.users Where username = '"+username+"' AND password = '"+password+"' ";
          ResultSet rs = st.executeQuery(statement); //step four
          System.out.println(rs);
          Home usersdashboard = new Home();
          if(rs.next()){                   
//                    Alert al = new Alert(Alert.AlertType.CONFIRMATION);
//                    al.setContentText("Successful Login");
//                    al.show();
                    //You can add the code to open HomePage (after successful login)
                   
                   usersdashboard.start(usersdashboard.user_dashboard);
                  
                    
                }else{
                    Alert a = new Alert(Alert.AlertType.WARNING);
                    a.setContentText("Invalid User Name or Password");
                    a.show(); 
                }
          con.close();
            
        }
        catch(Exception ee){System.out.println(ee);System.out.println("Connection error");} 
     
        
        }
    })); 
    
    //creating an instance of an object of the Register.java
    Register registration_page = new Register();
    
    register_button.setOnMouseClicked((new EventHandler<MouseEvent>(){
        public void handle(MouseEvent Event){
            registration_page.start(registration_page.register_page);
        }
    }));
    
    //close button for close the window for stage
    close_button.setOnMouseClicked((new EventHandler<MouseEvent>(){
        public void handle(MouseEvent event){
            primaryStage.close();
        }
    }));
    
    
   
  //GRIDPANE
    GridPane gridPane = new GridPane();
    gridPane.add(uname_label,1,1);
    gridPane.add(pass_label,1,2);    
    gridPane.add(user_name_tf,2,1);
    gridPane.add(pass_field,2,2);
    gridPane.add(login_button,2,3);
    gridPane.add(register_button,2,4);
    gridPane.add(close_button,3,3);
    gridPane.setMinSize(600,300);        
    gridPane.setVgap(10);        
    gridPane.setHgap(20);        
    gridPane.setAlignment(Pos.CENTER); 
    
    //SCENE
    Scene my_scene = new Scene(gridPane);


    //STAGE
    primaryStage.setScene(my_scene);
    primaryStage.setTitle("Exprobe Networks Limited");
    primaryStage.show();    
        
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
