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
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
/**
 *
 * @author bllv1
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
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
    
    private Image mSinEspada;
    private Image pConEspada;
    private Image pSinEspada;
    private StackPane panel;
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

    public Label getLabel() {
        return label;
    }

    public JFXSlider getCantidadPersonajes() {
        return cantidadPersonajes;
    }

    public JFXSlider getPosicionInicio() {
        return posicionInicio;
    }

    public JFXSlider getnDesfase() {
        return nDesfase;
    }

    public JFXSlider getDiferenciaVelocidad() {
        return diferenciaVelocidad;
    }

    public JFXButton getStart() {
        return start;
    }

    public JFXButton getLeft() {
        return left;
    }

    public JFXButton getRight() {
        return right;
    }

    public Label getCantidadPersonasL() {
        return cantidadPersonasL;
    }

    public Label getnDesfaceL() {
        return nDesfaceL;
    }

    public Label getPosicionInicioL() {
        return posicionInicioL;
    }

    public Label getDiferenciaVelocidadL() {
        return diferenciaVelocidadL;
    }

    public Image getmSinEspada() {
        return mSinEspada;
    }

    public Image getpConEspada() {
        return pConEspada;
    }

    public Image getpSinEspada() {
        return pSinEspada;
    }

    public StackPane getPanel() {
        return panel;
    }

    public String getDireccion() {
        return direccion;
    }

    public ListIterator<Personaje> getIterador() {
        return iterador;
    }

    public CircleDoubleLinkedList<Personaje> getLinkedList() {
        return linkedList;
    }

    public int getPosIniMuerte() {
        return posIniMuerte;
    }

    public int getDesfase() {
        return desfase;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public int getPersonasVivas() {
        return personasVivas;
    }

    public static Thread getHilo() {
        return hilo;
    }

    public boolean isSuspender() {
        return suspender;
    }

    public static Runnable getControlThread() {
        return controlThread;
    }

    public int getVelocity() {
        return velocity;
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
