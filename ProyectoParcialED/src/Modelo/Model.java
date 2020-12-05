
package Modelo;

import java.util.ListIterator;
import LinkedList.CircleDoubleLinkedList;
import javafx.application.Platform;
/**
 *
 * @author Gabriela
 */
public class Model {
    private CircleDoubleLinkedList<Persona> personas ;
    private final View view;
    SuicidiosGUI animacionSuicidios;
    private boolean pause;


    public SuicidiosGUI getAnimacionSuicidios() {
        return animacionSuicidios;
    }
    
    public Model(View view){
        this.personas= new CircleDoubleLinkedList();
        personas= inicializarPersonas(12, 0);
        this.view= view;
        pause=false;
    }

    public CircleDoubleLinkedList<Persona> inicializarPersonas(int cantidad, int indexPrimero){

        for(int i=0; i<cantidad;i++){
            personas.addLast(new Persona(i));
        }
        personas.setLast(cantidad-indexPrimero);
        return personas;
    }
    
    public void suicidios(int saltos, String direccion, long velocidad, int inicio) throws InterruptedException{
        animacionSuicidios= new SuicidiosGUI(direccion,velocidad,saltos, inicio);
        animacionSuicidios.start();
   }
    
    private class SuicidiosGUI extends Thread{
        private Persona asesino;
        private Persona persona;
        private Persona nuevoAsesino;
        private String direccion;
        private long velocidad;
        private int saltos;
        private int inicio;

        public SuicidiosGUI(String direccion, long velocidad,int saltos, int inicio) {
            this.direccion = direccion;
            this.asesino= null;
            this.persona= null;
            this.nuevoAsesino=null;
            this.velocidad=velocidad;
            this.saltos= saltos;
            this.inicio = inicio;
        }
        
        @Override
        @SuppressWarnings("empty-statement")
        public void run(){
            int nodosRestantes= personas.size();
            
            ListIterator<Persona> personsIte= personas.listIterator();
            if("Derecha".equals(direccion)){
                for (int i = 0; i < inicio; i++) {
                    asesino=personsIte.next();
                }
                
                if (inicio == 0)
                    asesino= personsIte.next(); 
            }
            else {
                for (int i = 0; i < inicio; i++) {
                    asesino=personsIte.previous();
                }
                if (inicio == 0)
                asesino= personsIte.previous();
            }
            
            while(!pause && nodosRestantes>0){                
                int cont=0;
                while (cont <=saltos && nodosRestantes>1) {
                    if ("Derecha".equals(direccion)) persona= personsIte.next();
                    else persona= personsIte.previous();
                    cont++;
                    while(persona.getIsAlive() != true ){
                        persona=personsIte.next();
                    }
                    asesino.matar(persona, view,velocidad);
                if("Derecha".equals(direccion)) nuevoAsesino=personsIte.next();
                else nuevoAsesino= personsIte.previous();
                while (nuevoAsesino.getIsAlive()==false) {
                    if("Derecha".equals(direccion)) nuevoAsesino=personsIte.next();
                    else nuevoAsesino= personsIte.previous();
                }
                asesino.pasarEspada(nuevoAsesino, view,velocidad);
                nodosRestantes--;
                asesino=nuevoAsesino;
                }
            }
            if("Derecha".equals(direccion)) persona=personsIte.next();
            else persona= personsIte.previous();
            while (persona.getIsAlive()==false) {
                if("Derecha".equals(direccion)) persona=personsIte.next();
                else persona= personsIte.previous(); 
                
            }
            
            //otra direccion
            if("Izquierda".equals(direccion)){
                if (inicio == 0)
                    asesino= personsIte.next(); 
                else{
                    for (int i = 0; i < inicio; i++) {
                    asesino=personsIte.next();
                }
                }                              
                
            }
            else {
                if (inicio == 0)
                    asesino= personsIte.previous();
                else{
                    for (int i = 0; i < inicio; i++) {
                        asesino=personsIte.previous();
                    }
                }
            }
            while(nodosRestantes>0 && !pause){                
                int cont=0;
                while (cont <=saltos && nodosRestantes>1) {
                    if ("Izquierda".equals(direccion)) {
                        if (inicio == 0)
                            persona= personsIte.next(); 
                        else{
                            for (int i = 0; i < inicio; i++) {
                                persona=personsIte.next();
                            }
                        }
                    }
                    
                    else {
                        if (inicio == 0)
                            persona= personsIte.previous();
                        else{
                            for (int i = 0; i < inicio; i++) {
                                persona=personsIte.previous();
                            }
                        }
                    }
                    cont++;
                    while(persona.getIsAlive() != true ){
                        persona=personsIte.next();
                    }
                    asesino.matar(persona, view,velocidad);
                if("Izquierda".equals(direccion)) nuevoAsesino=personsIte.next();
                else nuevoAsesino= personsIte.previous();
                while (nuevoAsesino.getIsAlive()==false) {
                    if("Izquierda".equals(direccion)) nuevoAsesino=personsIte.next();
                    else nuevoAsesino= personsIte.previous();
                }
                asesino.pasarEspada(nuevoAsesino, view,velocidad);
                nodosRestantes--;
                asesino=nuevoAsesino;
                }
            if("Izquierda".equals(direccion)) persona=personsIte.next();
            else persona= personsIte.previous();
            while (persona.getIsAlive()==false) {
                if("Izquierda".equals(direccion)) persona=personsIte.next();
                else persona= personsIte.previous();  
            }
            } 
        }
    }
    
    
    
    //Setter y getters
    
    public boolean getPause() {
        return pause;
    }

    
    public void setPause(boolean pause) {
        this.pause = pause;
    }
    
    
    public CircleDoubleLinkedList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(CircleDoubleLinkedList<Persona> personas) {
        this.personas = personas;
    }
}
