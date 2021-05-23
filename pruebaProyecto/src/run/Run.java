/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import controlador.Controlador;
import vistas.SVentana;

import java.io.IOException;
import java.sql.SQLException;

import modelos.*;
import vistas.CVentana;


/**
 *
 * @author Guiller
 */
public class Run {
    public static void main(String[] args) throws SQLException, IOException {
        
        
        SVentana p = new SVentana();
        //CVentana p = new CVentana();
        ArrayModelo m = new ArrayModelo();
        //CollectionModelo m = new CollectionModelo();
       //MapaModelo m = new MapaModelo();
        //FicherosBinariosModelo m = new FicherosBinariosModelo();
        //FicherosTextoModelo m = new FicherosTextoModelo();
        //SqlModelo m = new SqlModelo();
        Controlador c = new Controlador(m, p);
        p.setControlador(c);
        p.mostrar();
    }
}
