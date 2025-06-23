package jdialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Celda;
import clases.Veterinario;
import clases.Zoologico;
import clases.Animal;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import enumes.Alimentacion;

import javax.swing.DefaultComboBoxModel;

import runner.Zoo;

public class CrearVeterinario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Zoologico controlador;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox<Celda> comboBoxCelda;
	private JComboBox<Alimentacion> comboBoxEspecialidades;
	private JButton okButton;
	private Zoo ventanaPrincipal;


	public CrearVeterinario(Zoologico controlador, Zoo ventanaPrincipal) {
		this.controlador = controlador;
		this.ventanaPrincipal = ventanaPrincipal;

		setBounds(100, 100, 606, 559);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("NOMBRE");
			label.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
			label.setBounds(21, 93, 168, 26);
			contentPanel.add(label);
		}
		{
			JLabel lblCarnet = new JLabel("CARNET");
			lblCarnet.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
			lblCarnet.setBounds(288, 93, 168, 26);
			contentPanel.add(lblCarnet);
		}
		{
			JLabel label = new JLabel("CELDAS ATENDIDAS");
			label.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
			label.setBounds(288, 251, 216, 26);
			contentPanel.add(label);
		}
		{
			JLabel lblEspecialidad = new JLabel("ESPECIALIDAD");
			lblEspecialidad.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
			lblEspecialidad.setBounds(21, 251, 168, 26);
			contentPanel.add(lblEspecialidad);
		}
		{
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(21, 141, 186, 32);
			contentPanel.add(textField);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(288, 141, 186, 32);
			contentPanel.add(textField_1);
		}
		{
			comboBoxEspecialidades = new JComboBox();
			comboBoxEspecialidades.setModel(new DefaultComboBoxModel(Alimentacion.values()));	
			comboBoxEspecialidades.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					actualizarComboBoxCeldas();
				}
			});

			{
				comboBoxCelda = new JComboBox();
				comboBoxCelda.setBounds(288, 298, 243, 32);
				contentPanel.add(comboBoxCelda);
			}
			actualizarComboBoxCeldas();

			comboBoxEspecialidades.setBounds(21, 298, 186, 32);
			comboBoxEspecialidades.setSelectedIndex(0);
			contentPanel.add(comboBoxEspecialidades);
		}


		setLocationRelativeTo(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("CREAR");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						crearVeterinario();
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
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void actualizarComboBoxCeldas() {
		Alimentacion seleccion = (Alimentacion) comboBoxEspecialidades.getSelectedItem();
		ArrayList<Celda> todas = controlador.getTodasLasCeldas();
		ArrayList<Celda> celdasFiltradas = new ArrayList<>();
		boolean hayAlgunaCeldaConAnimales = false;

		for (Celda c : todas) {
			if (c.tieneAnimales()) {
				hayAlgunaCeldaConAnimales = true;
				boolean tieneAnimalConAlimentacion = false;
				for (Animal a : c.getAnimales()) {
					if (a.getEspecie().getAlimentacion() == seleccion) {
						tieneAnimalConAlimentacion = true;
						break;
					}
				}
				if (tieneAnimalConAlimentacion) {
					celdasFiltradas.add(c);
				}
			}
		}

		if (!hayAlgunaCeldaConAnimales) {
			JOptionPane.showMessageDialog(this,
					"No existen celdas con animales en el zoológico.",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			dispose();
			return;
		}

		if (celdasFiltradas.isEmpty()) {
			JOptionPane.showMessageDialog(this,
					"No existen celdas con animales de tipo: " + seleccion,
					"Aviso",
					JOptionPane.WARNING_MESSAGE);
			comboBoxCelda.setModel(new DefaultComboBoxModel(new Celda[0]));
		} else {
			comboBoxCelda.setModel(new DefaultComboBoxModel<>(celdasFiltradas.toArray(new Celda[0])));
		}
	}



	public void crearVeterinario() {
		try {
			String nombre = textField.getText().trim();
			String carnet = textField_1.getText().trim();
			Alimentacion especialidad = (Alimentacion) comboBoxEspecialidades.getSelectedItem();

			if (comboBoxCelda.getItemCount() == 0) {
				JOptionPane.showMessageDialog(this,
						"No hay celdas disponibles para la especialidad seleccionada. Cree celdas primero.",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			Celda celdaSeleccionada = (Celda) comboBoxCelda.getSelectedItem();

			if (celdaSeleccionada == null) {
				JOptionPane.showMessageDialog(this,
						"Debe seleccionar una celda para asignar al veterinario.",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			Veterinario v = new Veterinario(nombre, carnet, especialidad);
			v.agregarCeldaAtencion(celdaSeleccionada);

			controlador.agregarTrabajador(v);
			ventanaPrincipal.actualizarResumen();
			ventanaPrincipal.actualizarTablaVeterinarios();

			JOptionPane.showMessageDialog(this, "Veterinario creado exitosamente.");
			dispose();

		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}