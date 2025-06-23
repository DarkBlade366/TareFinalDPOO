package jdialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Custodio;
import clases.Servicio;
import clases.Zoologico;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import runner.Zoo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearServicio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Zoologico controlador;
	private JTextField textFieldNombre;
	private JTextField textFieldCarnet;
	private JTextField textFieldZona;
	private Zoo ventanaPrincipal;

	public CrearServicio(Zoologico controlador, Zoo ventanaPrincipal) {
		this.controlador = controlador;
		this.ventanaPrincipal = ventanaPrincipal;
		initComponents();
	}
	public CrearServicio(Zoologico controlador, String nombre, String carnet) {
		this.controlador = controlador;
		initComponents();
		textFieldNombre.setText(nombre);  
		textFieldCarnet.setText(carnet);
	}
	private void initComponents(){

		setBounds(100, 100, 606, 559);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("NOMBRE");
			label.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
			label.setBounds(21, 89, 168, 26);
			contentPanel.add(label);
		}
		{
			textFieldNombre = new JTextField();
			textFieldNombre.setColumns(10);
			textFieldNombre.setBounds(21, 129, 186, 32);
			contentPanel.add(textFieldNombre);
		}
		{
			JLabel label = new JLabel("CARNET");
			label.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
			label.setBounds(373, 89, 168, 26);
			contentPanel.add(label);
		}
		{
			textFieldCarnet = new JTextField();
			textFieldCarnet.setColumns(10);
			textFieldCarnet.setBounds(373, 129, 186, 32);
			contentPanel.add(textFieldCarnet);
		}
		{
			JLabel lblZona = new JLabel("ZONA");
			lblZona.setFont(new Font("Segoe UI Black", Font.PLAIN, 21));
			lblZona.setBounds(273, 268, 168, 26);
			contentPanel.add(lblZona);
		}
		{
			textFieldZona = new JTextField();
			textFieldZona.setColumns(10);
			textFieldZona.setBounds(21, 303, 538, 32);
			contentPanel.add(textFieldZona);
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
						crearServicio();
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
	private void crearServicio() {
		try {
			String nombre = textFieldNombre.getText().trim();
			String carnet = textFieldCarnet.getText().trim();
			String zona = textFieldZona.getText().trim();

			if (nombre.isEmpty() || carnet.isEmpty() || zona.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Debe llenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			Servicio servicio = new Servicio(nombre, carnet, zona);
			controlador.agregarTrabajador(servicio);
			ventanaPrincipal.actualizarResumen();
			ventanaPrincipal.actualizarTablaServicio();

			JOptionPane.showMessageDialog(this, "Servicio creado exitosamente.");
			dispose();

		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void llenarCampos(String nombre, String carnet) {
	    textFieldNombre.setText(nombre);
	    textFieldCarnet.setText(carnet);
	}

}