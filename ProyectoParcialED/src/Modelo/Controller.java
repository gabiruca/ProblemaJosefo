/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
/**
 *
 * @author User
 */
public class Controller {
    private final View vista;

    public Controller(View vista) {
        this.vista = vista;
    }

    public  ChangeListener<Number> numNodosListener() {
    return (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
        vista.getModel().getPersonas().clear();
        vista.borrarCirculos();
        vista.getModel().setPersonas(vista.getModel().inicializarPersonas((int) newValue+5, 0));
        vista.inicializarCirculos();
    };
    }
    
    public EventHandler<ActionEvent> botonPlayAction(){
        
        return (ActionEvent event) -> {
            try {
                if(vista.getModel().getPause() ==true) vista.getModel().setPause(false);
                else vista.getModel().suicidios(vista.getSaltosBox().getValue(),vista.getDireccionChoiceBox().getValue(),(long) vista.getVelocidadSlider().getValue());
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                Thread.currentThread().interrupt();
            }
        };
    }

    public EventHandler<ActionEvent> botonStopAction(){
        return (ActionEvent event) -> {
            vista.getModel().getPersonas().clear();
            vista.borrarCirculos();
            vista.getModel().setPersonas(vista.getModel().inicializarPersonas(vista.getNumeroNodos().getValue(), 0));
            Platform.runLater(()->{vista.getPane().getChildren().remove(vista.getSword());});         
            vista.inicializarCirculos();
            vista.newSword();
            
        };
    }
    
    public EventHandler<ActionEvent> botonPauseAction(){
        return (ActionEvent event) -> {
            vista.getModel().setPause(true);
        };
    }
}
