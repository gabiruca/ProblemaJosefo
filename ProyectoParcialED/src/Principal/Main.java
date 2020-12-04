/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;
import Modelo.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/**
 *
 * @author User
 */
public class Main extends Application{
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        View view=new View();
        Scene scene=view.iniciarEscena();
        primaryStage.setScene(scene);
        scene.getStylesheets().add("estilo/estilo.css");
        primaryStage.getIcons().add(new Image("/Imagenes/sword.png"));
        primaryStage.setTitle("El problema de Josefo");
        primaryStage.show();
    }
    
}
