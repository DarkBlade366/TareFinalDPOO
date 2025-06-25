package jdialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	private JTextField txtNombre;
	private JTextField txtCarnet;
	private JComboBox<Alimentacion> cbEspecialidad;
	private JComboBox<Celda> cbNuevaCelda;
	private DefaultListModel<Celda> listaModel;
	private JList<Celda> listCeldas;
	private Zoologico zoo;
	private Zoo ventanaPrincipal;


	public CrearVeterinario(Zoologico zoo, Zoo ventanaPrincipal) {
		this.zoo = zoo;
		this.ventanaPrincipal = ventanaPrincipal;

		setTitle("Crear Veterinario");
		setBounds(100, 100, 606, 559);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblNombre.setBounds(21, 42, 168, 26);
		contentPanel.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		txtNombre.setBounds(21, 80, 250, 32);
		contentPanel.add(txtNombre);

		JLabel lblCarnet = new JLabel("CARNET");
		lblCarnet.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblCarnet.setBounds(310, 42, 168, 26);
		contentPanel.add(lblCarnet);

		txtCarnet = new JTextField();
		txtCarnet.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		txtCarnet.setBounds(310, 80, 250, 32);
		contentPanel.add(txtCarnet);

		JLabel lblEspecialidad = new JLabel("ESPECIALIDAD");
		lblEspecialidad.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblEspecialidad.setBounds(21, 140, 200, 26);
		contentPanel.add(lblEspecialidad);

		cbEspecialidad = new JComboBox<Alimentacion>();
		cbEspecialidad.setModel(new DefaultComboBoxModel<Alimentacion>(Alimentacion.values()));
		cbEspecialidad.setBounds(21, 178, 250, 32);
		contentPanel.add(cbEspecialidad);

		cbEspecialidad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listaModel.clear();
				refrescarComboCeldasDisponibles();
			}
		});

		JLabel lblCeldas = new JLabel("CELDAS ATENDIDAS");
		lblCeldas.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblCeldas.setBounds(21, 240, 250, 26);
		contentPanel.add(lblCeldas);

		listaModel = new DefaultListModel<>();
		listCeldas = new JList<>(listaModel);
		listCeldas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		JScrollPane scrollList = new JScrollPane(listCeldas);
		scrollList.setBounds(21, 280, 250, 150);
		contentPanel.add(scrollList);

		JLabel lblAgregar = new JLabel("AÑADIR CELDA");
		lblAgregar.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
		lblAgregar.setBounds(310, 240, 200, 26);
		contentPanel.add(lblAgregar);

		cbNuevaCelda = new JComboBox<>();
		cbNuevaCelda.setBounds(310, 280, 250, 32);
		contentPanel.add(cbNuevaCelda);

		setLocationRelativeTo(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAdd = new JButton("AÑADIR");
				btnAdd.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
				btnAdd.setBounds(310, 325, 120, 32);
				btnAdd.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Celda sel = (Celda) cbNuevaCelda.getSelectedItem();
						if (sel != null && !listaModel.contains(sel)) {
							listaModel.addElement(sel);
							refrescarComboCeldasDisponibles();
						}
					}
				});
				contentPanel.add(btnAdd);
			}
			{
				JButton btnGuardar = new JButton("GUARDAR");
				btnGuardar.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
				btnGuardar.setBounds(310, 380, 250, 40);
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						crearVeterinario();
					}
				});
				contentPanel.add(btnGuardar);
			}
			{
				JButton btnQuitar = new JButton("QUITAR");
				btnQuitar.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
				btnQuitar.setBounds(21, 440, 250, 32);
				btnQuitar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Celda sel = listCeldas.getSelectedValue();
						if (sel != null) {
							listaModel.removeElement(sel);
							refrescarComboCeldasDisponibles();
						}
					}
				});
				contentPanel.add(btnQuitar);
			}
			{
				JButton btnCancelar = new JButton("CANCELAR");
				btnCancelar.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
				btnCancelar.setBounds(310, 430, 250, 40);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				contentPanel.add(btnCancelar);	
			}
		}

		refrescarComboCeldasDisponibles();
	}

	private void refrescarComboCeldasDisponibles() {
		Alimentacion esp = (Alimentacion) cbEspecialidad.getSelectedItem();
		cbNuevaCelda.removeAllItems();
		for (Celda c : zoo.getTodasLasCeldas()) {
			if (c.tieneAnimales()
					&& c.getAlimentacion() == esp
					&& !listaModel.contains(c)) {
				cbNuevaCelda.addItem(c);
			}
		}
	}

	private void crearVeterinario() {
		try {
			String nombre = txtNombre.getText().trim();
			String carnet = txtCarnet.getText().trim();
			Alimentacion especialidad = (Alimentacion) cbEspecialidad.getSelectedItem();

			if (nombre.isEmpty() || carnet.isEmpty()) {
				throw new IllegalArgumentException("Nombre y Carnet no pueden estar vacíos.");
			}

			Veterinario nuevo = new Veterinario(nombre, carnet, especialidad);
			for (int i = 0; i < listaModel.size(); i++) {
				nuevo.agregarCeldaAtencion(listaModel.getElementAt(i));
			}

			zoo.agregarTrabajador(nuevo);
			ventanaPrincipal.actualizarTablaVeterinarios();
			ventanaPrincipal.actualizarResumen();
			JOptionPane.showMessageDialog(this, "Veterinario creado exitosamente.");
			dispose();
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}