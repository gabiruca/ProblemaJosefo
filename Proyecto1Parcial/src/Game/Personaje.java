/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Gabriela
 */
public class Personaje {
    private ImageView image;
    private boolean isAlive;

    public Personaje(ImageView image, boolean isAlive) {
        this.image = image;
        this.isAlive= isAlive;
    }
    
    public ImageView getImagen() {
        return image;
    }

    public void setImagen(Image image) {
        this.image.setImage(image);
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
}
