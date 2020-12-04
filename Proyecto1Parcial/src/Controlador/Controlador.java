/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Game.Personaje;
import Vista.Vista;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author bllv1
 */
public class Controlador {
    private final Vista vista;
    
    public Controlador(Vista vista) {
        this.vista = vista;
    }
    public  ChangeListener<Number> numNodosListener() {
    return (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
        vista.getModelo().getPersonaje().clear();
   //     vista.borrarCirculos();
        vista.getModelo().setPersonaje(vista.getModelo().inicializarPersonaje((int) newValue+10, 0));
        vista.inicializarCirculos();
    };
    }
    public EventHandler<MouseEvent> circuloPresionado(Personaje personaje){
        return (MouseEvent event) -> {
     //       vista.lowlight(vista.getModelo().getPersonaje().getFirst().getCircle());
       //     vista.highlight(personaje.getCircle());
            vista.getModelo().getPersonaje().setLast(personaje.getIndex()-1);
         //   vista.moverEspada(personaje);
        };
    }
    
    public EventHandler<ActionEvent> botonStopAction(){
        return (ActionEvent event) -> {
            vista.getModelo().getPersonaje().clear();
           // vista.borrarCirculos();
            vista.getModelo().setPersonaje(vista.getModelo().inicializarPersonaje(vista.getNumeroNodos().getValue(), 0));
            Platform.runLater(()->{vista.getPane().getChildren().remove(vista.getCespada());});         
            vista.inicializarCirculos();
            //vista.newCespada();
            //view.moverEspada(view.getModel().getPersonas().getFirst());
        };
    }
    
    public EventHandler<ActionEvent> botonPauseAction(){
        return (ActionEvent event) -> {
            vista.getModelo().setPause(true);
        };
    }
    
    //Eventos al presionar Play
    public EventHandler<ActionEvent> botonPlayAction(){
        return (ActionEvent event) -> {
            try {
                if(vista.getModelo().getPause() ==true) vista.getModelo().setPause(false);
                else vista.getModelo().suicidios(vista.getSaltosBox().getValue(),vista.getDireccionChoiceBox().getValue(),(long) vista.getVelocidadSlider().getValue());
            } catch (InterruptedException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                Thread.currentThread().interrupt();
            }
        };
    }
}
