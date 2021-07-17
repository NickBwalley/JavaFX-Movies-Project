package exprobenetworks;

import exprobenetworks.Genres;
import exprobenetworks.Customers;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 *
 * @author a47
 */
public class Home extends Application{
    
    Stage user_dashboard = new Stage();
    
    @Override
    public void start(Stage primaryStage) {
       //LABLES
               
        Button Movies1 = new Button("Movies");
        Button Genres1 = new Button("Genres");
        Button Customers1 = new Button("Customers");
        Button Rentals1 = new Button("Rentals");
        
        Button close_button = new Button("Close"); 
        
        //GRIDPANE
        GridPane gridPane = new GridPane();
        
      
        
        gridPane.add(Movies1,0,1);
        gridPane.add(Genres1,0,2);
        gridPane.add(Customers1,0,3);
        gridPane.add(Rentals1,0,4);
        gridPane.add(close_button,1,4);
        
        gridPane.setMinSize(500,250);        
        gridPane.setVgap(10);        
        gridPane.setHgap(20);        
        gridPane.setAlignment(Pos.CENTER);
        
        //MOVIES
        //Event handler for new_window_button (without the login credentials)
        Movies1.setOnMouseClicked((new EventHandler<MouseEvent>() { 
         public void handle(MouseEvent event) {        
            Movies hp = new Movies();
            hp.start(hp.movie_stage);
         }
      }));
        
        //GENRES
        Genres1.setOnMouseClicked((new EventHandler<MouseEvent>() { 
         public void handle(MouseEvent event) {        
            Genres hp = new Genres();
            hp.start(hp.genres_stage);
         }
      }));
        
        //CUSTOMERS
        Customers1.setOnMouseClicked((new EventHandler<MouseEvent>() { 
         public void handle(MouseEvent event) {        
            Customers hp = new Customers();
            hp.start(hp.customers_stage);
         }
      }));
        
        //RENTALS
        Rentals1.setOnMouseClicked((new EventHandler<MouseEvent>() { 
         public void handle(MouseEvent event) {        
            Rentals hp = new Rentals();
            hp.start(hp.rentals_stage);
         }
      }));
        
        
        
        
       //Event Handler for the close button
        close_button.setOnMouseClicked((new EventHandler<MouseEvent>() { 
         public void handle(MouseEvent event) { 
          Stage stage = (Stage) close_button.getScene().getWindow();            
          stage.close();             
         } 
      }));
       
        
        //SCENE
        Scene my_scene = new Scene(gridPane);
        
        //STAGE
        primaryStage.setScene(my_scene);
        gridPane.setStyle("-fx-background-color: #2e8ea3; ");
        primaryStage.setTitle("WELCOME TO MR.BEAST SHOP!");
        primaryStage.show();
        
    }
    
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        launch(args);
//    }
}
