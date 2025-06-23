package jdialog;

import java.awt.BorderLayout;


import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import runner.Zoo;
import clases.Servicio;
import clases.Zoologico;

public class EditarServicio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombre;
	private JTextField textFieldCarnet;
	private JTextField textFieldZona;
	private Servicio servicio;
	private Zoologico controlador;
	private Zoo ventanaPrincipal;

	public EditarServicio(Servicio servicio, Zoologico controlador, Zoo ventanaPrincipal) {
		this.servicio = servicio;
		this.controlador = controlador;
		this.ventanaPrincipal = ventanaPrincipal;
		
		setTitle("Editar Servicio");
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

		gardarCambios();
		setLocationRelativeTo(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnGuardar = new JButton("GUARDAR");
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						editarServicio();
					}
				});
				buttonPane.add(btnGuardar, BorderLayout.EAST);
			}
			{
	

				JButton cancelButton = new JButton("CANCEL");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton, BorderLayout.WEST);
			}
		}
	}
	private void editarServicio() {
		try {
			String nombre = textFieldNombre.getText().trim();
			String zona = textFieldZona.getText().trim();

			if (nombre.isEmpty() || zona.isEmpty()) {
				throw new IllegalArgumentException("Todos los campos deben estar completos.");
			}

			servicio.setNombre(nombre);
			servicio.setZona(zona);

			ventanaPrincipal.actualizarTablaAnimales();
			ventanaPrincipal.actualizarResumen();
			ventanaPrincipal.actualizarTablaServicio();

			JOptionPane.showMessageDialog(this, "Servicio actualizado correctamente.");
			dispose();
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void gardarCambios() {
		textFieldNombre.setText(servicio.getNombre());
        textFieldCarnet.setText(servicio.getNumCarnet());
        textFieldZona.setText(servicio.getZona());
    }
}
