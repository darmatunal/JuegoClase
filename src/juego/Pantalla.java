/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import javax.swing.JFrame;

/**
 *
 * @author Estudiante
 */
public class Pantalla extends JFrame{

    //En el constructor se crea crea en Panel 
    public Pantalla(){
     add (new MiPanel());
    }
    
    
   
}
