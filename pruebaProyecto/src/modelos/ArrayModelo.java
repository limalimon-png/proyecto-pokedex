/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import clases.ExcepcionActuaizar;
import java.util.Arrays;
import clases.Pokemon;

/**
 *
 * @author Guiller
 */
public class ArrayModelo implements IModelo {

    private Pokemon[] box;
    private int contador;
    private int contador2;
    private boolean actualizar;
    //condador lleva la cuenta de cuantos pokemon se dan de alta
    //contador2 indica en que posicion del array esta 

    public ArrayModelo() {
        box = new Pokemon[10];
        Arrays.fill(box, new Pokemon());
        contador = 0;
        contador2 = 0;
    }

    @Override
    public void darDeAlta(Pokemon po) throws ExcepcionActuaizar {
        //en caso que el pokemon que se reciba no tiene el codigo lanzamos 
        //null para que se encargue la vista
        if (po == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < contador; i++) {
            if (box[i].getCodigoPokedex() == po.getCodigoPokedex()) {
                if (actualizar) {
                    box[i] = po;
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
        box[contador] = po;
        contador++;
        Arrays.sort(box);
        consultaPorClave(po.getCodigoPokedex());
    }

    @Override
    public void darDeBaja(Pokemon po) {
        //al ir ordenados le ponemos un codigo comun y lo mandamos al final del array
        po.setCodigoPokedex(99999);
        box[contador2] = po;
        contador--;
        Arrays.sort(box);
        //cuando eliminemos el ultimo de esta forma limpiaremos la vista
        throw new NullPointerException();

    }

    @Override
    public Pokemon restablecer(Pokemon po) {
        for (int i = 0; i < contador; i++) {
            if (box[i].getCodigoPokedex() == po.getCodigoPokedex()) {
                return box[i];
            }
        }
        return null;
    }

    @Override
    public Pokemon consultaPorClave(int clave) {
        for (int i = 0; i < contador; i++) {
            if (box[i].getCodigoPokedex() == clave) {
                contador2 = i;
                return box[i];
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
            if (box[i].getNombre().equalsIgnoreCase(nombre)) {
                contador2 = i;
                return box[i];
            }
        }
        return null;
    }

    @Override
    public Pokemon primero() {
        contador2 = 0;
        if (box[contador2].getCodigoPokedex() == 99999) {
            throw new NullPointerException();
        }
        return box[contador2];
    }

    @Override
    public Pokemon ultimo() {
        contador2 = contador - 1;
        return box[contador2];
    }

    @Override
    public Pokemon siguiente() {
        if (contador2 < contador - 1) {
            contador2++;
            return box[contador2];

        } else {
            return ultimo();
        }
    }

    @Override
    public Pokemon anterior() {
        if (contador2 > 0) {
            contador2--;
            return box[contador2];

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
