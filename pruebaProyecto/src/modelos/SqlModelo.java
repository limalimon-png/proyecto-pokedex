/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import clases.Ataque;
import clases.ExcepcionActuaizar;
import clases.Pokemon;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vespertino
 */
public class SqlModelo implements IModelo {

    private boolean actualizar = false;
    private boolean ejecutar = false;
    private Connection conexion;
    private int contador;

    

    @Override
    public void darDeAlta(Pokemon po) throws IOException, ExcepcionActuaizar, NullPointerException {
        Statement sentencia;
        //en caso que el pokemon que se reciba no tiene el codigo lanzamos 
        //null para que se encargue la vista
        if(po==null)throw new NullPointerException();
        
        try {
            int filas;
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/bd_pokemon", "root",
                    "");
            //insertamos los valores del pokemon
            String sql = "INSERT INTO pokedex VALUES (" + po.getCodigoPokedex() + 
                    ", '"+ po.getNombre() + "','" + po.getTipo() + "','" + 
                    po.getGeneracion() + "','" + po.getObjetoEspecial()
                    + "','" + po.getZonaDeAparicion() + "'," + po.getNumEvoluciones()
                    + "," + po.getVida()+ ",'" + po.getShiny() + "','" + 
                    po.getGenero() + "','" + po.getAtaque1().getNombreAtaque() + 
                    "',"+ po.getAtaque1().getPs() + ",'" + 
                    po.getAtaque2().getNombreAtaque() + "',"
                    + po.getAtaque2().getPs() + ",'" +
                    po.getAtaque3().getNombreAtaque() + "',"
                    + po.getAtaque3().getPs() + ",'" 
                    + po.getAtaque1().getNombreAtaque() + "',"
                    + po.getAtaque1().getPs() + "," + 1 + ")";
          
            System.out.println(sql);
            sentencia = conexion.createStatement();
            try {
                filas = sentencia.executeUpdate(sql);
            } catch (SQLException e) {
                //cuando la excepcion actualizar ya ha saltado y en la vista lo han aceptado
                if (consultaPorClave(po.getCodigoPokedex()) != null && actualizar == true) {
                    sentencia.executeUpdate("delete from pokedex where codPokedex=" + po.getCodigoPokedex() + ";");
                    sentencia.executeUpdate(sql);
                    actualizar=false;
                    sentencia.close(); 
                    conexion.close();
                    return;
                }
                //en caso de que el registro ya exista saltara la excepcion
                sentencia.close(); 
                conexion.close();
                throw new ExcepcionActuaizar();
            }
            //cierra la conexion si no ha tenido que actualizar
            sentencia.close(); 
            conexion.close(); 
            }catch (NullPointerException nu){
                throw nu;
            }catch(ExcepcionActuaizar ea){
                throw new ExcepcionActuaizar();
            }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void darDeBaja(Pokemon p) throws IOException {
        if(p==null)throw new NullPointerException();
        try {
            int filas;
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/bd_pokemon", "root",
                    "");

            String sql = "DELETE FROM  pokedex WHERE codPokedex=" + p.getCodigoPokedex();

            System.out.println(sql);

            Statement sentencia = conexion.createStatement();
            filas = 0;
            try {
                filas = sentencia.executeUpdate(sql);
            } catch (SQLException e) {
                System.out.println("error sql exception");
            }

            sentencia.close(); // Cerrar Statement
            conexion.close(); // Cerrar conexi�n
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public Pokemon restablecer(Pokemon o) throws IOException {
        return consultaPorClave(o.getCodigoPokedex());
    }

    @Override
    public Pokemon consultaPorClave(int clave) throws IOException {
        String sql = "SELECT * FROM pokedex  where codPokedex=" + clave + ";";
        return setearPokemon(sql);
    }

    @Override
    public Pokemon consultaPorNombre(String nombre) throws IOException {
        String sql = "SELECT * FROM pokedex  where nombrePokemon=" + "'" + nombre + "';";
        return setearPokemon(sql);
    }

    @Override
    public Pokemon primero() throws IOException {
        contador = 1;
        String SQL = "SELECT * FROM pokedex  limit 1";
        return setearPokemon(SQL);
    }

    @Override
    public Pokemon ultimo() throws IOException {
        String SQL = "SELECT * FROM pokedex  order by codPokedex desc limit 1";
        contador = contar();
        return setearPokemon(SQL);
    }

    @Override
    public Pokemon siguiente() throws IOException {
        if (contador < contar()) {
            contador++;
            return setearPokemon("select * from pokedex", contador);
        } else {
            return ultimo();
        }
    }

    @Override
    public Pokemon anterior() throws IOException {
        if (contador > 1) {
            contador--;
            return setearPokemon("select * from pokedex", contador);
        } else {
            return primero();
        }

    }

    @Override
    public int getContador() throws IOException {
        return contar();
    }

    private Pokemon setearPokemon(String sql) {
        Pokemon aux = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/bd_pokemon", "root",
                    "");
            aux = new Pokemon();

            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();
            aux.setCodigoPokedex(rs.getInt("codpokedex"));
            aux.setNombre(rs.getString("nombrePokemon"));
            aux.setTipo(rs.getString("tipo"));
            aux.setGeneracion(rs.getInt("generacion"));
            aux.setObjetoEspecial(rs.getString("objetoEspecial"));
            aux.setZonaDeAparicion(rs.getString("zonaAparicion"));
            aux.setNumEvoluciones(rs.getInt("numEvoluciones")+"");
            aux.setVida(rs.getInt("vidaPokemon")+"");
            aux.setShiny(Boolean.parseBoolean(rs.getString("shiny")));
            aux.setGenero(Boolean.parseBoolean(rs.getString("genero")));
            aux.setAtaque1(new Ataque(rs.getString("nomAtaque1") + "", rs.getInt("psAtaque1")+""));
            aux.setAtaque2(new Ataque(rs.getString("nomAtaque2") + "", rs.getInt("psAtaque2")+""));
            aux.setAtaque3(new Ataque(rs.getString("nomAtaque3") + "", rs.getInt("psAtaque3")+""));
            aux.setAtaque4(new Ataque(rs.getString("nomAtaque4") + "", rs.getInt("psAtaque4")+""));
            Blob blob = rs.getBlob("imagen");

            rs.close();
            stmt.close();
        }catch (SQLException s){
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aux;

    }

    private Pokemon setearPokemon(String sql, int contador) {
        int cont = 0;
        Pokemon aux = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/bd_pokemon", "root",
                    "");
            aux = new Pokemon();

            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                cont++;
                if (contador == cont) {
                    aux.setCodigoPokedex(rs.getInt("codpokedex"));
                    aux.setNombre(rs.getString("nombrePokemon"));
                    aux.setTipo(rs.getString("tipo"));
                    aux.setGeneracion(rs.getInt("generacion"));
                    aux.setObjetoEspecial(rs.getString("objetoEspecial"));
                    aux.setZonaDeAparicion(rs.getString("zonaAparicion"));
                    aux.setNumEvoluciones(rs.getInt("numEvoluciones")+"");
                    aux.setVida(rs.getInt("vidaPokemon")+"");
                    aux.setShiny(Boolean.parseBoolean(rs.getString("shiny")));
                    aux.setGenero(Boolean.parseBoolean(rs.getString("genero")));
                    aux.setAtaque1(new Ataque(rs.getString("nomAtaque1") + "", rs.getInt("psAtaque1")+""));
                    aux.setAtaque2(new Ataque(rs.getString("nomAtaque2") + "", rs.getInt("psAtaque2")+""));
                    aux.setAtaque3(new Ataque(rs.getString("nomAtaque3") + "", rs.getInt("psAtaque3")+""));
                    aux.setAtaque4(new Ataque(rs.getString("nomAtaque4") + "", rs.getInt("psAtaque4")+""));
                    Blob blob = rs.getBlob("imagen");
                    break;
                }
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return aux;

    }

    private int contar() {
        int cuenta = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/bd_pokemon", "root",
                    "");

            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) as cuenta from pokedex");

            rs.next();
            cuenta = rs.getInt("cuenta");

            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cuenta;
    }

    @Override
    public void setActualizacion(boolean act) {
        actualizar = act;
    }
}
