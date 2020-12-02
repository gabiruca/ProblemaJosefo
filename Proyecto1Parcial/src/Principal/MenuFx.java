/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Game.Personaje;
import List.CircleDoubleLinkedList;
import java.awt.Desktop.Action;
import java.util.ListIterator;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;

/**
 *
 * @author User
 */
public class MenuFx {
    private Button start;
    private StackPane panel;
    private Slider cantidadPersonajes;
    private Slider posicionInicio;
    private Slider desfase;
    private Button left;
    private Button right;
    private int posicionInicial;
    private ListIterator<Personaje> iterador;
    private CircleDoubleLinkedList<Personaje> listaEnlazada;

    public Button getStart() {
        return start;
    }

    public void setStart(Button start) {
        this.start = start;
    }

    public StackPane getPanel() {
        return panel;
    }

    public void setPanel(StackPane panel) {
        this.panel = panel;
    }

    public Slider getNumeroPersonas() {
        return cantidadPersonajes;
    }

    public void setNumeroPersonas(Slider numeroPersonas) {
        this.cantidadPersonajes = cantidadPersonajes;
    }

    public Slider getPosicionInicio() {
        return posicionInicio;
    }

    public void setPosicionInicio(Slider posicionInicio) {
        this.posicionInicio = posicionInicio;
    }

    public Slider getDesfase() {
        return desfase;
    }

    public void setDesfase(Slider desfase) {
        this.desfase = desfase;
    }

    public Button getLeft() {
        return left;
    }

    public void setLeft(Button left) {
        this.left = left;
    }

    public Button getRight() {
        return right;
    }

    public void setRight(Button right) {
        this.right = right;
    }

    public int getPosicionInicial() {
        return posicionInicial;
    }

    public void setPosicionInicial(int posicionInicial) {
        this.posicionInicial = posicionInicial;
    }

    public ListIterator<Personaje> getIterador() {
        return iterador;
    }

    public void setIterador(ListIterator<Personaje> iterador) {
        this.iterador = iterador;
    }

    public CircleDoubleLinkedList<Personaje> getListaEnlazada() {
        return listaEnlazada;
    }

    public void setListaEnlazada(CircleDoubleLinkedList<Personaje> listaEnlazada) {
        this.listaEnlazada = listaEnlazada;
    }
    
   
    
}
