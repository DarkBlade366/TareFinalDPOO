package jdialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.Component;

import clases.Celda;
import clases.Cuidador;
import clases.Especie;
import clases.Zoologico;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import enumes.TipoEntorno;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.SpinnerNumberModel;

import runner.Zoo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearCuidador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Zoologico controlador;
	private JTextField textFieldNombre;
	private JTextField textFieldCarnet;
	private JTextField textFieldHoras;
	private JComboBox<Celda> comboBoxCelda1;
	private JComboBox<Celda> comboBoxCelda2;
	private JSpinner spinnerInicio1;
	private JSpinner spinnerFin1;
	private JSpinner spinnerInicio2;
	private JSpinner spinnerFin2;
	private ArrayList<Celda> celdasDisponibles;
	private Zoo ventanaPrincipal;

	public CrearCuidador(Zoologico controlador, final ArrayList<Celda> celdasDisponibles, Zoo ventanaPrincipal) {
		this.controlador = controlador;
		this.celdasDisponibles = celdasDisponibles;
		this.ventanaPrincipal = ventanaPrincipal;

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

		JLabel lblHorasTrabajadas = new JLabel("HORAS TRABAJADAS");
		lblHorasTrabajadas.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblHorasTrabajadas.setBounds(21, 492, 234, 26);
		contentPanel.add(lblHorasTrabajadas);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(21, 68, 186, 32);
		contentPanel.add(textFieldNombre);

		textFieldCarnet = new JTextField();
		textFieldCarnet.setBounds(305, 68, 186, 32);
		contentPanel.add(textFieldCarnet);

		textFieldHoras = new JTextField();
		textFieldHoras.setBounds(21, 539, 186, 32);
		contentPanel.add(textFieldHoras);

		comboBoxCelda1 = new JComboBox<Celda>();
		comboBoxCelda1.setModel(crearModeloComboBox(celdasDisponibles, null));
		comboBoxCelda1.setBounds(94, 157, 337, 32);
		contentPanel.add(comboBoxCelda1);

		comboBoxCelda2 = new JComboBox<Celda>();
		comboBoxCelda2.setModel(crearModeloComboBox(celdasDisponibles, null));
		comboBoxCelda2.setBounds(94, 344, 337, 32);
		contentPanel.add(comboBoxCelda2);

		comboBoxCelda1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Celda seleccionada1 = (Celda) comboBoxCelda1.getSelectedItem();
				Celda seleccionActual2 = (Celda) comboBoxCelda2.getSelectedItem();
				DefaultComboBoxModel<Celda> modelo2 = crearModeloComboBox(celdasDisponibles, seleccionada1);
				comboBoxCelda2.setModel(modelo2);
				if (seleccionActual2 != null && !seleccionActual2.equals(seleccionada1)) {
					comboBoxCelda2.setSelectedItem(seleccionActual2);
				}
			}
		});

		comboBoxCelda2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Celda seleccionada2 = (Celda) comboBoxCelda2.getSelectedItem();
				Celda seleccionActual1 = (Celda) comboBoxCelda1.getSelectedItem();
				DefaultComboBoxModel<Celda> modelo1 = crearModeloComboBox(celdasDisponibles, seleccionada2);
				comboBoxCelda1.setModel(modelo1);
				if (seleccionActual1 != null && !seleccionActual1.equals(seleccionada2)) {
					comboBoxCelda1.setSelectedItem(seleccionActual1);
				}
			}
		});

		comboBoxCelda1.setRenderer(new DefaultListCellRenderer() {
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				setText(value == null ? "Sin asignar" : value.toString());
				return this;
			}
		});
		comboBoxCelda2.setRenderer(comboBoxCelda1.getRenderer());

		spinnerInicio1 = new JSpinner(new SpinnerNumberModel(1, 0, 23, 1));
		spinnerInicio1.setBounds(31, 242, 168, 32);
		contentPanel.add(spinnerInicio1);

		spinnerFin1 = new JSpinner(new SpinnerNumberModel(24, 1, 24, 1));
		spinnerFin1.setBounds(305, 242, 168, 32);
		contentPanel.add(spinnerFin1);

		spinnerInicio2 = new JSpinner(new SpinnerNumberModel(1, 0, 23, 1));
		spinnerInicio2.setBounds(21, 439, 168, 32);
		contentPanel.add(spinnerInicio2);

		spinnerFin2 = new JSpinner(new SpinnerNumberModel(24, 1, 24, 1));
		spinnerFin2.setBounds(305, 440, 168, 32);
		contentPanel.add(spinnerFin2);

		if (celdasDisponibles.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No hay celdas disponibles para agregar cuidadores", "Atención", JOptionPane.WARNING_MESSAGE);
			dispose();
		}

		setLocationRelativeTo(null);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("CREAR");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						crearCuidador();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("CANCEL");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private DefaultComboBoxModel<Celda> crearModeloComboBox(ArrayList<Celda> lista, Celda celdaExcluir) {
		DefaultComboBoxModel<Celda> modelo = new DefaultComboBoxModel<Celda>();
		modelo.addElement(null);
		for (Celda c : lista) {
			if (!c.equals(celdaExcluir)) {
				modelo.addElement(c);
			}
		}
		return modelo;
	}

	private void crearCuidador() {
		try {
			String nombre = textFieldNombre.getText().trim();
			String carnet = textFieldCarnet.getText().trim();
			double horas = Double.parseDouble(textFieldHoras.getText().trim());

			if (nombre.isEmpty() || carnet.isEmpty())
				throw new IllegalArgumentException("Nombre y Carnet no pueden estar vacíos.");
			if (horas <= 0 || horas > 60)
				throw new IllegalArgumentException("Horas inválidas.");

			Celda celda1 = (Celda) comboBoxCelda1.getSelectedItem();
			Celda celda2 = (Celda) comboBoxCelda2.getSelectedItem();

			int inicio1 = ((Integer) spinnerInicio1.getValue()).intValue();
			int fin1 = ((Integer) spinnerFin1.getValue()).intValue();
			int inicio2 = celda2 != null ? ((Integer) spinnerInicio2.getValue()).intValue() : 0;
			int fin2 = celda2 != null ? ((Integer) spinnerFin2.getValue()).intValue() : 0;

			if (celda1 == null)
				throw new IllegalArgumentException("Debe seleccionar al menos una celda.");
			if (celda1.equals(celda2))
				throw new IllegalArgumentException("Las celdas deben ser diferentes.");
			if (inicio1 >= fin1)
				throw new IllegalArgumentException("Hora inicio debe ser menor que hora fin en celda 1.");
			if (celda2 != null && inicio2 >= fin2)
				throw new IllegalArgumentException("Hora inicio debe ser menor que hora fin en celda 2.");
			if (!celda1.puedeAgregarCuidador(null, inicio1, fin1))
				throw new IllegalArgumentException("Horarios solapados en Celda 1.");
			if (celda2 != null && !celda2.puedeAgregarCuidador(null, inicio2, fin2))
				throw new IllegalArgumentException("Horarios solapados en Celda 2.");

			Cuidador nuevo = new Cuidador(nombre, carnet, horas, inicio1, fin1, inicio2, fin2);
			nuevo.setCeldaAsignada1(celda1);
			nuevo.setCeldaAsignada2(celda2);

			celda1.agregarCuidador(nuevo);
			if (celda2 != null) {
				celda2.agregarCuidador(nuevo);
			}

			controlador.agregarCuidador(nuevo);
			ventanaPrincipal.actualizarTablaCuidadores();
			ventanaPrincipal.actualizarResumen();
			ventanaPrincipal.actualizarTablaCeldas();

			JOptionPane.showMessageDialog(this, "Cuidador creado exitosamente.");
			dispose();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
