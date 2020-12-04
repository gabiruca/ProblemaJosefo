/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Vista.Vista;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Gabriela
 */
public class Personaje implements Comparable<Personaje>{
    private int index;
    private Circle circle;    
    private ImageView image;
    private boolean isAlive;
    private double posX;
    private double posY;

    public Personaje(int index) {
        this.index = index;
        this.circle=new Circle();
        circle.setFill(Color.ALICEBLUE);
        isAlive=true;
    }

    
    
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }
    
    public void morir(){
        this.isAlive=false;
        this.circle.setFill(javafx.scene.paint.Color.GRAY);
    }

    @Override
    public int compareTo(Personaje o) {
        if(this.getIndex()==o.getIndex()){
            return 0;
        }else{
            return -1;
        }
    }
    
    public void matar(Personaje personaje, Vista vista, long velocity ){
        Platform.runLater(()->{
         //   vista.moverEspada(personaje);
            personaje.morir(); 
        });
        try {
            Thread.sleep(1000/velocity);
        } catch (InterruptedException ex) {
            Logger.getLogger(Personaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
  /*  public void pasarEspada (Personaje nuevoAsesino, Vista vista, long velocidad){
        Platform.runLater(()->{
            vista.lowlight(this.getCircle());
            vista.moverEspada(nuevoAsesino);
            vista.highlight(nuevoAsesino.getCircle());
        });
        try {
            Thread.sleep(1000/velocidad);
        } catch (InterruptedException ex) {
            Logger.getLogger(Personaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
