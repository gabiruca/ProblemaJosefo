/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import LinkedList.CircleDoubleLinkedList;
import java.util.ArrayList;
import java.util.Iterator;
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

/**
 *
 * @author Gabriela
 */
public class View {
    private Pane pane;
    private final Controller controller;
    private Model model;
    private ImageView sword;
    private ChoiceBox<String> direccionChoiceBox;
    private ChoiceBox<Integer> numeroNodos;
    private Slider velocidadSlider;
    private ChoiceBox<Integer> saltosBox;
    private ImageView imPersona;
    
     public View(){
        pane=new Pane();
        controller=new Controller(this);
        model= new Model(this);
 
    }
    public Scene iniciarEscena(){
        HBox root= new HBox();
        root.setAlignment(Pos.CENTER_RIGHT);
        root.setPrefWidth(1500);
        root.setPrefHeight(1000);
        pane.setPrefWidth(600);
        pane.setPrefHeight(1000);
        VBox vbox= new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);
        
        inicializarCirculos();
        
        //Elegir numero de Jugadores
        HBox numeroNodosHbox= new HBox();
        Label numeroNodosLabel= new Label("Numero de jugadores:");
        numeroNodos= new ChoiceBox();
        ArrayList<Integer> list= new ArrayList();
        for(int i=5;i<=20;i++){
            list.add(i);
        }
        numeroNodos.getItems().addAll(list);
        numeroNodos.setValue(12);
        numeroNodos.getSelectionModel().selectedIndexProperty().addListener(controller.numNodosListener());
        numeroNodosHbox.getChildren().addAll(numeroNodosLabel,numeroNodos);
        numeroNodosHbox.setAlignment(Pos.CENTER);
        
        //Elegir la direccion de giro
        HBox direccionHBox = new HBox();
        Label direccionLabel= new Label("Direccion:");
        direccionChoiceBox= new ChoiceBox();
        direccionChoiceBox.getItems().addAll("Derecha","Izquierda");
        direccionChoiceBox.setValue("Derecha");
        direccionHBox.getChildren().addAll(direccionLabel,direccionChoiceBox);
        direccionHBox.setAlignment(Pos.CENTER);
        
        //Control de la velocidad
        velocidadSlider= new Slider();
        velocidadSlider.setMin(0.5);
        velocidadSlider.setMax(1.5);
        velocidadSlider.setValue(1);
        velocidadSlider.setShowTickLabels(true);
        velocidadSlider.setShowTickMarks(true);
        velocidadSlider.setBlockIncrement(0.5);
        velocidadSlider.setMajorTickUnit(1.5);
        
        //Controlar numero de saltos
        HBox saltosHBox = new HBox();
        Label saltosLabel= new Label("Saltos:");
        saltosBox= new ChoiceBox<>();
        ArrayList<Integer> listSaltos= new ArrayList<>();
        for (int i=0; i<5;i++) listSaltos.add(i);
        saltosBox.getItems().addAll(listSaltos);
        saltosBox.setValue(1);
        saltosHBox.getChildren().addAll(saltosLabel,saltosBox);
        saltosHBox.setAlignment(Pos.CENTER);
        
        //Botones
        //Inicio
        Button playButton= new Button("Iniciar");
        playButton.setOnAction(controller.botonPlayAction());
        //Pausa
        Button pauseButton= new Button("Pausar");
        pauseButton.setOnAction(controller.botonPauseAction());
        //Finalizar
        Button stopButton= new Button("Finalizar");
        stopButton.setOnAction(controller.botonStopAction());
        
        vbox.getChildren().addAll(numeroNodosHbox,direccionHBox,velocidadSlider,saltosHBox,playButton,pauseButton,stopButton);
        root.getChildren().addAll(pane,vbox);
        return new Scene(root,900,800);
    }

    public void inicializarCirculos(){
       Iterator<Persona> ite=model.getPersonas().iterator();
       int tamano= model.getPersonas().size();
       double radioCircunferencia= 11*tamano;
       double separacionAngulos=360/tamano;
       int cont=0;
       
       while(ite.hasNext()){
           Persona persona= ite.next();
           imPersona=persona.getImage();
           double posX=-140+radioCircunferencia*Math.cos(Math.toRadians(separacionAngulos*cont));
           double posY=180+radioCircunferencia*Math.sin(Math.toRadians(separacionAngulos*cont));
           persona.setPosX(posX);
           persona.setPosY(posY);
           imPersona.setLayoutX(posX);
           imPersona.setLayoutY(posY);
           pane.getChildren().add(imPersona);
           cont++;
       }
       
       sword= new ImageView("Imagenes/sword.png");
       moverEspada(model.getPersonas().getFirst());
       sword.setFitHeight(60);
       sword.setFitWidth(60);
       pane.getChildren().add(sword);
       //cambiarImagen();
       //mostrarPM(model.getPersonas().getFirst());
    }
        
    public void borrarCirculos(){
       pane.getChildren().clear();
    }

    public void setSword(ImageView sword) {
        this.sword = sword;
    }
   
   //Movimiento de espada
    public void moverEspada(Persona persona){
       sword.setLayoutX(persona.getPosX()+350);
       sword.setLayoutY(persona.getPosY()+200);
    }

    public void cambiarImagen(){
        CircleDoubleLinkedList<Persona> llPersonas= model.getPersonas();
        for (Persona p: llPersonas){
           if(p.getIsAlive()==false){
               p.setImage(new ImageView("Imagenes/MSinEspada.png"));
           }
       }
    }
    
    public void mostrarPM(Persona p){
       p.getImage().setLayoutX(p.getPosX());
       p.getImage().setLayoutY(p.getPosY());
    }
    
    public void moverEspada(double x,double y){
       sword.setLayoutX(x-140);
       sword.setLayoutY(y);
    }
   
   public void newSword(){
       sword = new ImageView("Imagenes/sword.png");
       pane.getChildren().add(sword);
   }
   
   
    //Setters y getters 
   
    public Slider getVelocidadSlider() {
        return velocidadSlider;
    }

    public ChoiceBox<Integer> getSaltosBox() {
        return saltosBox;
    }

    public ChoiceBox<Integer> getNumeroNodos() {
        return numeroNodos;
    }

    public ChoiceBox<String> getDireccionChoiceBox() {
        return direccionChoiceBox;
    }

    public ImageView getSword() {
        return sword;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
