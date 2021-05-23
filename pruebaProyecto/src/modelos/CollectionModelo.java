/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import clases.ExcepcionActuaizar;
import java.util.Collections;
import java.util.LinkedList;
import clases.Pokemon;

/**
 *
 * @author Vespertino
 */
public class CollectionModelo implements IModelo {

    private LinkedList<Pokemon> box;
    private int contador;
    private int contador2;
    private boolean actualizar;
    //condador lleva la cuenta de cuantos pokemon se dan de alta
    //contador2 indica en que posicion de la collection esta 

    public CollectionModelo() {
        box = new LinkedList<>();
        contador = 0;
    }

    @Override
    public void darDeAlta(Pokemon o) throws ExcepcionActuaizar {
        //en caso que el pokemon que se reciba no tiene el codigo lanzamos 
        //null para que se encargue la vista
        if (o == null) throw new NullPointerException();
        
        for (int i = 0; i < contador; i++) {
            if (box.get(i).getCodigoPokedex() == o.getCodigoPokedex()) {
                if (actualizar) {
                    box.remove(i);
                    box.add(o);
                    Collections.sort(box);
                    actualizar = false;
                    return;
                    /*si existe el registro lanzamos la excepcion para que acepte
                o deniegue la operacion*/
                } else {
                    throw new ExcepcionActuaizar();
                }
            }
        }
        //si no existe el registro lo añade directamente
        box.add(o);
        contador++;
        Collections.sort(box);
        consultaPorClave(o.getCodigoPokedex());
    }

    @Override
    public void darDeBaja(Pokemon p) {
        try{
        box.remove(contador2);
        contador--;
        }catch (IndexOutOfBoundsException iobe){
            throw new NullPointerException();
        }

    }

    @Override
    public Pokemon restablecer(Pokemon o) {
        for (int i = 0; i < contador; i++) {
            if (box.get(i).getCodigoPokedex() == o.getCodigoPokedex()) {
                return box.get(i);
            }
        }
        return null;
    }

    @Override
    public Pokemon consultaPorClave(int clave) {
        for (int i = 0; i < contador; i++) {
            if (box.get(i).getCodigoPokedex() == clave) {
                contador2 = i;
                return box.get(i);
            }
        }
        return null;

    }

    @Override
    public Pokemon consultaPorNombre(String nombre) {
        int num = 0;
        
        for (int i = contador2; i < contador; i++) {
            /*con esto hacemos que de una vuelta de mas para comprobar si 
            existen mas pokemon con el mismo nombre*/
            if (i >= contador - 1 && num == 0) {
                i = 0;
                num++;
            } else if (num == 0) {
                i++;
                num++;
            }

            if (box.get(i).getNombre().equalsIgnoreCase(nombre)) {
                contador2 = i;
                return box.get(i);
            }
        }
        return null;
    }

    @Override
    public Pokemon primero() {
        contador2 = 0;
        try{
        return box.get(contador2);
        }catch (IndexOutOfBoundsException iobe){
            return null;
        }
        
    }

    @Override
    public Pokemon ultimo() {
        contador2 = contador - 1;
        try{
        return box.get(contador2);
        }catch (IndexOutOfBoundsException iobe){
            return null;
        }
    }

    @Override
    public Pokemon siguiente() {
        if (contador2 < contador - 1) {
            contador2++;
            return box.get(contador2);

        } else {
            return ultimo();
        }
    }

    @Override
    public Pokemon anterior() {
        if (contador2 > 0) {
            contador2--;
            return box.get(contador2);

        } else {
            return primero();
        }
    }

    @Override
    public int getContador() {
        return contador;
    }

    @Override
    public void setActualizacion(boolean act) {
        actualizar = act;
    }

}
