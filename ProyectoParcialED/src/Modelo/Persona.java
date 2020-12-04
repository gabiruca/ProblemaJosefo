
package Modelo;

import View.View;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

/**
 *
 * @author Gabriela
 */
public class Persona implements Comparable<Persona>{
    private int indice;
    private boolean isAlive;
    private double posX;
    private double posY;
    private ImageView imagenP;
    
        
    public void morir(){
        this.isAlive=false;
    }
    
    public Persona(int indice) {
        this.indice = indice;
        isAlive= true;
        this.imagenP= new ImageView("image/files/PSinEspada.png");  
    }
    
    public void matar( Persona persona, View view, long velocidad){
        Platform.runLater(()->{
            view.moverEspada(persona);
            persona.morir();  
        });
        try {
            Thread.sleep(1000/velocidad);
        } catch (InterruptedException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void pasarEspada (Persona nuevoAsesino, View view, long velocidad){
        Platform.runLater(()->{
            view.moverEspada(nuevoAsesino);
        });
        try {
            Thread.sleep(1000/velocidad);
        } catch (InterruptedException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
  //Setters y getters
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

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public ImageView getImage() {
        return imagenP;
    }

    public void setImage(ImageView circle) {
        this.imagenP = circle;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    

    @Override
    public int compareTo(Persona o) {
        if(this.getIndice()== o.getIndice()) return 0;
        else return -1;
    }
  
}
