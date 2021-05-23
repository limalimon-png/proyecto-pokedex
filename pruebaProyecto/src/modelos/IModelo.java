/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import clases.ExcepcionActuaizar;
import java.io.IOException;
import clases.Pokemon;

/**
 *
 * @author Guiller
 */
public interface IModelo {
    /**
     * Recibimos un pokemon para guardarlo o actualizarlo
     * @param po
     * @throws IOException
     * @throws ExcepcionActuaizar 
     */
    public abstract void darDeAlta(Pokemon po) throws IOException,ExcepcionActuaizar;
    /**
     * Recibimos un pokemon para eliminarlo
     * @param p
     * @throws IOException 
     */
    public abstract void darDeBaja(Pokemon p)throws IOException;
    /**
     * recibimos un pokemon y buscamos su codigo para restablecerlo.
     * @param o
     * @return Pokemon
     * @throws IOException 
     */
    public abstract Pokemon restablecer(Pokemon o)throws IOException;
    /**
     * Recibimos la clave del pokemon, en caso de que exista devolveremos el 
     * pokemon, si no null
     * @param clave
     * @return Pokemon
     * @throws IOException 
     */
    public abstract Pokemon consultaPorClave(int clave)throws IOException;
    /**
     * Recibimos el nombre del pokemon, en caso de que exista devolveremos el 
     * pokemon
     * @param nombre
     * @return Pokemon
     * @throws IOException 
     */
    public abstract Pokemon consultaPorNombre(String nombre)throws IOException;
    /**
     * Devuelve el primer pokemon, en caso de no existir devuelve null
     * @return Pokemon
     * @throws IOException 
     */
    public abstract Pokemon primero()throws IOException;
    /**
     * Devuelve el último pokemon, en caso de no existir devuelve null
     * @return Pokemon
     * @throws IOException 
     */
    public abstract Pokemon ultimo()throws IOException;
    /**
     * Devuelve el pokemon siguinte, en caso de no existir devuelve null
     * @return Pokemon
     * @throws IOException 
     */
    public abstract Pokemon siguiente()throws IOException;
    /**
     * Devuelve el  pokemon anterior, en caso de no existir devuelve null
     * @return Pokemon
     * @throws IOException 
     */
    public abstract Pokemon anterior()throws IOException;
    /**
     * Devuelve en que posicion esta el contador
     * @return int
     * @throws IOException 
     */
    public abstract int getContador()throws IOException;
    /**
     * Recibe si se puede actualizar o no
     * @param act 
     */
    public abstract void setActualizacion(boolean act);
    
}
