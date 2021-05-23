/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import clases.ExcepcionActuaizar;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import clases.Pokemon;

/**
 *
 * @author Guiller
 */
public class MapaModelo implements IModelo {

    private TreeMap<Pokemon, Pokemon> box;
    private int contador;
    private int contador2;
    private boolean actualizacion;
    //condador lleva la cuenta de cuantos pokemon se dan de alta
    //contador2 indica en que posicion del mapa esta

    public MapaModelo() {
        box = new TreeMap();
        contador2 = contador = 0;
    }

    @Override
    public void darDeAlta(Pokemon o) throws ExcepcionActuaizar {
        Iterator it;
        //en caso que el pokemon que se reciba no tiene el codigo lanzamos 
        //null para que se encargue la vista
        if (o == null) {
            throw new NullPointerException();
        }

        Set a = box.keySet();
        it = a.iterator();

        while (it.hasNext()) {
            Pokemon po = (Pokemon) it.next();
            if (po.getCodigoPokedex() == o.getCodigoPokedex()) {
                if (actualizacion == true) {
                    box.remove(po);
                    box.put(new Pokemon(o.getCodigoPokedex()), o);
                    actualizacion = false;
                    return;
                    /*si existe el registro lanzamos la excepcion para que acepte
                o deniegue la operacion*/
                } else {
                    throw new ExcepcionActuaizar();
                }
            }
        }
        //si no existe el registro lo añade directamente

        box.put(new Pokemon(o.getCodigoPokedex()), o);
        contador++;
        consultaPorClave(o.getCodigoPokedex());
    }

    @Override
    public void darDeBaja(Pokemon p) {
        Set a = box.keySet();
        Iterator it = a.iterator();
        while (it.hasNext()) {
            Pokemon po = (Pokemon) it.next();
            if (po.getCodigoPokedex() == p.getCodigoPokedex()) {
                box.remove(po);
            }
        }
        contador--;
    }

    @Override
    public Pokemon restablecer(Pokemon o) {
        return consultaPorClave(o.getCodigoPokedex());
    }

    @Override
    public Pokemon consultaPorClave(int clave) {
        Set a = box.keySet();
        Iterator it = a.iterator();
        int cont = 1;

        while (it.hasNext()) {
            Pokemon po = (Pokemon) it.next();
            if (po.getCodigoPokedex() == clave) {
                contador2 = cont;
                return box.get(po);
            }
            cont++;
        }

        return null;
    }

    @Override
    public Pokemon consultaPorNombre(String nombre) {
        Set a = box.keySet();
        Iterator it = a.iterator();
        int cont = 0;

        while (it.hasNext()) {
            Pokemon po = (Pokemon) it.next();
            if (po.getNombre() == nombre) {
                contador2 = cont;
                return box.get(po);
            }
            cont++;
        }

        return null;
    }

    @Override
    public Pokemon primero() {
        Set a = box.keySet();
        Iterator it = a.iterator();
        
        while (it.hasNext()) {
            Pokemon po = (Pokemon) it.next();
            contador2 = 1;
            return box.get(po);
        }
        return null;

    }

    @Override
    public Pokemon ultimo() {
        Set a = box.keySet();
        Iterator it = a.iterator();
        int cont = 1;
        
        while (it.hasNext()) {
            Pokemon po = (Pokemon) it.next();
            if (cont == a.size()) {
                return consultaPorClave(po.getCodigoPokedex());
            }
            cont++;
        }
        return null;
    }

    @Override
    public Pokemon siguiente() {
        Set a = box.keySet();
        Iterator it = a.iterator();
        int cont = 1;
        
        while (it.hasNext()) {
            Pokemon po = (Pokemon) it.next();
            if (cont == contador2 + 1) {
                contador2++;
                return box.get(po);
            }
            cont++;
        }
        return ultimo();
    }

    @Override
    public Pokemon anterior() {
        Set a = box.keySet();
        Iterator it = a.iterator();
        int cont = 1;
        
        while (it.hasNext()) {
            Pokemon po = (Pokemon) it.next();
            if (contador == 1) {
                return primero();
            }
            if (cont == contador2 - 1) {
                contador2--;
                return box.get(po);
            }
            cont++;
        }
        return null;
    }

    @Override
    public int getContador() {
        return contador;
    }

    @Override
    public void setActualizacion(boolean act) {
        actualizacion = act;
    }

}
