/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
/**
 *
 * @author User
 */
public class Controller {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final View vista;
    private final Model model;

    public Controller(View vista, Model model) {
        this.vista = vista;
        this.model=model;
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
                if(vista.getModel().getPause() ==true) {
                    vista.getModel().setPause(false);
                    
                }else {
                    
                    vista.getModel().suicidios(vista.getSaltosBox().getValue(),vista.getDireccionChoiceBox().getValue(),(long) vista.getVelocidadSlider().getValue());
                }
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                Thread.currentThread().interrupt();
            }
            
        };
        
    }

    public EventHandler<ActionEvent> botonStopAction(){
        return (ActionEvent event) -> {
            System.exit(3);
        };
    }
    
    public EventHandler<ActionEvent> botonPauseAction(){
        return (ActionEvent event) -> {
            vista.getModel().setPause(true);
        };
    }
}
