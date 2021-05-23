package modelos;

import clases.ExcepcionActuaizar;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.logging.Level;
import java.util.logging.Logger;
import clases.Pokemon;
import java.io.FileNotFoundException;
import java.io.InvalidClassException;

/**
 *
 * @author Guiller
 */
public class FicherosBinariosModelo implements IModelo {

    private int contador;
    private File fichero;
    private boolean actualizar;
    private int contador2;
    private boolean com = false;
    //condador lleva la cuenta de cuantos pokemon se dan de alta
    //contador2 indica en que posicion esta en el fichero
    public FicherosBinariosModelo() {
    }

    @Override
    public void darDeAlta(Pokemon p) throws IOException, ExcepcionActuaizar {
        if (p == null) throw new NullPointerException();
        //lanza null cuando no es un codigo valido
        
        Pokemon pokemon;
        File fichero1 = new File("auxiliar.dat");
        File fichero2 = new File("Fichero.dat");
        fichero2.createNewFile();

        //en caso de que el fichero vacio escribiremos directamente en el
        if (fichero2.length() == 0) {
            ObjectOutputStream a = new ObjectOutputStream(new FileOutputStream(fichero2, true));
            a.writeObject(p);
            a.close();
            contador++;
            return;
        }
        
        ObjectInputStream original = new ObjectInputStream(new FileInputStream(fichero2));
        ObjectOutputStream temporal = new ObjectOutputStream(new FileOutputStream(fichero1, true));
        try {
            int i = 1;
            while (true) {
                /*en caso de que exista el registro lanzaremos una excepcion,
                la cual informará al usuario de si quieres sobreescribir*/
                pokemon = (Pokemon) original.readObject();
                if (pokemon.getCodigoPokedex() == p.getCodigoPokedex()) {
                    if (actualizar) {
                        com = true;
                        temporal.writeObject(p);
                        actualizar = false;
                    } else {
                        temporal.close();
                        original.close();
                        fichero1.delete();
                        throw new ExcepcionActuaizar();
                    }
                //si no existe el registro lo escribimos directamente
                } else {
                    temporal.writeObject(pokemon);
                }
                i++;
                contador = i;
            }
        } catch (EOFException eo) {
            if (com == false) {
                temporal.writeObject(p);
                contador2 = ultimoNumero() + 1;
            }
            temporal.close();
            original.close();
            fichero2.delete();
            fichero1.renameTo(fichero2);//auxiliar lo pasamos al fichero original        
        } catch (ClassNotFoundException ex) {}
    }

    @Override
    public void darDeBaja(Pokemon p) throws IOException {
        Pokemon auxiliar;
        File fichero1 = new File("auxiliar.dat");
        File fichero2 = new File("Fichero.dat");
        ObjectInputStream original = new ObjectInputStream(new FileInputStream(fichero2));
        ObjectOutputStream temporal = new ObjectOutputStream(new FileOutputStream(fichero1, true));

        try {
            while (true) {
                auxiliar = (Pokemon) original.readObject();
                /*En el caso de que el pokemon auxiliar coincida con el pokemon 
                que nos han davuelto, nos saltaremos la escritura de el mismo*/
                if (auxiliar.getCodigoPokedex() == p.getCodigoPokedex()) {
                    contador--;
                    continue;
                } else {
                    temporal.writeObject(auxiliar);
                }
            }
        } catch (EOFException eo) {
            temporal.close();
            original.close();
            fichero2.delete();
            fichero1.renameTo(fichero2);

        } catch (FileNotFoundException ex) {
            temporal.close();
            fichero1.renameTo(fichero2);
        } catch (ClassNotFoundException ex) {
        } catch (NullPointerException nul) {
            temporal.close();
            original.close();
            fichero2.delete();
            fichero1.renameTo(fichero2);
        }
    }

    @Override
    public Pokemon restablecer(Pokemon p) throws IOException {
        return consultaPorClave(p.getCodigoPokedex());
    }

    @Override
    public Pokemon consultaPorClave(int clave) throws IOException {
        Pokemon p;
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream("Fichero.dat"));
        int i = 1;

        try {
            while (true) { //si coincide la clave devolveremos el pokemon
                p = (Pokemon) dataIS.readObject();
                i++;
                if (p.getCodigoPokedex() == clave) {
                    contador2 = i;
                    dataIS.close();
                    return p;
                }
            }
        } catch (EOFException eo) {
            dataIS.close();
        } catch (StreamCorruptedException x) {
            dataIS.close();
        } catch (ClassNotFoundException ex) {
            dataIS.close();
        }
        dataIS.close();
        return null;
    }

    @Override
    public Pokemon consultaPorNombre(String nombre) throws IOException {
        Pokemon p;
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream("Fichero.dat"));
        int i = 1;

        try {
            while (true) {
                p = (Pokemon) dataIS.readObject();
                i++;
                
                if (p.getNombre().equalsIgnoreCase(nombre)) {
                    contador2 = i;
                    dataIS.close();
                    return p;
                }
            }
        } catch (EOFException eo) {
            dataIS.close();
        } catch (StreamCorruptedException x) {
            dataIS.close();
        } catch (ClassNotFoundException ex) {
            dataIS.close();
        }
        dataIS.close();
        return null;
    }

    @Override
    public Pokemon primero() throws IOException {
        fichero = new File("Fichero.dat");
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(fichero));
        Pokemon p = null;
        int i = 1;
        //cogemos el primer pokemon que se encuentre escrito
        while (i < 2) {
            try {
                p = (Pokemon) dataIS.readObject(); 
                i++;
            } catch (InvalidClassException b){
            dataIS.close();
             File filo=new File("Fichero.dat");
            filo.createNewFile();
            filo.delete();
        }catch (ClassNotFoundException ex) {
            } catch (EOFException eo) {
                dataIS.close();
                return p;
            }
        }
        dataIS.close();
        contador2 = 2;
        return p;
    }

    @Override
    public Pokemon ultimo() throws IOException {
        ObjectInputStream dataIS;
        try{
         dataIS = new ObjectInputStream(new FileInputStream("Fichero.dat"));
        }catch (FileNotFoundException a){
            
            return null;
        }

        Pokemon po = null;
        int i = 1;

        try {
            while (true) {
                po = (Pokemon) dataIS.readObject(); 
                i++;
                contador2 = i;
            }
        } catch (EOFException a) {
            dataIS.close();
        }catch (InvalidClassException b){
            dataIS.close();
             File filo=new File("Fichero.dat");
            filo.createNewFile();
            filo.delete();
        }catch (ClassNotFoundException ex) {
            dataIS.close();
            return null;
        }         dataIS.close();
        return po;
    }

    @Override
    public Pokemon siguiente() throws IOException {
        ObjectInputStream dataIS;
        Pokemon e;
        int i;
        
        if (contador2 == ultimoNumero()) {
            return ultimo();
        }
        try{
        dataIS = new ObjectInputStream(new FileInputStream("Fichero.dat"));
        }catch (FileNotFoundException a){
            
            return null;
        }
        e = null;
        i = 1;

        while (i != contador2 + 1) {
            try {
                e = (Pokemon) dataIS.readObject();
                i++;
            } catch (ClassNotFoundException ex) {
                dataIS.close();
                return null;
            } catch (EOFException eo) {
                dataIS.close();
            } catch (IOException io){}
        }
        dataIS.close();
        contador2++;
        return e;
    }

    @Override
    public Pokemon anterior() {
        Pokemon e = null;
        ObjectInputStream dataIS;
        int i;
        try {
            if (contador2 == 2) {
                return primero();
            }

            dataIS = new ObjectInputStream(new FileInputStream("Fichero.dat"));

            i = 1;

            while (i != contador2 - 1) {
                try {
                    e = (Pokemon) dataIS.readObject();
                    i++;
                } catch (ClassNotFoundException ex) {
                    dataIS.close();
                } catch (EOFException eo) {
                    dataIS.close();
                } catch (IOException io) {
                    dataIS.close();
                    return primero();
                }
            }
            dataIS.close();
            contador2--;
        } catch (IOException io) {}
        return e;
    }

    @Override
    public int getContador() {
        return contador;
    }

    private int ultimoNumero() throws IOException {
        ObjectInputStream dataIS;
        
                try{
        dataIS = new ObjectInputStream(new FileInputStream("Fichero.dat"));
        }catch (FileNotFoundException a){
            
            return 0;
        }
        Pokemon e = null;
        int i = 1;

        try {
            while (true) {
                e = (Pokemon) dataIS.readObject(); // leer una Persona
                i++;
            }
        } catch (EOFException a) {
            dataIS.close();
        } catch (InvalidClassException b){
            dataIS.close();
             File filo=new File("Fichero.dat");
            filo.createNewFile();
            filo.delete();
        }
        catch (ClassNotFoundException ex) {
            dataIS.close();
            
        } 
        dataIS.close();
        return i;
    }

    @Override
    public void setActualizacion(boolean act) {
        actualizar = act;
    }

}
