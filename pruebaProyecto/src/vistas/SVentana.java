/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.*;
import clases.Ataque;
import clases.ExcepcionActuaizar;
import controlador.Controlador;
import clases.Pokemon;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guiller
 */
public class SVentana extends JFrame implements IVentana {

    //propiedades
    private JFrame frame;
    private JLabel JLLogo, JLVida, JLObjetospecial, JLNumEvoluciones,
            JLZonaAparicion, icono, JLCod, JLNombre, JLTipo, JLGenero;

    private JTextField JTObjetoEspecial, JTCod, JTNombre, JTZonaAparicion,
            JTAtaque1, JTAtaque2, JTAtaque3, JTAtaque4, JTPorCodigo, JTPorNombre;

    private JList<String> generacion;
    private JComboBox<String> JCTipo;
    private DefaultComboBoxModel<String> tipos;
    private JCheckBox shiny;
    private JRadioButton JRMasculino, JRFemenino;
    private JSpinner JSNumEvoluciones, JSVida, JSPsAtaque1, JSPsAtaque2,
            JSPsAtaque3, JSPsAtaque4;

    private JPanel JPMenuSuperior, JPPanelDerecho, JPPanelIzquierdoUnion,
            JPPanelIzquierdoAbajo, JPPanelIzquierdoArriba, JPLogo, JPInfo,
            JPInfoIzquierda, JPPanelDerechaArriba, JPPanelDerechaAbajo,
            JPInfoDerecha, JPAtaques, JPNavegacionInferior, JPUnionParteIzqDch,
            JPBusqueda;

    private JButton JBcrear, JBborrar, JBBusqueda, JBGuardar, JBRestablecer,
            JBPrimero, JBAnterior, JBSiguiente, JBUltimo, JBCambiar, JBPorCodigo,
            JBPorNombre;

    private JTabbedPane JTPTab;
    private ImageIcon IICrear, IIBorrar, IIGuardar, IIRestablecer, IIBuscar,
            IIUlimo, IIAnterior, IISiguiente, IIPrimero, IILogo;

    private ButtonGroup BGGenero;
    private Controlador controlador;
    private int instruccion;
    private String[] datos;
    private int cp;
    private boolean actualizacion = false;

    public SVentana() {
        instanciarObjetos();
        construyeParteSuperior();
        construyeParteCentral();
        construyendoParteInferior();
        construyendoPieDePagina();
        construyeVentana();
        JBcrear.doClick();

    }

    
    //empezamos a instanciar todos los objetos
    
    
    private void instanciarObjetos() {
        instanciarCombo();
        instanciarLabels();
        instanciarTextFields();
        instanciarJButtons();
        instanciarImageIcons();
        instanciarJSpinners();
        instanciarJPanels();
        instanciarElResto();
    }

    private void instanciarCombo() {

        tipos = new DefaultComboBoxModel();
        tipos.addElement("ACERO");
        tipos.addElement("AGUA");
        tipos.addElement("BICHO");
        tipos.addElement("DRAGON");
        tipos.addElement("ELECTRICO");
        tipos.addElement("FANTASMA");

        tipos.addElement("FUEGO");
        tipos.addElement("HADA");
        tipos.addElement("HIELO");

        tipos.addElement("LUCHA");
        tipos.addElement("NORMAL");
        tipos.addElement("PLANTA");

        tipos.addElement("PSIQUICO");
        tipos.addElement("ROCA");
        tipos.addElement("SINIESTRO");
        tipos.addElement("TIERRA");
        tipos.addElement("VENENO");
        tipos.addElement("VOLADOR");
        JCTipo = new JComboBox<>(tipos);

    }

    private void instanciarLabels() {

        JLVida = new JLabel();
        JLObjetospecial = new JLabel();
        JLNumEvoluciones = new JLabel();
        JLZonaAparicion = new JLabel();
        JLCod = new JLabel();
        JLNombre = new JLabel();
        JLTipo = new JLabel();

        JLLogo = new JLabel();
        JLGenero = new JLabel();
        icono = new JLabel();
    }

    private void instanciarTextFields() {
        JTObjetoEspecial = new JTextField();
        JTCod = new JTextField();
        JTNombre = new JTextField();
        JTAtaque1 = new JTextField();
        JTAtaque2 = new JTextField();
        JTAtaque3 = new JTextField();
        JTAtaque4 = new JTextField();
        JTZonaAparicion = new JTextField();
        JTPorCodigo = new JTextField();
        JTPorNombre = new JTextField();
    }

    private void instanciarJButtons() {
        JBcrear = new JButton();
        JBborrar = new JButton();
        JBGuardar = new JButton();
        JBRestablecer = new JButton();
        JBBusqueda = new JButton();
        JBPrimero = new JButton();
        JBAnterior = new JButton();
        JBSiguiente = new JButton();
        JBUltimo = new JButton();
        JBCambiar = new JButton();
        JBPorCodigo = new JButton();
        JBPorNombre = new JButton();
    }

    private void instanciarImageIcons() {
        IICrear = new ImageIcon(getClass().getResource("/herramientas/crear.png"));
        IIBorrar = new ImageIcon(getClass().getResource("/herramientas/borrar.png"));
        IIGuardar = new ImageIcon(getClass().getResource("/herramientas/guardar.jpg"));
        IIRestablecer = new ImageIcon(getClass().getResource("/herramientas/restablecer.png"));
        IIBuscar = new ImageIcon(getClass().getResource("/herramientas/lupa.png"));
        IIUlimo = new ImageIcon(getClass().getResource("/herramientas/ultimo.png"));
        IISiguiente = new ImageIcon(getClass().getResource("/herramientas/siguiente.png"));
        IIAnterior = new ImageIcon(getClass().getResource("/herramientas/anterior.png"));
        IIPrimero = new ImageIcon(getClass().getResource("/herramientas/primero.png"));
        IILogo = new ImageIcon(getClass().getResource("/imagenes/pokemon.png"));
    }

    private void instanciarJSpinners() {
        JSNumEvoluciones = new JSpinner();
        JSVida = new JSpinner();
        JSPsAtaque1 = new JSpinner();
        JSPsAtaque2 = new JSpinner();
        JSPsAtaque3 = new JSpinner();
        JSPsAtaque4 = new JSpinner();
    }

    private void instanciarJPanels() {
        JPLogo = new JPanel();
        JPNavegacionInferior = new JPanel();
        JPPanelDerecho = new JPanel();
        JPPanelIzquierdoUnion = new JPanel();
        JPPanelIzquierdoAbajo = new JPanel();
        JPPanelIzquierdoArriba = new JPanel();
        JPInfoIzquierda = new JPanel();
        JPPanelDerechaArriba = new JPanel();
        JPPanelDerechaAbajo = new JPanel();
        JPInfoDerecha = new JPanel();
        JPInfo = new JPanel();
        JPAtaques = new JPanel();
        JPMenuSuperior = new JPanel();
        JPUnionParteIzqDch = new JPanel();
        JPBusqueda = new JPanel();
    }

    private void instanciarElResto() {
        JRMasculino = new JRadioButton();
        JRFemenino = new JRadioButton();
        shiny = new JCheckBox();
        BGGenero = new ButtonGroup();
        JTPTab = new JTabbedPane();
        cp = -1;

    }

    
    //añadimos los objetos necesarios a cada panel
    
    
    private void construyeParteSuperior() {
        JPLogo.setLayout(new FlowLayout());
        Insets margen = new Insets(0, 0, 0, 0);

        JLLogo.setIcon(IILogo);
        JPLogo.add(JLLogo);

        JPMenuSuperior.setLayout(new FlowLayout());

        JBcrear.setIcon(IICrear);
        JBborrar.setIcon(IIBorrar);
        JBGuardar.setIcon(IIGuardar);
        JBRestablecer.setIcon(IIRestablecer);
        JBBusqueda.setIcon(IIBuscar);

        JBcrear.setMargin(margen);
        JBborrar.setMargin(margen);
        JBGuardar.setMargin(margen);
        JBRestablecer.setMargin(margen);
        JBBusqueda.setMargin(margen);

        JPMenuSuperior.add(JBcrear);
        JPMenuSuperior.add(JBborrar);
        JPMenuSuperior.add(JBGuardar);
        JPMenuSuperior.add(JBRestablecer);
        JPMenuSuperior.add(JBBusqueda);
        acciones();

    }

    private void construyeParteCentral() {
        construyePartelIzquierda();
        construyeParteDerecha();
        JPUnionParteIzqDch.setLayout(new GridLayout(1, 2));
        JPUnionParteIzqDch.add(JPPanelIzquierdoUnion);
        JPUnionParteIzqDch.add(JPPanelDerecho);
    }

    private void construyePartelIzquierda() {

        JPPanelIzquierdoUnion.setLayout(new BorderLayout());
        JPPanelIzquierdoArriba.setLayout(new FlowLayout());
        JPPanelIzquierdoAbajo.setLayout(new FlowLayout());
        ImageIcon funciona = new ImageIcon(getClass().getResource("/imagenes/bloqueado.png"));
        icono.setIcon(funciona);
        JBCambiar.setText("Cambiar imagen");
        JPPanelIzquierdoArriba.add(icono);
        JPPanelIzquierdoAbajo.add(JBCambiar);
        JPPanelIzquierdoUnion.add(JPPanelIzquierdoArriba, BorderLayout.NORTH);
        JPPanelIzquierdoUnion.add(JPPanelIzquierdoAbajo, BorderLayout.SOUTH);
        JBCambiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imagenAleatoria();
            }
        });

    }

    private void construyeParteDerecha() {

        JPPanelDerecho.setLayout(new GridLayout(4, 2, 8, 8));
        JLNombre.setText("Nombre");
        JLCod.setText("Cod.Pokèdex");
        JLTipo.setText("Tipo");
        JLNumEvoluciones.setText("Num.Evoluciones");

        JPPanelDerecho.add(JLCod);
        JPPanelDerecho.add(JLTipo);
        JPPanelDerecho.add(JTCod);
        JPPanelDerecho.add(JCTipo);
        JPPanelDerecho.add(JLNombre);
        JPPanelDerecho.add(JLNumEvoluciones);
        JPPanelDerecho.add(JTNombre);
        JPPanelDerecho.add(JSNumEvoluciones);

    }

    private void construyendoParteInferior() {
        JTPTab.addTab("Info", JPInfo);
        JTPTab.addTab("Ataques", JPAtaques);
        izquierdaInfo();
        derechaInfo();
        unirInfo();
        ataques();
    }

    private void izquierdaInfo() {
        datos = new String[]{"Primera generación", "Segunda generación", "Tercera generación",
            "Cuarta generación", "Quinta generación", "Sexta generación", "Septima generación"};
        generacion = new JList(datos);

        JPInfoIzquierda.setLayout(new BorderLayout());
        JPInfoIzquierda.add(generacion, BorderLayout.BEFORE_FIRST_LINE);

    }

    private void derechaInfo() {
        JPPanelDerechaArriba.setLayout(new GridLayout(4, 1));

        JLObjetospecial.setText("Objeto especial");
        JLZonaAparicion.setText("Zona de aparicion");
        JPPanelDerechaArriba.add(JLZonaAparicion);
        JPPanelDerechaArriba.add(JTZonaAparicion);
        JPPanelDerechaArriba.add(JLObjetospecial);
        JPPanelDerechaArriba.add(JTObjetoEspecial);

        JPPanelDerechaAbajo.setLayout(new GridLayout(3, 2));

        shiny.setText("Shiny");
        JRFemenino.setText("Femenino");
        JRMasculino.setText("Masculino");
        JLVida.setText("vida");
        JLGenero.setText("Genero");
        BGGenero.add(JRMasculino);
        BGGenero.add(JRFemenino);
        JPPanelDerechaAbajo.add(JLGenero);
        JPPanelDerechaAbajo.add(JLVida);
        JPPanelDerechaAbajo.add(JRFemenino);
        JPPanelDerechaAbajo.add(JSVida);
        JPPanelDerechaAbajo.add(JRMasculino);
        JPPanelDerechaAbajo.add(shiny);

    }

    private void unirInfo() {
        JPInfoDerecha.setLayout(new GridLayout(2, 1));
        JPInfoDerecha.add(JPPanelDerechaArriba);
        JPInfoDerecha.add(JPPanelDerechaAbajo);

        JPInfo.setLayout(new GridLayout(1, 2));
        JPInfo.add(JPInfoIzquierda);
        JPInfo.add(JPInfoDerecha);

    }

    private void ataques() {

        JPAtaques.setLayout(new GridLayout(4, 4, 5, 5));

        JPAtaques.add(new JLabel("Nombre"));
        JPAtaques.add(JTAtaque1);
        JPAtaques.add(new JLabel("Nombre"));
        JPAtaques.add(JTAtaque2);
        JPAtaques.add(new JLabel("PS"));
        JPAtaques.add(JSPsAtaque1);
        JPAtaques.add(new JLabel("PS"));
        JPAtaques.add(JSPsAtaque2);
        JPAtaques.add(new JLabel("Nombre"));
        JPAtaques.add(JTAtaque3);
        JPAtaques.add(new JLabel("Nombre"));
        JPAtaques.add(JTAtaque4);
        JPAtaques.add(new JLabel("PS"));
        JPAtaques.add(JSPsAtaque3);
        JPAtaques.add(new JLabel("PS"));
        JPAtaques.add(JSPsAtaque4);

    }

    private void construyendoPieDePagina() {
        JPNavegacionInferior.setLayout(new FlowLayout());
        Insets margen = new Insets(0, 0, 0, 0);

        JBPrimero.setIcon(IIPrimero);
        JBAnterior.setIcon(IIAnterior);
        JBSiguiente.setIcon(IISiguiente);
        JBUltimo.setIcon(IIUlimo);

        JBPrimero.setMargin(margen);
        JBAnterior.setMargin(margen);
        JBSiguiente.setMargin(margen);
        JBUltimo.setMargin(margen);

        JPNavegacionInferior.add(JBPrimero);
        JPNavegacionInferior.add(JBAnterior);
        JPNavegacionInferior.add(JBSiguiente);
        JPNavegacionInferior.add(JBUltimo);

    }

    
    //Preparando la ventana para que se muestre todos los componentes
    
    
    private void construyeVentana() {

        frame = new JFrame("POKEDEX");
        frame.getContentPane().setLayout(new GridLayout(5, 1));
        frame.getContentPane().add(JPLogo);
        frame.getContentPane().add(JPMenuSuperior);
        frame.getContentPane().add(JPUnionParteIzqDch);
        frame.getContentPane().add(JTPTab);
        frame.getContentPane().add(JPNavegacionInferior);

        Image icon = new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage();
        frame.setIconImage(icon);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();

    }

  
    //Todo lo que tienen que hacer los botones
    
    
    private void acciones() {
        JButtonCrear();
        JButtonBorrar();
        JButtonGuardar();
        JButtonRestablecer();
        JButtonBuscar();
        JButtonPrimero();
        JButtonAnterior();
        JButtonSiguiente();
        JButtonUltimo();
        JButtonPorCodigo();
        JButtonPorNombre();

    }

    private void JButtonCrear() {
        JBcrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTCod.setText("");
                JTNombre.setText("");
                JSNumEvoluciones.setValue(0);
                JTZonaAparicion.setText("");
                JTObjetoEspecial.setText("");
                JSVida.setValue(100);
                tipos.setSelectedItem("ELECTRICO");
                generacion.setToolTipText("");
                shiny.setSelected(false);
                JTAtaque1.setText("");
                JTAtaque2.setText("");
                JTAtaque3.setText("");
                JTAtaque4.setText("");
                JSPsAtaque1.setValue(25);
                JSPsAtaque2.setValue(25);
                JSPsAtaque3.setValue(25);
                JSPsAtaque4.setValue(25);
                JRFemenino.setSelected(true);
                Image img = new ImageIcon(getClass().getResource("/imagenes"
                        + "/bloqueado.png")).getImage();
                ImageIcon img2 = new ImageIcon(img.getScaledInstance(150, 157,
                        Image.SCALE_SMOOTH));
                icono.setIcon(img2);

            }
        });

    }

    private void JButtonBorrar() {
        JBborrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instruccion = 2;
                try {
                    controlador.notificacion();
                } catch (IOException ex) {
                    JBcrear.doClick();
                } catch (ExcepcionActuaizar ex) {
                    Logger.getLogger(SVentana.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NullPointerException nu) {
                    JBcrear.doClick();
                }
            }
        });

    }

    private void JButtonGuardar() {
        JBGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instruccion = 3;
                try {
                    controlador.notificacion();
                } catch (ExcepcionActuaizar ea) {
                    //Avisará que el registro existe
                    int resp = JOptionPane.showConfirmDialog(null, "Se sobrees"
                            + "cribiran los datos", "Alerta!",
                            JOptionPane.OK_CANCEL_OPTION);
                    if (resp == 0)opcionTrue();
                    else opcionFalse();
                    
                } catch (IOException ex) {
                    Logger.getLogger(SVentana.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NullPointerException nu) {
                    //en caso de no poner el codigo bien saltará esto
                    JOptionPane.showMessageDialog(null, "Codigo entero obligatorio");
                }
            }
        });
    }

    private void opcionTrue() {
        actualizacion = true;
        instruccion = 1;
        try {
            controlador.notificacion();

        } catch (IOException ex) {
            System.out.println("correcto");
        } catch (ExcepcionActuaizar ex) {
            Logger.getLogger(SVentana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void opcionFalse() {
        actualizacion = false;
        System.out.println("hace el else");
        instruccion = 4;
        try {
            controlador.notificacion();
        } catch (IOException ex) {
            Logger.getLogger(SVentana.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcepcionActuaizar ex) {
            Logger.getLogger(SVentana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void JButtonRestablecer() {
        JBRestablecer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instruccion = 4;
                try {
                    controlador.notificacion();
                } catch (IOException ex) {
                    Logger.getLogger(SVentana.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExcepcionActuaizar ex) {
                    Logger.getLogger(SVentana.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    private void JButtonBuscar() {
        JBBusqueda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame busqueda = new JFrame();
                JPBusqueda.setLayout(new GridLayout(2, 2));

                JBPorCodigo.setText("Por código");
                JBPorNombre.setText("Por nombre");
                JPBusqueda.add(JTPorCodigo);
                JPBusqueda.add(JTPorNombre);
                JPBusqueda.add(JBPorCodigo);
                JPBusqueda.add(JBPorNombre);
                busqueda.getContentPane().add(JPBusqueda);

                busqueda.pack();
                busqueda.setVisible(true);
                busqueda.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                busqueda.setLocation(500, 500);
            }
        });

    }

    private void JButtonPrimero() {

        JBPrimero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instruccion = 6;
                try {
                    controlador.notificacion();
                } catch (IOException ex) {
                    System.out.println("algo salio mal boton primero");
                } catch (ExcepcionActuaizar ex) {
                    Logger.getLogger(SVentana.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NullPointerException nu) {
                    JBcrear.doClick();
                }
            }
        });
    }

    private void JButtonAnterior() {
        JBAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instruccion = 7;
                try {
                    controlador.notificacion();
                } catch (IOException ex) {
                    Logger.getLogger(SVentana.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExcepcionActuaizar ex) {
                    Logger.getLogger(SVentana.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NullPointerException nu) {
                    JBcrear.doClick();
                }
            }
        });
    }

    private void JButtonSiguiente() {
        JBSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instruccion = 8;
                try {
                    controlador.notificacion();
                } catch (IOException ex) {
                    Logger.getLogger(SVentana.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExcepcionActuaizar ex) {
                    Logger.getLogger(SVentana.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ArrayIndexOutOfBoundsException aib) {
                    JBcrear.doClick();
                } catch (NullPointerException nu) {
                    JBcrear.doClick();
                }

            }
        });
    }

    private void JButtonUltimo() {
        JBUltimo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instruccion = 9;
                try {
                    controlador.notificacion();
                } catch (IOException ex) {
                    Logger.getLogger(SVentana.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExcepcionActuaizar ex) {
                    Logger.getLogger(SVentana.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ArrayIndexOutOfBoundsException nu) {
                    JBcrear.doClick();
                }
            }
        });
    }

    private void imagenAleatoria() {
        int i, cont = 0;
        do {
            i = (int) (Math.random() * 12);
            if (cont == 0) cont++;
             else  cp = i;
            
        } while (i == cp);

        Image img = new ImageIcon(getClass().getResource("/imagenes/bloqueado.png")).getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(150, 157, Image.SCALE_SMOOTH));

        switch (i) {
            case 0:
                img = new ImageIcon(getClass().getResource("/imagenes/pokemon4.png")).getImage();
                img2 = new ImageIcon(img.getScaledInstance(150, 157, Image.SCALE_SMOOTH));
                icono.setIcon(img2);
                break;
            case 1:
                img = new ImageIcon(getClass().getResource("/imagenes/pokemon5.png")).getImage();
                img2 = new ImageIcon(img.getScaledInstance(150, 157, Image.SCALE_SMOOTH));
                icono.setIcon(img2);
                break;
            case 2:
                img = new ImageIcon(getClass().getResource("/imagenes/pokemon2.png")).getImage();
                img2 = new ImageIcon(img.getScaledInstance(150, 157, Image.SCALE_SMOOTH));
                icono.setIcon(img2);

                break;

            default:
                continuaElSwitch(i, img, img2);
        }
    }

    private void continuaElSwitch(int i, Image img, ImageIcon img2) {
        switch (i) {
            case 3:
                img = new ImageIcon(getClass().getResource("/imagenes/pokemon3.jpg")).getImage();
                img2 = new ImageIcon(img.getScaledInstance(150, 157, Image.SCALE_SMOOTH));
                icono.setIcon(img2);
                break;
            case 4:
                img = new ImageIcon(getClass().getResource("/imagenes/pokemon6.png")).getImage();
                img2 = new ImageIcon(img.getScaledInstance(150, 157, Image.SCALE_SMOOTH));

                icono.setIcon(img2);
                break;
            case 5:
                img = new ImageIcon(getClass().getResource("/imagenes/pokemon7.png")).getImage();
                img2 = new ImageIcon(img.getScaledInstance(150, 157, Image.SCALE_SMOOTH));

                icono.setIcon(img2);
                break;
            case 6:
                img = new ImageIcon(getClass().getResource("/imagenes/pokemon8.png")).getImage();
                img2 = new ImageIcon(img.getScaledInstance(150, 157, Image.SCALE_SMOOTH));

                icono.setIcon(img2);
                break;

            default:
                continuaElSwitch2(i, img, img2);
        }
    }

    private void continuaElSwitch2(int i, Image img, ImageIcon img2) {
        switch (i) {
            case 7:
                img = new ImageIcon(getClass().getResource("/imagenes/pokemon9.png")).getImage();
                img2 = new ImageIcon(img.getScaledInstance(150, 157, Image.SCALE_SMOOTH));

                icono.setIcon(img2);
                break;
            case 8:
                img = new ImageIcon(getClass().getResource("/imagenes/picahu.png")).getImage();
                img2 = new ImageIcon(img.getScaledInstance(150, 157, Image.SCALE_SMOOTH));

                icono.setIcon(img2);
                break;
            case 9:
                img = new ImageIcon(getClass().getResource("/imagenes/pokemon10.png")).getImage();
                img2 = new ImageIcon(img.getScaledInstance(150, 157, Image.SCALE_SMOOTH));

                icono.setIcon(img2);
                break;
            case 10:
                img = new ImageIcon(getClass().getResource("/imagenes/pokemon11.png")).getImage();
                img2 = new ImageIcon(img.getScaledInstance(150, 157, Image.SCALE_SMOOTH));

                icono.setIcon(img2);
                break;
            case 11:
                img = new ImageIcon(getClass().getResource("/imagenes/pokemon12.png")).getImage();
                img2 = new ImageIcon(img.getScaledInstance(150, 157, Image.SCALE_SMOOTH));

                icono.setIcon(img2);
                break;
        }
    }

    private void JButtonPorCodigo() {

        JBPorCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instruccion = 5;
                try {
                    controlador.notificacion();
                } catch (IOException ex) {
                    Logger.getLogger(SVentana.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExcepcionActuaizar ex) {
                    Logger.getLogger(SVentana.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NullPointerException nu) {
                }

            }
        });
    }

    private void JButtonPorNombre() {

        JBPorNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instruccion = 10;
                try {
                    controlador.notificacion();
                } catch (IOException ex) {
                    Logger.getLogger(SVentana.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExcepcionActuaizar ex) {
                    Logger.getLogger(SVentana.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NullPointerException nu) {
                }
            }
        });
    }

    
    //metodos sobreescritos
    
    
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("/imagenes/icono.png"));

        return retValue;
    }

    @Override
    public boolean getActualizacion() {
        return actualizacion;
    }
    
    @Override
    public int getClave() {
        return Integer.parseInt(JTPorCodigo.getText());
    }

    @Override
    public String getNombre() {
        return JTPorNombre.getText();
    }

    @Override
    public void setPokemon(Pokemon po) throws NullPointerException {
        try {

            JTNombre.setText(po.getNombre());
            JTCod.setText(po.getCodigoPokedex() + "");
            JTZonaAparicion.setText(po.getZonaDeAparicion() + "");
            JTObjetoEspecial.setText(po.getObjetoEspecial() + "");
            tipos.setSelectedItem(po.getTipo() + "");
            generacion.setSelectedIndex(po.getGeneracion());
            JSNumEvoluciones.setValue(po.getNumEvoluciones());
            JSVida.setValue(po.getVida());
            shiny.setSelected(po.getShiny());
            if (po.getGenero()) {
                JRFemenino.setSelected(true);
            } else {
                JRMasculino.setSelected(true);
            }
            JTAtaque1.setText(po.getAtaque1().getNombreAtaque());
            JTAtaque2.setText(po.getAtaque2().getNombreAtaque());
            JTAtaque3.setText(po.getAtaque3().getNombreAtaque());
            JTAtaque4.setText(po.getAtaque4().getNombreAtaque());
            JSPsAtaque1.setValue(po.getAtaque1().getPs());
            JSPsAtaque2.setValue(po.getAtaque2().getPs());
            JSPsAtaque3.setValue(po.getAtaque3().getPs());
            JSPsAtaque4.setValue(po.getAtaque4().getPs());
            icono.setIcon(po.getImagen());
            //en caso de que intenten poner un pokemon que no esta instanciado
        } catch (NullPointerException np) {
            JBcrear.doClick();
        }

    }

    @Override
    public Pokemon getPokemon() throws NumberFormatException {
        try {
            Pokemon p = new Pokemon(Integer.parseInt(JTCod.getText()),
                    JTNombre.getText() + "", (String) JCTipo.getSelectedItem() + "",
                    generacion.getAnchorSelectionIndex(), JTObjetoEspecial.getText() + "",
                    JTZonaAparicion.getText() + "", JSNumEvoluciones.getValue() + "",
                    JSVida.getValue() + "", shiny.isSelected(), JRFemenino.isSelected(),
                    new Ataque(JTAtaque1.getText(), JSPsAtaque1.getValue().toString()),
                    new Ataque(JTAtaque2.getText(), JSPsAtaque2.getValue() + ""),
                    new Ataque(JTAtaque3.getText(), JSPsAtaque3.getValue() + ""),
                    new Ataque(JTAtaque4.getText(), JSPsAtaque4.getValue() + ""),
                    icono.getIcon());

            return p;
        } catch (NumberFormatException n) {
            return null;

        }

    }

    @Override
    public void mostrar() {
        frame.setVisible(true);

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
