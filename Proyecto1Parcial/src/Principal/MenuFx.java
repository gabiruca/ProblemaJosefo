/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Game.Personaje;
import List.CircleDoubleLinkedList;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author User
 */
public class MenuFx {
    private Button start;
    private Button left;
    private Button right;
    private StackPane panel;
    private Slider cantidadPersonajes;
    private Slider posicionInicio;
    private Slider nDesfase;
    private Slider diferenciaVelocidad;
    private Label cantidadPersonasL;
    private Label nDesfaceL;
    private Label posicionInicioL;
    private Label diferenciaVelocidadL;
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

    public Button getStart() {
        return start;
    }

    public Button getLeft() {
        return left;
    }

    public Button getRight() {
        return right;
    }

    public StackPane getPanel() {
        return panel;
    }

    public Slider getCantidadPersonajes() {
        return cantidadPersonajes;
    }

    public Slider getPosicionInicio() {
        return posicionInicio;
    }

    public Slider getnDesfase() {
        return nDesfase;
    }

    public Slider getDiferenciaVelocidad() {
        return diferenciaVelocidad;
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

   //Metodos
    public void pauseEjecution(){
        suspender = true;
    }
    public void playEjecution(){
        suspender = false;
    }
    private void iniciarSimulacion(ActionEvent event) {
        cantidadPersonajes.setDisable(true);
        posicionInicio.setDisable(true);
        nDesfase.setDisable(true);
        left.setDisable(true);
        right.setDisable(true);
        iterador = linkedList.listIteratorNode(posIniMuerte);
        hilo.start();
        start.setDisable(true);
    }
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
        
        public void llenarJuego(CircleDoubleLinkedList<Personaje> personaje, int valor) {
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
                Logger.getLogger(MenuFx.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(MenuFx.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
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
        public void inicializar(URL url, ResourceBundle rb) {

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
    

