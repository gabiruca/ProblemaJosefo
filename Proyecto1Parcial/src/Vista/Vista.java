/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Controlador;
import Game.Personaje;
import java.util.Iterator;
import Model.Modelo;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author bllv1
 */
public class Vista {
    private Pane pane;
    private final Controlador controlador;
    private Modelo modelo;
    private ImageView cespada;
    private ChoiceBox<String> direccionChoiceBox;
    private ChoiceBox<Integer> numeroNodos;
    private Slider velocidadSlider;
    private ChoiceBox<Integer> saltosBox;
    
    public Vista(){
        pane=new Pane();
        controlador=new Controlador(this);
        modelo= new Modelo(this);   
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public ImageView getCespada() {
        return cespada;
    }

    public void setCespada(ImageView cespada) {
        this.cespada = cespada;
    }

    public ChoiceBox<String> getDireccionChoiceBox() {
        return direccionChoiceBox;
    }

    public void setDireccionChoiceBox(ChoiceBox<String> direccionChoiceBox) {
        this.direccionChoiceBox = direccionChoiceBox;
    }

    public ChoiceBox<Integer> getNumeroNodos() {
        return numeroNodos;
    }

    public void setNumeroNodos(ChoiceBox<Integer> numeroNodos) {
        this.numeroNodos = numeroNodos;
    }

    public Slider getVelocidadSlider() {
        return velocidadSlider;
    }

    public void setVelocidadSlider(Slider velocidadSlider) {
        this.velocidadSlider = velocidadSlider;
    }

    public ChoiceBox<Integer> getSaltosBox() {
        return saltosBox;
    }

    public void setSaltosBox(ChoiceBox<Integer> saltosBox) {
        this.saltosBox = saltosBox;
    }
    
    public Scene Escenainicio(){
        //contenedor principal
        HBox root= new HBox();
        root.setAlignment(Pos.CENTER_RIGHT);
        root.setPrefWidth(1500);
        root.setPrefHeight(1000);
        pane.setPrefWidth(600);
        pane.setPrefHeight(1000);
        
        //DIbuja los circulos en el pane
        inicializarCirculos();
          //Contenedor que contendra al pane y al resto de botones y ChoiceBoxes
        VBox vbox= new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);
        
        //ChoiceBox para elegir numero de Nodos
        HBox numeroNodosHbox= new HBox();
        Label numeroNodosLabel= new Label("Numero de Nodos:");
        numeroNodos= new ChoiceBox();
        ArrayList<Integer> list= new ArrayList();
        for(int i=10;i<=100;i++){
            list.add(i);
        }
        numeroNodos.getItems().addAll(list);
        numeroNodos.setValue(30);
        numeroNodos.getSelectionModel().selectedIndexProperty().addListener(controlador.numNodosListener());
        numeroNodosHbox.getChildren().addAll(numeroNodosLabel,numeroNodos);
        numeroNodosHbox.setAlignment(Pos.CENTER);
        
        //ChoiceBox para elegir la direccion de giro
        HBox direccionHBox = new HBox();
        Label direccionLabel= new Label("Direccion:");
        direccionChoiceBox= new ChoiceBox();
        direccionChoiceBox.getItems().addAll("Derecha","Izquierda");
        direccionChoiceBox.setValue("Derecha");
        direccionHBox.getChildren().addAll(direccionLabel,direccionChoiceBox);
        direccionHBox.setAlignment(Pos.CENTER);
        
        //Slider para controlar la velocidad de giro
        velocidadSlider= new Slider();
        velocidadSlider.setMin(0.5);
        velocidadSlider.setMax(1.5);
        velocidadSlider.setValue(1);
        velocidadSlider.setShowTickLabels(true);
        velocidadSlider.setShowTickMarks(true);
        velocidadSlider.setBlockIncrement(0.1);
        velocidadSlider.setMajorTickUnit(0.5);
        
        //ChoiceBox para controlar numero de saltos
        HBox saltosHBox = new HBox();
        Label saltosLabel= new Label("Saltos:");
        saltosBox= new ChoiceBox<>();
        ArrayList<Integer> listSaltos= new ArrayList<>();
        for (int i=0; i<20;i++) listSaltos.add(i);
        saltosBox.getItems().addAll(listSaltos);
        saltosBox.setValue(2);
        saltosHBox.getChildren().addAll(saltosLabel,saltosBox);
        saltosHBox.setAlignment(Pos.CENTER);
        //Boton de Play
        Button playButton= new Button("Play");
        playButton.setOnAction(controlador.botonPlayAction());
        
        //Boton de Pausa
        Button pauseButton= new Button("Pause");
        pauseButton.setOnAction(controlador.botonPauseAction());
        
        //Boton de Stop
        Button stopButton= new Button("Stop");
        stopButton.setOnAction(controlador.botonStopAction());
        
        vbox.getChildren().addAll(numeroNodosHbox,direccionHBox,velocidadSlider,saltosHBox,playButton,pauseButton,stopButton);
        root.getChildren().addAll(pane,vbox);
        return new Scene(root,800,600);
    }
    
    public void inicializarCirculos(){
       Iterator<Personaje> ite=modelo.getPersonaje().iterator();
       double tamano= (double) modelo.getPersonaje().size();
       double radioCircunferencia= 7*tamano;
      
       double separacionAngulos=360/tamano;
       int cont=0;
       while(ite.hasNext()){
           Personaje persona= ite.next();
           Circle cirPersona=persona.getCircle();
           //Ubicar cada circulo completando un circulo grande
           double posX= 300+radioCircunferencia*Math.cos(Math.toRadians(separacionAngulos*cont));
           double posY= 300+radioCircunferencia*Math.sin(Math.toRadians(separacionAngulos*cont));
           persona.setPosX(posX);
           persona.setPosY(posY);
           cirPersona.setRadius(20);
           cirPersona.setLayoutX(posX);
           cirPersona.setLayoutY(posY);
         //  if(cont ==0) highlight(cirPersona);
         //  else lowlight(cirPersona);
           //Accion de volver primero a un nodo cuando se presiona un circulo
           cirPersona.setOnMouseClicked(controlador.circuloPresionado(persona));
           Label label= new Label(Integer.toString(persona.getIndex()));
           label.setLayoutX(posX);
           label.setLayoutY(posY);
           label.setAlignment(Pos.CENTER_LEFT);
           pane.getChildren().addAll(cirPersona,label);
           cont++;
        }
       cespada= new ImageView("image/files/sword.png");
       //moverEspada(modelo.getPersonaje().getFirst());
       cespada.setFitHeight(50);
       cespada.setFitWidth(50);
       pane.getChildren().add(cespada);
    }/*
    //Resalta el circulo   
    public void highlight(Circle circle){
       circle.setStrokeWidth(5);
       circle.setStroke(Color.YELLOW);
   }
   
   //Desubraya el circulo
   public void lowlight(Circle circle){
       circle.setStrokeWidth(1);
       circle.setStroke(Color.BLACK);
   }
   public void moverEspada(Personaje personaje){
       cespada.setLayoutX(personaje.getPosX());
       cespada.setLayoutY(personaje.getPosY());
   }
   public void moverEspada(double x,double y){
       cespada.setLayoutX(x);
       cespada.setLayoutY(y);
   }
   
   public void newCespada(){
       cespada = new ImageView("imagenes/PConEspada.png");
       pane.getChildren().add(cespada);
   }
   
   public void borrarCirculos(){
       pane.getChildren().clear();
   }*/

    
}
