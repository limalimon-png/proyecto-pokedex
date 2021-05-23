/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Image;
import java.io.Serializable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Guiller
 */
public class Pokemon implements Comparable<Pokemon> , Serializable {

    private int codigoPokedex, generacion;
    private String nombre, tipo, ObjetoEspecial, zonaDeAparicion;
    private int vida;
    private boolean shiny;
    private int numEvoluciones;
    private Ataque ataque1, ataque2, ataque3, ataque4;
    private Icon imagen;
    private boolean genero;
    private int cod;
    
    
    //constructores
    /**
     * 
     * @param codigoPokedex
     * @param nombre
     * @param tipo
     * @param generacion
     * @param ObjetoEspecial
     * @param zonaDeAparicion
     * @param numEvoluciones
     * @param vida
     * @param shiny
     * @param genero
     * @param ataque1
     * @param ataque2
     * @param ataque3
     * @param ataque4
     * @param imagen 
     */
    public Pokemon(int codigoPokedex, String nombre, String tipo, int generacion,
            String ObjetoEspecial, String zonaDeAparicion, String numEvoluciones, String vida,
            boolean shiny, boolean genero, Ataque ataque1, Ataque ataque2, Ataque ataque3,
            Ataque ataque4, Icon imagen
    ) {

        this.codigoPokedex = codigoPokedex;
        setNombre(nombre);
        setTipo(tipo);
        setGeneracion(generacion);
        setObjetoEspecial(ObjetoEspecial);
        setZonaDeAparicion(zonaDeAparicion);
        setVida(vida);
        setShiny(shiny);
        setNumEvoluciones(numEvoluciones);
        setGenero(genero);
        setAtaque1(ataque1);
        setAtaque2(ataque2);
        setAtaque3(ataque3);
        setAtaque4(ataque4);
        
        this.imagen = imagen;

    }

    public Pokemon() {
        this(99999, "", "", 2, "", "", 1+"", 100+"", false, false,
                new Ataque("", 25+""), new Ataque("", 25+""), new Ataque("", 25+""),
                new Ataque("", 25+""), null);

    }
    
    public Pokemon(int cod) {
        this(cod, "", "", 2, "", "", 1+"", 100+"", false, false,
                new Ataque("", 25+""), new Ataque("", 25+""), new Ataque("", 25+""),
                new Ataque("", 25+""), null);

    }
    
    
    //getters

    
    public int getCodigoPokedex() {
        return codigoPokedex;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getGeneracion() {
        return generacion;
    }

    public boolean getGenero() {
        return genero;
    }

    public String getObjetoEspecial() {
        return ObjetoEspecial;
    }

    public String getZonaDeAparicion() {
        return zonaDeAparicion;
    }

    public int getVida() {
        return vida;
    }

    public boolean getShiny() {
        return shiny;
    }

    public int getNumEvoluciones() {
        return numEvoluciones;
    }

    public Ataque getAtaque1() {
        return ataque1;
    }

    public Ataque getAtaque2() {
        return ataque2;
    }

    public Ataque getAtaque3() {
        return ataque3;
    }

    public Ataque getAtaque4() {
        return ataque4;
    }

    public Icon getImagen() {
        return imagen;
    }

    
    //setters 
    
    
    public void setCodigoPokedex(int codigoPokedex) {
        this.codigoPokedex = codigoPokedex;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setGeneracion(int generacion) {
        if(generacion<0||generacion>18) this.generacion=1;
        else this.generacion = generacion;
    }

    public void setObjetoEspecial(String ObjetoEspecial) {
        this.ObjetoEspecial = ObjetoEspecial;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }

    public void setZonaDeAparicion(String zonaDeAparicion) {
        this.zonaDeAparicion = zonaDeAparicion;
    }

    public void setVida(String vida) {
        if(vida.matches("\\d*")&&Integer.parseInt(vida)>0)
        this.vida = Integer.parseInt(vida);
        else this.vida=100;
    }

    public void setShiny(boolean shiny) {
        this.shiny = shiny;
    }

    public void setNumEvoluciones(String numEvoluciones) {
        if(numEvoluciones.matches("\\d*")&&Integer.parseInt(numEvoluciones)>-1)
        this.numEvoluciones = Integer.parseInt(numEvoluciones);
        else this.numEvoluciones=1;
    }

    public void setImagen(Icon imagen) {
        this.imagen = imagen;
    }

    public void setAtaque1(Ataque ataque1) {
        this.ataque1 = ataque1;
    }

    public void setAtaque2(Ataque ataque2) {
        this.ataque2 = ataque2;
    }

    public void setAtaque3(Ataque ataque3) {
        this.ataque3 = ataque3;
    }

    public void setAtaque4(Ataque ataque4) {
        this.ataque4 = ataque4;
    }

    
    //overriders
    
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        
        return "#"+getCodigoPokedex()+"#"+getNombre()+"#"+getTipo()+
                "#"+getGeneracion()+"#"+getObjetoEspecial()+"#"+getZonaDeAparicion()
                +"#"+getNumEvoluciones()+"#"+getVida()+"#"+getShiny()+"#"+getGenero()
                +"#"+getAtaque1()+"#"+getAtaque2()+"#"+getAtaque3()+"#"+getAtaque4()
                +"#"+"*";
       
    }
    public String toString(int a) {
        
        return "----------------------------------------------------------------"
                + "\nCodigo pokedex: "+getCodigoPokedex()+"\nNombre: "+getNombre()+""
                + "\nTipo: "+getTipo()+"\nGeneracion : "+getGeneracion()+"\n"
                + "Objeto especial: "+getObjetoEspecial()+"\nZona de aparicion: "
                + ""+getZonaDeAparicion()+"\nNum evoluciones: "
                +getNumEvoluciones()+"\nVida: "+getVida()+"\nShiny: "+getShiny()+
                "\nGénero: "+getGenero()+"\nNombre ataque 1: "+getAtaque1()
                        .getNombreAtaque()+"\nPS: "+getAtaque1().getPs()+"\nNom"
                + "bre ataque 2: "+getAtaque2()
                        .getNombreAtaque()+"\nPS: "+getAtaque2().getPs()+"\nNom"
                + "bre ataque 3: "+getAtaque3()
                        .getNombreAtaque()+"\nPS: "+getAtaque3().getPs()+"\nNom"
                + "bre ataque 4: "+getAtaque4()
                        .getNombreAtaque()+"\nPS: "+getAtaque4().getPs()
                +"\n------------------------------------------------------------";
       
    }

    @Override
    public int compareTo(Pokemon o) {
        return getCodigoPokedex() - o.getCodigoPokedex();
    }

    public Pokemon getPokemon() {
        return this;
     }

}
