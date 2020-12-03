/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 *
 * @author Gabriela
 */
public class Main extends Application{
/*private final Image fondo = new Image("file:src/Imagenes/cave2.jpg");
    private final StackPane container = new StackPane();
    Scene scene = new Scene(container,900,700);*/
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        /*
        ImageView imageView = new ImageView();
        imageView.setImage(fondo);
        imageView.setFitHeight(700);
        imageView.setFitWidth(900);
        container.getChildren().add(imageView);
        */
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        primaryStage.getIcons().add(new Image("\\Imagenes\\guerreroicono.png"));
        primaryStage.setTitle("El Problema de Flavio Josefo");
        primaryStage.setScene(scene);
        primaryStage.show();
        

    }
    public static void main(String[] args){
        launch(args);
    }   
}
