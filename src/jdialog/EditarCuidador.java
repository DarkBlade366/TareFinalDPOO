package jdialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import clases.Celda;
import clases.Cuidador;
import clases.Zoologico;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import runner.Zoo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import clases.Celda;
import clases.Cuidador;
import clases.Zoologico;
import runner.Zoo;

public class EditarCuidador extends JDialog {

	 private final JPanel contentPanel = new JPanel();
	    private Zoologico controlador;
	    private Zoo ventanaPrincipal;
	    private Cuidador cuidadorEditar;

	    private JTextField txtNombre;
	    private JTextField txtCarnet;
	    private JTextField txtHoras;
	    private JComboBox<Celda> comboBoxCelda1;
	    private JComboBox<Celda> comboBoxCelda2;
	    private JSpinner spinnerInicio1;
	    private JSpinner spinnerFin1;
	    private JSpinner spinnerInicio2;
	    private JSpinner spinnerFin2;

	    private ArrayList<Celda> celdasDisponibles;

	    public EditarCuidador(Zoologico controlador, final ArrayList<Celda> celdasDisponibles, Zoo ventanaPrincipal, Cuidador cuidador) {
	        this.controlador = controlador;
	        this.celdasDisponibles = celdasDisponibles;
	        this.ventanaPrincipal = ventanaPrincipal;
	        this.cuidadorEditar = cuidador;

	        setTitle("Editar Cuidador");
	        setBounds(100, 100, 606, 708);
	        getContentPane().setLayout(new BorderLayout());
	        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	        getContentPane().add(contentPanel, BorderLayout.CENTER);
	        contentPanel.setLayout(null);

	        JLabel lblNombre = new JLabel("NOMBRE");
	        lblNombre.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
	        lblNombre.setBounds(21, 21, 168, 26);
	        contentPanel.add(lblNombre);

	        JLabel lblCarnet = new JLabel("CARNET");
	        lblCarnet.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
	        lblCarnet.setBounds(305, 21, 168, 26);
	        contentPanel.add(lblCarnet);

	        JLabel lblCelda1 = new JLabel("CELDA 1");
	        lblCelda1.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
	        lblCelda1.setBounds(215, 110, 216, 26);
	        contentPanel.add(lblCelda1);

	        JLabel lblHoraInicio1 = new JLabel("HORA INICIO 1");
	        lblHoraInicio1.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
	        lblHoraInicio1.setBounds(21, 206, 234, 26);
	        contentPanel.add(lblHoraInicio1);

	        JLabel lblHoraFin1 = new JLabel("HORA FIN 1");
	        lblHoraFin1.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
	        lblHoraFin1.setBounds(305, 206, 254, 26);
	        contentPanel.add(lblHoraFin1);

	        JLabel lblCelda2 = new JLabel("CELDA 2");
	        lblCelda2.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
	        lblCelda2.setBounds(215, 297, 216, 26);
	        contentPanel.add(lblCelda2);

	        JLabel lblHoraInicio2 = new JLabel("HORA INICIO 2");
	        lblHoraInicio2.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
	        lblHoraInicio2.setBounds(21, 397, 234, 26);
	        contentPanel.add(lblHoraInicio2);

	        JLabel lblHoraFin2 = new JLabel("HORA FIN 2");
	        lblHoraFin2.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
	        lblHoraFin2.setBounds(305, 398, 254, 26);
	        contentPanel.add(lblHoraFin2);

	        JLabel lblHoras = new JLabel("HORAS TRABAJADAS");
	        lblHoras.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
	        lblHoras.setBounds(21, 492, 234, 26);
	        contentPanel.add(lblHoras);

	        txtNombre = new JTextField(cuidador.getNombre());
	        txtNombre.setBounds(21, 68, 186, 32);
	        contentPanel.add(txtNombre);

	        txtCarnet = new JTextField(cuidador.getNumCarnet());
	        txtCarnet.setEnabled(false);
	        txtCarnet.setBounds(305, 68, 186, 32);
	        contentPanel.add(txtCarnet);

	        txtHoras = new JTextField(String.valueOf(cuidador.getHorasTrabajadas()));
	        txtHoras.setBounds(21, 539, 186, 32);
	        contentPanel.add(txtHoras);

	        comboBoxCelda1 = new JComboBox<Celda>();
	        comboBoxCelda2 = new JComboBox<Celda>();

	        actualizarModelosComboBox();

	        comboBoxCelda1.setBounds(94, 157, 337, 32);
	        contentPanel.add(comboBoxCelda1);
	        comboBoxCelda2.setBounds(94, 344, 337, 32);
	        contentPanel.add(comboBoxCelda2);

	        comboBoxCelda1.setRenderer(new DefaultListCellRenderer() {
	            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	                setText(value == null ? "Sin asignar" : value.toString());
	                return this;
	            }
	        });
	        comboBoxCelda2.setRenderer(comboBoxCelda1.getRenderer());

	        final boolean[] actualizando = {false};

	        comboBoxCelda1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (actualizando[0]) return;
	                actualizando[0] = true;
	                Celda seleccionada1 = (Celda) comboBoxCelda1.getSelectedItem();
	                comboBoxCelda2.setModel(crearModeloComboBox(celdasDisponibles, seleccionada1, cuidadorEditar.getCeldaAsignada2()));
	                comboBoxCelda2.setSelectedItem(cuidadorEditar.getCeldaAsignada2());
	                actualizando[0] = false;
	            }
	        });

	        comboBoxCelda2.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (actualizando[0]) return;
	                actualizando[0] = true;
	                Celda seleccionada2 = (Celda) comboBoxCelda2.getSelectedItem();
	                comboBoxCelda1.setModel(crearModeloComboBox(celdasDisponibles, seleccionada2, cuidadorEditar.getCeldaAsignada1()));
	                comboBoxCelda1.setSelectedItem(cuidadorEditar.getCeldaAsignada1());
	                actualizando[0] = false;
	            }
	        });
	        
	        int hInicio1 = cuidador.getHoraInicio1();
	        if (hInicio1 < 1 || hInicio1 > 24) hInicio1 = 1;
	        spinnerInicio1 = new JSpinner(new SpinnerNumberModel(hInicio1, 1, 24, 1));
	        spinnerInicio1.setBounds(31, 242, 168, 32);
	        contentPanel.add(spinnerInicio1);
	        
	        int hFin1 = cuidador.getHoraFin1();
	        if (hFin1 < 1 || hFin1 > 24) hFin1 = 24;
	        spinnerFin1 = new JSpinner(new SpinnerNumberModel(hFin1, 1, 24, 1));
	        spinnerFin1.setBounds(305, 242, 168, 32);
	        contentPanel.add(spinnerFin1);

	        int hInicio2 = cuidador.getHoraInicio2();
	        if (hInicio2 < 1 || hInicio2 > 24) hInicio2 = 1;
	        spinnerInicio2 = new JSpinner(new SpinnerNumberModel(hInicio2, 1, 24, 1));
	        spinnerInicio2.setBounds(21, 439, 168, 32);
	        contentPanel.add(spinnerInicio2);

	        int hFin2 = cuidador.getHoraFin2();
	        if (hFin2 < 1 || hFin2 > 24) hFin2 = 24;
	        spinnerFin2 = new JSpinner(new SpinnerNumberModel(hFin2, 1, 24, 1));
	        spinnerFin2.setBounds(305, 440, 168, 32);
	        contentPanel.add(spinnerFin2);
	        
	        setLocationRelativeTo(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("GUARDAR");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						guardarCambios();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("CANCEL");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	    private void actualizarModelosComboBox() {
	        comboBoxCelda1.setModel(crearModeloComboBox(celdasDisponibles, cuidadorEditar.getCeldaAsignada2(), cuidadorEditar.getCeldaAsignada1()));
	        comboBoxCelda2.setModel(crearModeloComboBox(celdasDisponibles, cuidadorEditar.getCeldaAsignada1(), cuidadorEditar.getCeldaAsignada2()));
	        comboBoxCelda1.setSelectedItem(cuidadorEditar.getCeldaAsignada1());
	        comboBoxCelda2.setSelectedItem(cuidadorEditar.getCeldaAsignada2());
	    }

	    private DefaultComboBoxModel<Celda> crearModeloComboBox(ArrayList<Celda> lista, Celda celdaExcluir, Celda celdaIncluir) {
	        DefaultComboBoxModel<Celda> modelo = new DefaultComboBoxModel<Celda>();
	        modelo.addElement(null);
	        for (Celda c : lista) {
	            if (!c.equals(celdaExcluir) || (celdaIncluir != null && c.equals(celdaIncluir))) {
	                modelo.addElement(c);
	            }
	        }
	        return modelo;
	    }

	    private void guardarCambios() {
	        try {
	            String nombre = txtNombre.getText().trim();
	            String carnet = txtCarnet.getText().trim();
	            if (nombre.isEmpty() || carnet.isEmpty()) {
	                throw new IllegalArgumentException("Nombre y Carnet no pueden estar vacíos.");
	            }

	            double horas = Double.parseDouble(txtHoras.getText().trim());

	            Celda celda1 = (Celda) comboBoxCelda1.getSelectedItem();
	            Celda celda2 = (Celda) comboBoxCelda2.getSelectedItem();

	            int inicio1 = ((Integer) spinnerInicio1.getValue()).intValue();
	            int fin1 = ((Integer) spinnerFin1.getValue()).intValue();
	            int inicio2 = celda2 != null ? ((Integer) spinnerInicio2.getValue()).intValue() : 0;
	            int fin2 = celda2 != null ? ((Integer) spinnerFin2.getValue()).intValue() : 0;

	            if (celda2 != null) {
	                if (inicio2 < 1 || inicio2 > 24 || fin2 < 1 || fin2 > 24) {
	                    throw new IllegalArgumentException("Horas de celda 2 deben estar entre 1 y 24.");
	                }
	                if (inicio2 >= fin2) {
	                    throw new IllegalArgumentException("Hora inicio debe ser menor que hora fin en celda 2.");
	                }
	                cuidadorEditar.setHoraInicio2(inicio2);
	                cuidadorEditar.setHoraFin2(fin2);
	            } else {
	                cuidadorEditar.setHoraInicio2(-1); 
	                cuidadorEditar.setHoraFin2(-1);
	            }
	            
	            if (celda1 == null) throw new IllegalArgumentException("Debe seleccionar al menos una celda.");
	            if (celda1.equals(celda2)) throw new IllegalArgumentException("Las celdas deben ser diferentes.");
	            if (inicio1 >= fin1) throw new IllegalArgumentException("Hora inicio debe ser menor que hora fin en celda 1.");
	            if (celda2 != null && inicio2 >= fin2) throw new IllegalArgumentException("Hora inicio debe ser menor que hora fin en celda 2.");

	            if (!celda1.puedeAgregarCuidadorExcluyendo(cuidadorEditar, inicio1, fin1))
	                throw new IllegalArgumentException("Horarios solapados en Celda 1.");
	            if (celda2 != null && !celda2.puedeAgregarCuidadorExcluyendo(cuidadorEditar, inicio2, fin2))
	                throw new IllegalArgumentException("Horarios solapados en Celda 2.");

	            cuidadorEditar.setNombre(nombre);
	            cuidadorEditar.setHorasTrabajadas(horas);

	            if (cuidadorEditar.getCeldaAsignada1() != null && !cuidadorEditar.getCeldaAsignada1().equals(celda1)) {
	                cuidadorEditar.getCeldaAsignada1().removerCuidador(cuidadorEditar);
	            }
	            if (cuidadorEditar.getCeldaAsignada2() != null && !cuidadorEditar.getCeldaAsignada2().equals(celda2)) {
	                cuidadorEditar.getCeldaAsignada2().removerCuidador(cuidadorEditar);
	            }

	            cuidadorEditar.setCeldaAsignada1(celda1);
	            cuidadorEditar.setHoraInicio1(inicio1);
	            cuidadorEditar.setHoraFin1(fin1);

	            cuidadorEditar.setCeldaAsignada2(celda2);
	            cuidadorEditar.setHoraInicio2(inicio2);
	            cuidadorEditar.setHoraFin2(fin2);

	            if (!celda1.getCuidadores().contains(cuidadorEditar)) {
	                celda1.agregarCuidador(cuidadorEditar);
	            }
	            if (celda2 != null && !celda2.getCuidadores().contains(cuidadorEditar)) {
	                celda2.agregarCuidador(cuidadorEditar);
	            }

	            ventanaPrincipal.actualizarTablaCuidadores();
	            ventanaPrincipal.actualizarTablaCeldas();
	            ventanaPrincipal.actualizarResumen();

	            JOptionPane.showMessageDialog(this, "Cuidador actualizado exitosamente.");
	            dispose();

	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }

}
