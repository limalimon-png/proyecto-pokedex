/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import clases.Ataque;
import clases.ExcepcionActuaizar;
import clases.Pokemon;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.out;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 *
 * @author Guiller
 */
public class FicherosTextoModelo implements IModelo {

    private boolean existe = false;
    private int contador2;
    private int contador;
    private boolean actualizar;
    //condador lleva la cuenta de cuantos pokemon se dan de alta
    //contador2 indica en que posicion del fichero de texto esta

    @Override
    public void darDeAlta(Pokemon o) throws IOException, ExcepcionActuaizar {
        int valor = '*', cont = 1, caracter, cod, co = 0;
        //en caso que el pokemon que se reciba no tiene el codigo lanzamos 
        //null para que se encargue la vista
        if (o == null) {
            throw new NullPointerException();
        }

        File prueba = new File("prueba.txt");
        prueba.createNewFile();
        File prueba2 = new File("otro.txt");
        BufferedReader read = new BufferedReader(new FileReader(prueba));
        BufferedWriter wr = new BufferedWriter(new FileWriter(prueba2));

        //En caso de que el fichero no este vacio entra
        if (prueba.length() != 0) {
            caracter = 0;
            String cadena = "";
            //empezamos la lectura
            while (caracter != -1) {
                caracter = read.read();
                if (caracter == valor) {
                    cadena = cadena + (char) caracter;
                    cod = cogeCod(cadena);
                    //comprobamos si el registro existe
                    if (cod == o.getCodigoPokedex()) {
                        //entrara cuando haya dicho que quiere sobreescribir en la vista
                        if (actualizar) {
                            wr.write(o.toString());
                            wr.newLine();
                            existe = true;
                            cadena = "";
                            cont++;
                            actualizar = false;
                        } else {
                            //en caso de que exista el registro lanzamos la excepcion
                            read.close();
                            wr.close();
                            prueba2.delete();
                            throw new ExcepcionActuaizar();
                        }
                        //si no existe lo añade directamente al fichero                       
                    } else {
                        wr.write(cadena);
                        wr.newLine();
                        cadena = "";
                        cont++;
                    }
                } else {
                    cadena = cadena + (char) caracter;
                }
            }
            contador = cont;
            read.close();
            //si no hay actualizacion lo escribe
            if (!existe) {
                wr.write(o.toString());
                contador++;
            }
            wr.close();
            prueba.delete();
            prueba2.renameTo(new File("prueba.txt"));
            //si no hay nada escrito en el fichero lo escribe
        } else {
            wr.write(o.toString());
            contador++;
            wr.newLine();
            wr.close();
            read.close();
            prueba.delete();
            prueba2.renameTo(new File("prueba.txt"));
            existe = false;
        }
        if (existe == true) {
            consultaPorClave(o.getCodigoPokedex());

        } else {
            existe = false;
            ultimo();
        }

    }

    @Override
    public void darDeBaja(Pokemon p) throws IOException {
        if (p == null) {
            throw new NullPointerException();
        }
        File prueba = new File("prueba.txt");
        File prueba2 = new File("otro.txt");
        BufferedReader read = new BufferedReader(new FileReader(prueba));
        BufferedWriter wr = new BufferedWriter(new FileWriter(prueba2));
        int cont = 1;
        String cadena = "";

        while (cadena != null) {
            cadena = read.readLine();

            if (cadena != null) {
                String a1;
                StringTokenizer aux = new StringTokenizer(cadena, "#");
                try {
                    a1 = aux.nextToken();
                } catch (NoSuchElementException nos) {
                    a1 = (consultaPorClave(p.getCodigoPokedex())).getCodigoPokedex() + "";

                }

                if (a1.equals(p.getCodigoPokedex() + "")) {
                    contador--;
                    continue;
                } else {
                    wr.write(cadena);
                    wr.newLine();
                    cont++;
                    contador = cont;
                }

            } else {
                read.close();
                wr.close();

            }

        }

        read.close();
        wr.close();
        prueba.delete();
        prueba2.renameTo(new File("prueba.txt"));

    }

    @Override
    public Pokemon restablecer(Pokemon o) throws IOException {
        return consultaPorClave(o.getCodigoPokedex());
    }

    @Override
    public Pokemon consultaPorClave(int clave) throws IOException {
        File prueba = new File("prueba.txt");
        BufferedReader read = new BufferedReader(new FileReader(prueba));
        String cadena = "";
        int cont = 1;
        Pokemon a = null;
        int po;

        while (cadena != null) {
            cadena = read.readLine();
            if (cadena.equals("")) {
                continue;
            }
            cont++;
            a = setearPokemon(cadena);
            po = cogeCod(cadena);
            if (po == clave) {
                contador2 = cont;
                read.close();
                return a;
            }
        }
        read.close();
        return null;
    }

    @Override
    public Pokemon consultaPorNombre(String nombre) throws IOException {
        File prueba = new File("prueba.txt");
        BufferedReader read = new BufferedReader(new FileReader(prueba));
        String cadena = "";
        int cont = 1;
        Pokemon a = null;
        String po;

        while (cadena != null) {
            cadena = read.readLine();
            if (cadena.equals("")) {
                continue;
            }
            cont++;
            a = setearPokemon(cadena);
            po = cogeNom(cadena);
            if (po.equalsIgnoreCase(nombre)) {
                contador2 = cont;
                read.close();
                return a;
            }
        }
        read.close();
        return null;
    }

    @Override
    public Pokemon primero() throws IOException {
        File prueba = new File("prueba.txt");
        BufferedReader read = new BufferedReader(new FileReader(prueba));
        String cadena = "";
        int c;

        if (prueba.exists()) {
            c = 0;
            cadena = read.readLine();
        }
        read.close();
        contador2 = 2;
        return setearPokemon(cadena);

    }

    @Override
    public Pokemon ultimo() throws IOException {
        File prueba = new File("prueba.txt");
        BufferedReader read = new BufferedReader(new FileReader(prueba));
        String cadena = "";
        String aux = "";
        int cont;

        if (prueba.exists()) {
            cont = 1;
            while (cadena != null) {
                aux = cadena;
                cadena = read.readLine();
                if (!aux.equals("")) {
                    cont++;
                }
            }
            contador2 = cont;
            read.close();
        }
        return setearPokemon(aux);

    }

    @Override
    public Pokemon siguiente() throws IOException {
        try {
            File prueba = new File("prueba.txt");
            BufferedReader read = new BufferedReader(new FileReader(prueba));
            String cadena = "";

            if (prueba.exists()) {
                int cont = 1;
                while (cont != contador2 + 1) {
                    cadena = read.readLine();
                    try {
                        if (!cadena.equals("")) {
                            cont++;
                        }
                    } catch (NullPointerException npe) {
                        read.close();
                        return ultimo();
                    }
                }
                read.close();
            }
            contador2++;
            return setearPokemon(cadena);
        } catch (Exception e) {
            return ultimo();
        }
    }

    @Override
    public Pokemon anterior() throws IOException {
        try {
            File prueba;
            BufferedReader read;
            String cadena = "";
            int cont;
            if (contador2 == 2) {
                return primero();
            }

            prueba = new File("prueba.txt");
            read = new BufferedReader(new FileReader(prueba));

            if (prueba.exists()) {
                cont = 1;

                while (cont != contador2 - 1) {

                    cadena = read.readLine();
                    if (!cadena.equals("")) {
                        cont++;
                    }
                }
                read.close();

            }

            contador2--;
            return setearPokemon(cadena);
        } catch (Exception e) {
            return primero();
        }

    }

    @Override
    public int getContador() throws IOException {
        return contador;
    }

    private Pokemon setearPokemon(String cadena) throws IOException {
        String[] poke = cadena.split("#");
        Pokemon aux2 = new Pokemon(Integer.parseInt(poke[1]), poke[2], poke[3],
                Integer.parseInt(poke[4]), poke[5], poke[6], poke[7], poke[8],
                Boolean.parseBoolean(poke[9]), Boolean.parseBoolean(poke[10]),
                new Ataque(poke[11], poke[12]), new Ataque(poke[11], poke[12]),
                new Ataque(poke[11], poke[12]), new Ataque(poke[11], poke[12]),
                null);

        return aux2;
    }

    @Override
    public void setActualizacion(boolean act) {
        actualizar = act;
    }

    private int cogeCod(String cadena) {
        String[] a = cadena.split("#");
        return Integer.parseInt(a[1]);
    }

    private String cogeNom(String cadena) {
        String[] a = cadena.split("#");
        return a[2];
    }

}
