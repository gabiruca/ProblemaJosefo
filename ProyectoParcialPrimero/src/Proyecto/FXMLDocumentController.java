package Proyecto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Game.Personaje;
import List.CircleDoubleLinkedList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
/**
 *
 * @author bllv1
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private JFXSlider cantidadPersonajes;
    
    @FXML
    private JFXSlider posicionInicio;
    
    @FXML
    private JFXSlider nDesfase;
    
    @FXML
    private JFXSlider diferenciaVelocidad;
    
    @FXML
    private JFXButton start;
    
    @FXML
    private JFXButton left;
    
    @FXML
    private JFXButton right;
    
    @FXML
    private Label cantidadPersonasL;
    
    @FXML
    private Label nDesfaceL;
    
    @FXML
    private Label posicionInicioL;
    
    @FXML    
    private Label diferenciaVelocidadL;
    
    @FXML
    private StackPane panel;
    
    private Image mSinEspada;
    private Image pConEspada;
    private Image pSinEspada;
    
    private String direccion;
    
    private ListIterator<Personaje> iterador;
    private CircleDoubleLinkedList<Personaje> linkedList;
    private int posIniMuerte;//posicion donde inicia el asesinato
    private int desfase;//el salto por matanza
    private int numeroPersonas;//cantidad de soldados
    private int personasVivas;
    private static Thread hilo;//Para manejar los hilos de ejecucion
    private boolean suspender;
    private static Runnable controlThread;
    private int velocity;

    
    
    //Metodos
    @FXML
    public void pauseEjecution(){
        suspender = true;
    }
    
    @FXML
    synchronized void playEjecution(){
        suspender = false;
    }
    
    @FXML
    private void iniciarSimulation(ActionEvent event) {
        cantidadPersonajes.setDisable(true);
        posicionInicio.setDisable(true);
        nDesfase.setDisable(true);
        left.setDisable(true);
        right.setDisable(true);
        iterador = linkedList.listIteratorNode(posIniMuerte);
        hilo.start();
        start.setDisable(true);
    }
    
    @FXML
    private void reiniciarSimulacion() {
        try {
            suspender = false;
            diferenciaVelocidad.setValue(500);
            velocity = ((int) Math.round((double) diferenciaVelocidad.getValue()));
            start.setDisable(false);
            hilo = new Thread(controlThread);
            direccion = "Izquierda";
            cantidadPersonajes.setDisable(false);
            posicionInicio.setDisable(false);
            nDesfase.setDisable(false);
            left.setDisable(false);
            right.setDisable(false);
            posicionInicio.setValue(1);
            posIniMuerte = ((int) Math.round((double) posicionInicio.getValue())) - 1;
            panel.getChildren().clear();
            linkedList = new CircleDoubleLinkedList<Personaje>();
            cantidadPersonajes.setValue(12);
            numeroPersonas = ((int) Math.round((double) cantidadPersonajes.getValue()));
            desfase = 2;
            cantidadPersonasL.setText("Numero de personas: " + numeroPersonas);
            nDesfaceL.setText("Desfase: " + desfase);
            posicionInicioL.setText("Posición persona que comienza: " + (posIniMuerte + 1));
            diferenciaVelocidadL.setText("Velocidad (ms): " + velocity);
            llenarJuego(linkedList, numeroPersonas);
            System.out.println("  Reiniciado!  ");
        } catch (Exception e) {
            System.out.println("E");
        }
    }
        
        private void llenarJuego(CircleDoubleLinkedList<Personaje> personaje, int valor) {
        for (int indice = 0; indice < valor; indice++) {
            ImageView imgActual = new ImageView(pConEspada);
            imgActual.setFitWidth(200);
            imgActual.setFitHeight(90);
            imgActual.setPreserveRatio(false);
            imgActual.setTranslateX(250 * Math.cos((((360 / (double) valor) * Math.PI) / 180) * indice));
            imgActual.setTranslateY(250 * Math.sin((((360 / (double) valor) * Math.PI) / 180) * indice));
            if (indice == posIniMuerte) {
                imgActual.setImage(pSinEspada);
            }
            imgActual.setRotate((360 / (double) valor) * indice);
            Personaje soldado = new Personaje(imgActual, true);
            personaje.addLast(soldado);
        }

        ListIterator<Personaje> iter = personaje.listIteratorNode(valor);
        while (iter.hasNext()) {
            panel.getChildren().add(iter.next().getImagen());
        }
    }
         private void iniciarEnDireccionIndicada(ListIterator<Personaje> iter, String direccion) {
        personasVivas = numeroPersonas - 1;
        System.out.println(" INICIADO  ");
        if (direccion.equals("left")) {
            try {
                while (iter.hasNext()) {
                    Personaje personajeParticipante = iter.next();
                    Personaje muerto = null;
                    if (personajeParticipante.getIsAlive()) {
                        int desfaseTemp = desfase - 1;
                        while (desfaseTemp > 0) {
                            muerto = iter.next();
                            desfaseTemp--;
                        }
                        linkedList.remove(muerto);
                        muerto.setImagen(mSinEspada);
                        personasVivas -= 1;
                    }
                    Thread.sleep(velocity);
                    synchronized (this) {
                        while (suspender) {
                            System.out.println("Waiting...");
                            wait(650);
                        }
                    }
                    if (personasVivas == 0) {
                        break;
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (direccion.equals("Derecha")) {
            try {
                while (iter.hasPrevious()) {
                    Personaje soldadoParticipante = iter.previous();
                    Personaje muerto = null;
                    if (soldadoParticipante.getIsAlive()) {
                        int desfaseTemp = desfase - 1;
                        while (desfaseTemp > 0) {
                            muerto = iter.previous();
                            desfaseTemp--;
                        }
                        linkedList.remove(muerto);
                        muerto.setImagen(mSinEspada);
                        personasVivas -= 1;
                    }
                    Thread.sleep(velocity);
                    synchronized (this) {
                        while (suspender) {
                            System.out.println("Waiting...");
                            wait(500);
                        }
                    }
                    if (personasVivas == 0) {
                        break;
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
         @FXML
        private void asignarDireccion(javafx.event.ActionEvent event) {
        start.setDisable(false);
        left.setDisable(false);
        right.setDisable(false);
        Button btn = (Button) event.getSource();
        if (btn.getText().equals("Left")) {
            direccion = "Right";
            btn.setDisable(true);
        } else if (btn.getText().equals("Left")) {
            direccion = "Right";
            btn.setDisable(true);
        }

    }
        @Override
        public void initialize(URL url, ResourceBundle rb) {

        suspender = false;
        velocity = ((int) Math.round((double) diferenciaVelocidad.getValue()));
        direccion = "Izquierda";
        pConEspada = new Image("\\Imagenes\\pConEspada.png");
        pSinEspada = new Image("\\Imagenes\\pSinEspada.png");
        mSinEspada = new Image("\\Imagenes\\mSinEspada.png");

        posIniMuerte = ((int) Math.round((double) posicionInicio.getValue())) - 1;
        desfase = 2;
        linkedList = new CircleDoubleLinkedList<Personaje>();
        numeroPersonas = 12;
        llenarJuego(linkedList, numeroPersonas);

        cantidadPersonasL.setText("Cantidad de personas: " + numeroPersonas);
        nDesfaceL.setText("Desfase: " + desfase);
        posicionInicioL.setText("Posición de la persona que comienza: " + (posIniMuerte + 1));
        diferenciaVelocidadL.setText("Velocidad (ms): " + velocity);
        cambioNumeroPersonas();
        cambioDesfase();
        cambioComienzoPosicionPersona();
        changeSpeed();

        controlThread = new Runnable() {
            @Override
            public void run() {
               iniciarEnDireccionIndicada(iterador, direccion);
            }
        };
        hilo = new Thread(controlThread);
    }
        public void cambioDesfase() {
        nDesfase.valueProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                nDesfaceL.setText("Desfase: " + ((int) Math.round((double) newValue)));
                desfase = ((int) Math.round((double) newValue));
            }
        });
    }
        public void cambioComienzoPosicionPersona() {
        posicionInicio.valueProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                linkedList.get(posIniMuerte).setImagen(pSinEspada);

                posIniMuerte = ((int) Math.round((double) newValue)) - 1;

                if (posIniMuerte < linkedList.size()) {
                    posicionInicioL.setText("Posición persona que comienza: " + (posIniMuerte + 1));
                    linkedList.get(posIniMuerte).setImagen(pConEspada);

                } else {
                    posIniMuerte = 0;
                    posicionInicio.setValue(0);
                }

            }
        });
    }
        public void cambioNumeroPersonas() {
            cantidadPersonajes.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                posicionInicio.setValue(1);
                posIniMuerte = ((int) Math.round((double) posicionInicio.getValue())) - 1;
                panel.getChildren().clear();
                linkedList = new CircleDoubleLinkedList<Personaje>();
                numeroPersonas = (int) Math.round((double) newValue);
                cantidadPersonasL.setText("Cantidad de personas: " + numeroPersonas);
                llenarJuego(linkedList, numeroPersonas);
            }
        });
    }
        public void changeSpeed() {
        diferenciaVelocidad.valueProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                velocity = ((int) Math.round((double) newValue));
                diferenciaVelocidadL.setText("Velocidad (ms): " + velocity);
            }
        });
     }
    
}
