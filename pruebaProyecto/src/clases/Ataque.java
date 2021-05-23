/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;

/**
 *
 * @author Guiller
 */
public class Ataque implements Serializable{
 private String nombreAtaque;
 private int ps;
  
 //constructor
 
/**
 * 
 * @param nombreAtaque
 * @param ps 
 */
    public Ataque(String nombreAtaque, String ps) {
        setNombreAtaque(nombreAtaque);
        setPs(ps);
    }
    
    //getters

    public String getNombreAtaque() {
        return nombreAtaque;
    }
    
     public int getPs() {
        return  ps;
    }

     
     //setters
     
     
    public void setNombreAtaque(String nombreAtaque) {
        if(nombreAtaque.isEmpty())
        this.nombreAtaque = nombreAtaque+" ";
        else this.nombreAtaque=nombreAtaque;
    }

    public void setPs(String ps) {
        if(ps.matches("\\d*")&&Integer.parseInt(ps)>-1)
        this.ps = Integer.parseInt(ps);
        else this.ps=25;
    }

    
    //overriders
    
    
    @Override
    public String toString() {
        return  nombreAtaque + "#" + ps;
    }
    
    
}
