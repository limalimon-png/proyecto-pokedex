/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import javax.swing.JButton;
import controlador.Controlador;
import clases.Pokemon;

/**
 *
 * @author Guiller
 */
public interface IVentana {
    /**
     * recibe la clave del pokemon
     * @return int
     */
    public abstract int getClave();
    /**
     * recibe el nombre del pokemon
     * @return String
     */
    public abstract String getNombre();
    /**
     * setea un pokemon
     * @param po 
     */
    public abstract void setPokemon(Pokemon po);
    /**
     * 
     * @return Pokemon
     */
    public abstract Pokemon getPokemon();
    public abstract void mostrar();
    public abstract void setControlador(Controlador c);
    /**
     * Coge la instruccion, que el controlador utilizara para elegir la accion
     * @return int
     */
    public abstract  int getInstruccion();
    /**
     * recibe si puede actualizarse o no 
     * @return boolean
     */
    public abstract  boolean getActualizacion();

   
}
