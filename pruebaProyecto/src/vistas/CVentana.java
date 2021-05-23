/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import clases.Ataque;
import clases.ExcepcionActuaizar;
import clases.Pokemon;
import controlador.Controlador;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * import pokemonPokedex.Ataque; import pokemonPokedex.Controlador; import
 * pokemonPokedex.Pokemon;
 *
 * @author Guiller
 */
public class CVentana implements IVentana {

    private Scanner sc;
    private Pokemon poke;
    private String menu,   msg,msg2;
    private int operacion;
    private boolean actualizacion;
    private Controlador controlador;
    private int instruccion;
    private int clave;
    private String nombre;
   
    public CVentana() {
        instanciar();

    }

    private void instanciar() {

        menu = "(1)Crear\t(5)Primero\n(2)Modificar\t(6)Anterior\n(3)Borrar\t"
                + "(7)Siguiente\n(4)Salir\t(8)Último\n(9)Consulta por clave\t(10)"
                + "consulta por nombre\n> ";
        msg="\nNo existen registros\n";
        msg2="\nIntroduce un valor correcto\n";

    }

    private void menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("¿Que operación deseas realizar?");
            System.out.print(menu);
            try{
            operacion = sc.nextInt();
            }catch(InputMismatchException im){
                break;
            }
            if (!menuPrincipalOperacion()) {
                break;
            }
        }

    }

    
    //llama al controlador segun la operacion elegida
    
    
    private boolean menuPrincipalOperacion() {
        try {
            switch (operacion) {
                case 1:
                    crearPokemon();
                    break;
                case 2:
                    modificar();
                    break;
                case 3:
                    eliminarPokemon();
                    break;
                case 4:return false;
                case 5:
                    instruccion = 6;
                    controlador.notificacion();
                    modificar();
                    break;
                case 6:
                    instruccion = 7;
                    controlador.notificacion();
                    modificar();
                    break;
                case 7:
                    instruccion = 8;
                    controlador.notificacion();
                    modificar();
                    break;
                case 8:
                    instruccion = 9;
                    controlador.notificacion();
                    modificar();
                    break;
                    case 9:
                    consultaPorClave();
                    break;
                    case 10:
                    consultaPorNombre();
                    break;
                default:return false;
            }
        } catch (ExcepcionActuaizar ex) {
        } catch (NullPointerException ex) { 
            System.out.println(msg);
        }catch(IndexOutOfBoundsException ib){
            System.out.println(msg);
        }catch(FileNotFoundException fn){ 
            System.out.println(msg);
        } catch (IOException ex) {
        } catch(InputMismatchException in){ 
            System.out.println(msg2);
        }
            return true;
    }
    
    //creamos las operaciones que puede realizar el usuario
    private void crearPokemon() throws IOException, ExcepcionActuaizar,InputMismatchException {
        int i;
        sc = new Scanner(System.in);
        String datos;
        poke = new Pokemon();
        System.out.println("Introduce los siguientes datos: ");
        System.out.print("\nCodigo Pokemon: ");
        poke.setCodigoPokedex(sc.nextInt());
        System.out.print("Nombre Pokemon: ");
        sc.nextLine();
        datos = sc.nextLine();
        poke.setNombre(datos);
        System.out.println("\n--------------------------------------------------");
        System.out.print("\n\nElige tipo:\n(1)Agua\t\t(2)Acero\t(3)Bicho\n(4)"
                + "Dragon\t(5)Electrico\t(6)Fantasma\n(7)Fuego\t(8)Hada\t\t(9)"
                + "Hielo\n(10)Lucha\t(11)Normal\t(12)Planta\n(13)Psiquico\t"
                + "(14)Roca\t(15)Siniestro\n(16)Tierra\t(17)Veneno\t(18)Volador\n> ");
        i = sc.nextInt();
        poke.setTipo(tipo(i));
        System.out.println("--------------------------------------------------");
        System.out.print("Elige generacion: \n(1)Primera\t(2)Segunda"
                + "\n(3)Tercera\t(4)Cuarta\n(5)Quinta\t(6)Sexta\n(7)Séptima\n> ");
        poke.setGeneracion(sc.nextInt());
        System.out.println("--------------------------------------------------");
        System.out.print("\nIntroduce el objeto especial: ");
        sc.nextLine();
        poke.setObjetoEspecial(sc.nextLine());
        System.out.print("\nIntroduce la zona de aparición: ");
        poke.setZonaDeAparicion(sc.nextLine());
        actualizacion = false;
        crearP2();
    }
    private void crearP2() throws IOException, ExcepcionActuaizar {
        String ataque;
        System.out.print("\nElige género:\n(1)Masculino\t(2)Femenino\n> ");
        if (sc.nextInt() == 1) poke.setGenero(false);
        else poke.setGenero(true);
        System.out.println("\n--------------------------------------------------");
        System.out.print("¿Es shiny?  S/N: ");
        if (sc.next().equalsIgnoreCase("s"))poke.setShiny(true);
        else poke.setShiny(false);
        System.out.print("\nIntroduce la vida: ");
        poke.setVida(sc.next());
        System.out.print("\nInserta el numero de evoluciones: ");
        poke.setNumEvoluciones(sc.next());
        System.out.println("\n--------------------------------------------------");
        System.out.println("\n\nIntroduce el nombre del ataque 1:");
        ataque = sc.next();
        sc.nextLine();
        System.out.println("Introduce los PS del ataque 1: ");
        poke.setAtaque1(new Ataque(ataque, sc.next()));
        System.out.println("\nIntroduce el nombre del ataque 2:");
        ataque = sc.next();
        sc.nextLine();
        System.out.println("Introduce los PS del ataque 2: ");
        poke.setAtaque2(new Ataque(ataque, sc.next()));
        System.out.println("\nIntroduce el nombre del ataque 3:");
        ataque = sc.next();
        sc.nextLine();
        System.out.println("Introduce los PS del ataque 3: ");
        poke.setAtaque3(new Ataque(ataque, sc.next()));
        System.out.println("\nIntroduce el nombre del ataque 4:");
        ataque = sc.next();
        sc.nextLine();
        System.out.println("Introduce los PS del ataque 4: ");
        poke.setAtaque4(new Ataque(ataque, sc.next()));
        System.out.println("--------------------------------------------------");
        instruccion = 3;
        controlador.notificacion();
    }
    private String tipo(int num) {
        switch (num) {
            case 1:return "AGUA";
            case 2:return "ACERO";
            case 3:return "BICHO";
            case 4:return "DRAGON";
            case 5:return "ELECTRICO";
            case 6: return "FANTASMA";
            case 7:return "FUEGO";
            case 8:return "HADA";
            case 9: return "HIELO";
            case 10:return "LUCHA";
            case 11:return "NORMAL";
            case 12:return "PLANTA";
            case 13: return "PSIQUICO";
            case 14: return "ROCA";
            case 15:return "SINIESTRO";
            case 16:return "TIERRA";
            case 17: return "VENENO";
            case 18:return "VOLADOR";
            default:return "";
        }
    }
    
    private void eliminarPokemon() throws IOException, ExcepcionActuaizar {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el codigo del pokemon: ");
        poke = new Pokemon(sc.nextInt());
        
            instruccion = 2;
           controlador.notificacion();
           System.out.println("eliminado");
        
    }
   
    private void consultaPorClave() throws IOException, ExcepcionActuaizar {
        sc = new Scanner(System.in);
        System.out.print("Introduce el codigo: ");
        clave=sc.nextInt();
        instruccion=5;
        controlador.notificacion();
    }
    
    private void consultaPorNombre() throws IOException, ExcepcionActuaizar {
        sc = new Scanner(System.in);
        System.out.print("Introduce el nombre: ");
        nombre=sc.next();
        instruccion=10;
        controlador.notificacion();
    }
    
    private boolean modificar() throws IOException, ExcepcionActuaizar{
        sc = new Scanner(System.in);
        System.out.print("Quieres modificarlo? S/N >");
        
        if(sc.next().equalsIgnoreCase("s")){
            editarPokemon();
            return true;
        }else return false;
        
    }
    private void editarPokemon() throws IOException, ExcepcionActuaizar {
     sc = new Scanner(System.in);
        String datos;
        
        
        System.out.println("\nCodigo Pokemon: "+poke.getCodigoPokedex());
        System.out.print("Nombre Pokemon: ");
        
        datos = sc.nextLine();
        poke.setNombre(datos);
        System.out.println("\n--------------------------------------------------");
        System.out.print("\n\nElige tipo:\n(1)Agua\t\t(2)Acero\t(3)Bicho\n(4)"
                + "Dragon\t(5)Electrico\t(6)Fantasma\n(7)Fuego\t(8)Hada\t\t(9)"
                + "Hielo\n(10)Lucha\t(11)Normal\t(12)Planta\n(13)Psiquico\t"
                + "(14)Roca\t(15)Siniestro\n(16)Tierra\t(17)Veneno\t(18)Volador\n> ");
        int i = sc.nextInt();
        poke.setTipo(tipo(i));
        System.out.println("--------------------------------------------------");
        System.out.print("Elige generacion: \n(1)Primera\t(2)Segunda"
                + "\n(3)Tercera\t(4)Cuarta\n(5)Quinta\t(6)Sexta\n(7)Séptima\n> ");
        poke.setGeneracion(sc.nextInt());
        System.out.println("--------------------------------------------------");
        System.out.print("\nIntroduce el objeto especial: ");
        sc.nextLine();
        poke.setObjetoEspecial(sc.nextLine());
        System.out.print("\nIntroduce la zona de aparición: ");
        poke.setZonaDeAparicion(sc.nextLine());
        actualizacion = true;
        editarV2();
    }
    private void editarV2() throws IOException, ExcepcionActuaizar {
        String ataque;
        System.out.print("\nElige género:\n(1)Masculino\t(2)Femenino\n> ");
        if (sc.nextInt() == 1) poke.setGenero(false);
        else poke.setGenero(true);
        System.out.println("\n--------------------------------------------------");
        System.out.print("¿Es shiny?  S/N: ");
        if (sc.next().equalsIgnoreCase("s"))poke.setShiny(true);
        else poke.setShiny(false);
        System.out.print("\nIntroduce la vida: ");
        poke.setVida(sc.next());
        System.out.print("\nInserta el numero de evoluciones: ");
        poke.setNumEvoluciones(sc.next());
        System.out.println("\n--------------------------------------------------");
        System.out.println("\n\nIntroduce el nombre del ataque 1:");
        ataque = sc.next();
        sc.nextLine();
        System.out.println("Introduce los PS del ataque 1: ");
        poke.setAtaque1(new Ataque(ataque, sc.next()));
        System.out.println("\nIntroduce el nombre del ataque 2:");
        ataque = sc.next();
        sc.nextLine();
        System.out.println("Introduce los PS del ataque 2: ");
        poke.setAtaque2(new Ataque(ataque, sc.next()));
        System.out.println("\nIntroduce el nombre del ataque 3:");
        ataque = sc.next();
        sc.nextLine();
        System.out.println("Introduce los PS del ataque 3: ");
        poke.setAtaque3(new Ataque(ataque, sc.next()));
        System.out.println("\nIntroduce el nombre del ataque 4:");
        ataque = sc.next();
        sc.nextLine();
        System.out.println("Introduce los PS del ataque 4: ");
        poke.setAtaque4(new Ataque(ataque, sc.next()));
        System.out.println("--------------------------------------------------");
        actualizacion=true;
        instruccion = 1;
        try{
        controlador.notificacion();
        }catch (NullPointerException ex) {
            System.out.println("\nPorfavor, antes de modificar, primero hay que"
                    + " buscar "
                    + "un registro\n");
        }
    }
    
    
    //sobreescribir los metodos
    @Override
    public boolean getActualizacion() {

        return actualizacion;
    }

    @Override
    public int getClave() {
     return clave;
    }
    @Override
    public String getNombre() {
     return  nombre;
             }

    @Override
    public void setPokemon(Pokemon po) {
        poke=po;
        System.out.println(po.toString(0));
    }

    @Override
    public Pokemon getPokemon() {
        Pokemon p = poke;
        return p;
    }

    @Override
    public void mostrar() {
        menuPrincipal();
    }

    @Override
    public void setControlador(Controlador c) {
        controlador = c;
    }

    @Override
    public int getInstruccion() {
        return instruccion;
    }



}
