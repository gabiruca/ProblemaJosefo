/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Gabriela
 */
public class Main extends Application {
    private final Image fondo = new Image("file:src/Imagenes/cave2.jpg");
    private final StackPane container = new StackPane();    
    Scene scene = new Scene(container,900,700);
    public  StackPane buttonBox=new StackPane();   
    public Button GStart=new Button("Juego Nuevo");
    public Button Salir=new Button("Salir");
    private final StackPane containergame = new StackPane();
    Scene scene2 = new Scene(containergame,900,700);
    
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("El Problema de Flavio Josefo");
        primaryStage.getIcons().add(new Image("file:src/Imagenes/guerreroicono.jpg"));
        ImageView imageView = new ImageView();
        imageView.setImage(fondo);
        imageView.setFitHeight(700);
        imageView.setFitWidth(900); 
        //Añadiendo los botones a la pantalla principal
        StackPane.setAlignment(GStart, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(Salir, Pos.BOTTOM_RIGHT);
        buttonBox.getChildren().addAll(GStart, Salir);
        GStart.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Stage secondStage = new Stage();
                secondStage.setTitle("El Problema de Flavio Josefo");
                secondStage.getIcons().add(new Image("file:src/Imagenes/guerreroicono.jpg"));
                MenuFx menu= new MenuFx();
                //containergame.getChildren().addAll(menu);
                secondStage.setScene(scene2);
                secondStage.show();
            }
            
        });
        
        Salir.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
            
        });
        
        container.setPadding(new Insets(350));        
        container.getChildren().addAll(imageView, buttonBox);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }
    
    
    
    public static void main(String[] args){
        launch(args);
    }   

    
    
    
}
