/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.checkin.ui;

import com.mycompany.checkin.logic.SesionService;
import com.mycompany.checkin.model.Inscripcion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author grabe
 */
public class CheckInGUI extends javax.swing.JFrame {


    private final SesionService service;
    private final JTextField nombreField = new JTextField(20);
    private final JTextField documentoField = new JTextField(20);
    private final JComboBox<String> cursoCombo = new JComboBox<>(new String[] {"Prog 1", "Prog 2", "Base de Datos"});
    private final DefaultTableModel tableModel = new DefaultTableModel(new String[] {"Nombre", "Documento", "Curso", "Hora"}, 0);
    private final JTable tabla = new JTable(tableModel);

    public CheckInGUI(SesionService service) {
        super("Check-in Aula - GUI");
        this.service = service;
        setLayout(new BorderLayout());

        // Panel formulario
        JPanel formPanel = new JPanel(new GridLayout(4,2,5,5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(nombreField);
        formPanel.add(new JLabel("Documento:"));
        formPanel.add(documentoField);
        formPanel.add(new JLabel("Curso:"));
        formPanel.add(cursoCombo);

        JButton registrarBtn = new JButton("Registrar");
        formPanel.add(new JLabel()); // espacio vacío
        formPanel.add(registrarBtn);

        add(formPanel, BorderLayout.NORTH);

        // Panel tabla
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        registrarBtn.addActionListener(e -> {
            String nombre = nombreField.getText().trim();
            String documento = documentoField.getText().trim();
            String curso = (String) cursoCombo.getSelectedItem();
            /*if(!nombre.equals("")&&!documento.equals("")&&!curso.equals("")){
                service.registrar(nombre, documento, curso);
                actualizarTabla();
            }*/
            service.registrar(nombre, documento, curso);
            actualizarTabla();
           
            // limpiar campos
            nombreField.setText("");
            documentoField.setText("");
            cursoCombo.setSelectedIndex(0);
        });

        actualizarTabla();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        List<Inscripcion> inscripciones = service.listar();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (Inscripcion i : inscripciones) {
            tableModel.addRow(new Object[] {
                    i.getNombre(),
                    i.getDocumento(),
                    i.getCurso(),
                    i.getFechaHora().format(formatter)
            });
        }
    }

    public static void show(SesionService service) {
        SwingUtilities.invokeLater(() -> {
            CheckInGUI ventana = new CheckInGUI(service);
            ventana.setVisible(true);
        });
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
