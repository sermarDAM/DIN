/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import model.Cantantes;
import model.Controller;

/**
 *
 * @author sermar
 */
public class frame extends JFrame {

    private JPanel panel_princ = new JPanel();
    private JPanel panel_text = new JPanel();
    private JPanel panel_boton = new JPanel();
    private JButton bAña, bBorr, bMost, bAct;
    private JLabel nomR, nomArt, prem, discos, fecha_nac, fecha_fall;
    private JTextField tnomR, tnomArt, tprem, tdiscos, tfecha_nac, tfecha_fall;
    private JTable tabla = new JTable();
    private DefaultTableModel jTabla1 = new DefaultTableModel();
    private JDialog ventanasec = new JDialog();
    private List<Cantantes> cantantes = new ArrayList<>();
    private Controller c = new Controller();
    private int ID;

    public frame(Controller control) {
        setSize(700, 500);
        setTitle("Cantantes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel_princ.setLayout(new BorderLayout());
        panel_text.setLayout(new GridLayout(6, 2, 10, 10));
        panel_boton.setLayout(new FlowLayout());

        Object[] tags = new Object[6];
        tags[0] = "Nombre Real";
        tags[1] = "Nombre Artista";
        tags[2] = "Premios";
        tags[3] = "Discos";
        tags[4] = "Fecha Nacimiento";
        tags[5] = "Fecha Fallecimiento";

        jTabla1.setColumnIdentifiers(tags);

        rellenarTabla();

        JScrollPane ScrollPanel = new JScrollPane(tabla);
        
        add(panel_princ);

        panel_princ.add(panel_text, BorderLayout.CENTER);
        panel_princ.add(panel_boton, BorderLayout.SOUTH);

        bAña = new JButton("Añadir Cantante");
        bBorr = new JButton("Eliminar Cantante");
        bMost = new JButton("Mostrar todos");
        bAct = new JButton("Actualizar");

        panel_boton.add(bAña);
        panel_boton.add(bBorr);
        panel_boton.add(bMost);
        panel_boton.add(bAct);

        nomR = new JLabel("Nombre Real del Cantante");
        tnomR = new JTextField(50);
        panel_text.add(nomR);
        panel_text.add(tnomR);

        nomArt = new JLabel("Nombre Artístico");
        tnomArt = new JTextField(50);
        panel_text.add(nomArt);
        panel_text.add(tnomArt);

        prem = new JLabel("Premios");
        tprem = new JTextField(50);
        panel_text.add(prem);
        panel_text.add(tprem);

        discos = new JLabel("Discos");
        tdiscos = new JTextField(50);
        panel_text.add(discos);
        panel_text.add(tdiscos);

        fecha_nac = new JLabel("Fecha de Nacimiento");
        tfecha_nac = new JTextField(50);
        panel_text.add(fecha_nac);
        panel_text.add(tfecha_nac);

        fecha_fall = new JLabel("Fecha de Fallecimiento");
        tfecha_fall = new JTextField(50);
        panel_text.add(fecha_fall);
        panel_text.add(tfecha_fall);

        tnomR.setEditable(false);
        tnomArt.setEditable(false);
        tprem.setEditable(false);
        tdiscos.setEditable(false);
        tfecha_nac.setEditable(false);
        tfecha_fall.setEditable(false);

        buttonsListener bl = new buttonsListener();
        bAña.addActionListener(bl);
        bBorr.addActionListener(bl);
        bMost.addActionListener(bl);
        bAct.addActionListener(bl);

        tabla.setModel(jTabla1);
        ventanasec = new JDialog();
        ventanasec.add(ScrollPanel);

    }

    class buttonsListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == bAña) {
                if (bAña.getText().equals("Añadir Cantante")) {
                    tnomR.setText("");
                    tnomArt.setText("");
                    tprem.setText("");
                    tdiscos.setText("");
                    tfecha_nac.setText("");
                    tfecha_fall.setText("");

                    bBorr.setEnabled(false);
                    bMost.setEnabled(false);
                    bAct.setEnabled(false);

                    bAña.setText("Añadiendo...");

                    tnomR.setEditable(true);
                    tnomArt.setEditable(true);
                    tprem.setEditable(true);
                    tdiscos.setEditable(true);
                    tfecha_nac.setEditable(true);
                    tfecha_fall.setEditable(true);

                } else {
                    bBorr.setEnabled(true);
                    bMost.setEnabled(true);
                    bAct.setEnabled(true);

                    tnomR.setEditable(false);
                    tnomArt.setEditable(false);
                    tprem.setEditable(false);
                    tdiscos.setEditable(false);
                    tfecha_nac.setEditable(false);
                    tfecha_fall.setEditable(false);

                    bAña.setText("Añadir Cantante");

                    String nomReal = tnomR.getText();
                    String nomCan = tnomArt.getText();
                    String premi = tprem.getText();
                    String disc = tdiscos.getText();
                    String fechn = tfecha_nac.getText();
                    String fechf = tfecha_fall.getText();

                    int premint = Integer.valueOf(premi);
                    int discint = Integer.valueOf(disc);

                    Cantantes cantante = new Cantantes(nomReal, nomCan, premint, discint, fechn, fechf);

                    try {
                        c.añadir(cantante);
                    } catch (SQLException ex) {
                    }

                }
                jTabla1.setRowCount(0);
                rellenarTabla();
            }

            if (e.getSource() == bBorr) {
                String iDs = JOptionPane.showInputDialog("Introduce id del artista");
                int ID = Integer.parseInt(iDs);

                Cantantes cantante = new Cantantes(ID);

                try {
                    c.eliminar(cantante);
                } catch (SQLException ex) {
                }
                jTabla1.setRowCount(0);
                rellenarTabla();
            }

            if (e.getSource() == bMost) {
                jTabla1.setRowCount(0);
                rellenarTabla();
                ventanasec.setVisible(true);

                ventanasec.setSize(800, 400);

            }

            if (e.getSource() == bAct) {
                if(bAct.getText().equals("Actualizar")){  
                    
                String iDs = JOptionPane.showInputDialog("Introduce id del artista");
                ID = Integer.parseInt(iDs);
                
                tnomR.setText("");
                tnomArt.setText("");
                tprem.setText("");
                tdiscos.setText("");
                tfecha_nac.setText("");
                tfecha_fall.setText("");

                bAña.setEnabled(false);
                bBorr.setEnabled(false);
                bMost.setEnabled(false);

                bAct.setText("Actualizando");

                tnomR.setEditable(true);
                tnomArt.setEditable(true);
                tprem.setEditable(true);
                tdiscos.setEditable(true);
                tfecha_nac.setEditable(true);
                tfecha_fall.setEditable(true);
            } else {
                    
                bAct.setText("Actualizar");
                bAña.setEnabled(true);
                bBorr.setEnabled(true);
                bMost.setEnabled(true);

                tnomR.setEditable(false);
                tnomArt.setEditable(false);
                tprem.setEditable(false);
                tdiscos.setEditable(false);
                tfecha_nac.setEditable(false);
                tfecha_fall.setEditable(false);

                String nomReal = tnomR.getText();
                String nomCan = tnomArt.getText();
                String premi = tprem.getText();
                String disc = tdiscos.getText();
                String fechn = tfecha_nac.getText();
                String fechf = tfecha_fall.getText();

                int premint = Integer.valueOf(premi);
                int discint = Integer.valueOf(disc);

                Cantantes cantante = new Cantantes(nomReal, nomCan, premint, discint, fechn, fechf, ID);
                    System.out.println(cantante.toString());
                            
                try {
                    c.actualizar(cantante);
                } catch (SQLException ex) {
                }

            }

            jTabla1.setRowCount(0);
            rellenarTabla();
            }
        }
    }

    public void rellenarTabla() {
        cantantes = c.verCantantes();
        for (int i = 0; i < cantantes.size(); i++) {
            Cantantes cantante = new Cantantes();
            cantante = cantantes.get(i);
            Object[] rowData = new Object[jTabla1.getColumnCount()];
            rowData[0] = cantante.getNombreReal();
            rowData[1] = cantante.getNombreArt();
            rowData[2] = cantante.getPremios();
            rowData[3] = cantante.getDiscos();
            rowData[4] = cantante.getFecha_nac();
            rowData[5] = cantante.getFecha_falle();
            jTabla1.addRow(rowData);
        }
    }
}
