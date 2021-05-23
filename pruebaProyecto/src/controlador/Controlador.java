/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import clases.ExcepcionActuaizar;
import vistas.IVentana;
import modelos.IModelo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Guiller
 */
public class Controlador  {

    private IModelo modelo;
    private IVentana vista;

    public Controlador(IModelo modelo, IVentana vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void notificacion() throws IOException,ExcepcionActuaizar,NullPointerException{
        try{
        switch (vista.getInstruccion()) {
            case 1:
                modelo.setActualizacion(vista.getActualizacion());
                modelo.darDeAlta(vista.getPokemon());
                break;
            case 2:
                modelo.darDeBaja(vista.getPokemon());
                if(modelo.getContador()!=0)
                vista.setPokemon(modelo.primero());
                else vista.setPokemon(modelo.siguiente());
                
                break;
            case 3:
                modelo.darDeAlta(vista.getPokemon());
                break;
            case 4:
                vista.setPokemon(modelo.restablecer(vista.getPokemon()));
                break;
            case 5:
               vista.setPokemon(modelo.consultaPorClave(vista.getClave()));
                
                break;
            case 6:
                vista.setPokemon(modelo.primero());
                break;
            case 7:
                vista.setPokemon(modelo.anterior());
                break;
            case 8:
                vista.setPokemon(modelo.siguiente());

                break;
            case 9:
                vista.setPokemon(modelo.ultimo());
                break;
            case 10:
                vista.setPokemon(modelo.consultaPorNombre(vista.getNombre()));
                break;

        }
        }catch(FileNotFoundException a){
            //no existe el archivo
        }

    }
}
