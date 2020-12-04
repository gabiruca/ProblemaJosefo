/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Game.Personaje;
import List.CircleDoubleLinkedList;
import Vista.Vista;

/**
 *
 * @author bllv1
 */
public class Modelo {
    private CircleDoubleLinkedList<Personaje> personaje ;
    private final Vista vista;
    SuicidiosGUI animacionSuicidios;
    private boolean pause;
    
    public Modelo(Vista vista){
        this.personaje= new CircleDoubleLinkedList();
        personaje= inicializarPersonaje(30, 0);
        this.vista= vista;
        pause=false;
    }

    public CircleDoubleLinkedList<Personaje> getPersonaje() {
        return personaje;
    }

    public void setPersonaje(CircleDoubleLinkedList<Personaje> personaje) {
        this.personaje = personaje;
    }

    public boolean getPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
    
    
    public SuicidiosGUI getAnimacionSuicidios() {
        return animacionSuicidios;
    }
    public CircleDoubleLinkedList<Personaje> inicializarPersonaje(int cantidad, int indexPrimero){

        for(int i=0; i<cantidad;i++){
            personaje.addLast(new Personaje(i));
        }
        
        personaje.setLast(cantidad-indexPrimero);
        return personaje;
    }
    
    // Inicia los suicidios
    public void suicidios(int saltos, String direccion, long velocidad) throws InterruptedException{
        animacionSuicidios= new SuicidiosGUI(direccion,velocidad,saltos);
        animacionSuicidios.start();
   }

    private static class SuicidiosGUI extends Thread{
        private Personaje asesino;
        private Personaje persona;
        private Personaje nuevoAsesino;
        private String direccion;
        private long velocidad;
        private int saltos;
        
        public SuicidiosGUI(String direccion, long velocidad,int saltos) {
            this.direccion = direccion;
            this.asesino= null;
            this.persona= null;
            this.nuevoAsesino=null;
            this.velocidad=velocidad;
            this.saltos= saltos;
        }
        
       /* @Override
        public void run(){
            int nodosRest=personaje.size();
            
        }*/
                
               
        
    }

}
